import com.leapmotion.leap.*;
import com.leapmotion.leap.Frame;

import javax.management.DescriptorRead;
import java.awt.*;
import java.awt.event.InputEvent;

class Vector2 {
    float x, y;
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static Vector2 from(Vector v) {
        return new Vector2(v.getX(), v.getY());
    }
}

public class LeapListener extends Listener {
    // Tamaño de los planos
    final int APP_WIDTH = 1600; //800;
    final int APP_HEIGHT = 900; //600;

    private long lastSwipeTimestampMicrosecs;

    // Transformar un punto 3D del frame a un punto 2D de la app
    private Vector2 toAppPoint(Vector point, Frame frame) {
        // https://developer-archive.leapmotion.com/documentation/v2/javascript/devguide/Leap_Coordinate_Mapping.html
        InteractionBox iBox = frame.interactionBox();
        Vector normalizedPoint = iBox.normalizePoint(point);

        float appX = normalizedPoint.getX() * APP_WIDTH;
        float appY = (1 - normalizedPoint.getY()) * APP_HEIGHT;
        return new Vector2(appX, appY);
    }

    public void onInit(Controller controller) {
        System.out.println("LeapListener onInit");
    }

    public void onConnect(Controller controller) {
        System.out.println("LeapListener onConnect");
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
    }

    public void onDisconnect(Controller controller) {
        System.out.println("LeapListener onDisconnect");
    }

    public void onExit(Controller controller) {
        System.out.println("LeapListener onExit");
    }

    public static Vector lineIntersection(Vector planePoint, Vector planeNormal, Vector linePoint, Vector lineDirection) {
        if (planeNormal.dot(lineDirection.normalized()) == 0) {
            return null;
        }

        float t = (planeNormal.dot(planePoint) - planeNormal.dot(linePoint)) / planeNormal.dot(lineDirection.normalized());
        return linePoint.plus(lineDirection.normalized().times(t));
    }

    /*
    public static float planeDistance1(Vector linePoint, Vector lineDirection, Vector2 coords) {
        //t = (-DISTANCE - linePoint.z)/(lineDirection.z)
        //(x, y, z) = linePoint + lineDirection*t
        //	= (linePoint.x + lineDirection.x*t, linePoint.y + lineDirection.y*t, -DISTANCE)
        //
        //x = linePoint.x + lineDirection.x*(-DISTANCE - linePoint.z)/lineDirection.z
        //(x - linePoint.x)*lineDirection.z/lineDirection.x + linePoint.z = -DISTANCE
        //(y - linePoint.y)*lineDirection.z/lineDirection.y + linePoint.z = -DISTANCE
        float d = (coords.x - linePoint.getX())*lineDirection.getZ()/lineDirection.getX() + linePoint.getZ();
        return -d;
    }

    public static float planeDistance2(Vector linePoint, Vector lineDirection, Vector2 coords) {
        float d = (coords.y - linePoint.getY())*lineDirection.getZ()/lineDirection.getY() + linePoint.getZ();
        return -d;
    }
    */

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        if (frame.hands().count() > 0) {
            Hand hand = frame.hands().get(0);
            Finger finger = hand.fingers().get(1); // dedo indice
            if (finger.isExtended()) {
                /*
                // Intento de calibracion, no funciona
                Vector planeNormal = new Vector(0, 0, 1);
                Vector linePoint = finger.tipPosition();
                Vector lineDirection = finger.direction();
                float distance1 = planeDistance1(linePoint, lineDirection, new Vector2(0, 0));
                Vector planePoint1 = new Vector(0, 0, -distance1);
                float distance2 = planeDistance2(linePoint, lineDirection, new Vector2(0, 0));
                Vector planePoint2 = new Vector(0, 0, -distance2);

                Vector intersec1 = lineIntersection(planePoint1, planeNormal, linePoint, lineDirection);
                Vector intersec2 = lineIntersection(planePoint2, planeNormal, linePoint, lineDirection);
                System.out.println("\r" + distance1 + " " + intersec1 + " " + distance2 + " " + intersec2);
                 */


                // Obtener a donde apunta el dedo. Bastante inestable
                /*
                int SCREEN_DISTANCE_FROM_DEVICE = 500;

                Vector planePoint = new Vector(0, 0, -SCREEN_DISTANCE_FROM_DEVICE);
                Vector planeNormal = new Vector(0, 0, 1);
                Vector linePoint = finger.stabilizedTipPosition();
                Vector lineDirection = finger.direction();
                Vector result = lineIntersection(planePoint, planeNormal, linePoint, lineDirection);
                //Vector2 appPoint =

                float x = result.getX() + APP_WIDTH/2;
                x = Math.max(x, 0);
                x = Math.min(x, APP_WIDTH);

                float y = -result.getY();
                //y +=
                y = Math.max(y, 0);
                y = Math.min(y, APP_HEIGHT);

                //System.out.print("\r" + result);
                System.out.print("\r" + new Vector2(x, y));
                Mouse.moveTo((int)x, (int)y);

                 */

                // Lo mas facil: posicion del dedo sin tener en cuenta a donde apunta
                Vector2 appPoint = toAppPoint(finger.stabilizedTipPosition(), frame);
                Mouse.moveTo(appPoint);
                System.out.print("\r" + appPoint);

                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){
                }
            }
        }


        for (Gesture gesture : frame.gestures()) {
            switch (gesture.type()) {
                case TYPE_SCREEN_TAP: {
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);

                    // Creo que lo mejor es ignorar la posicion del screenTap y usar solo la posicion actual del cursor
                    // sin moverlo
                    //Vector2 appPoint = toAppPoint(screenTap.position(), frame);
                    //Mouse.moveTo((int)appPoint.x, (int)appPoint.y);

                    Vector2 appPoint = toAppPoint(screenTap.pointable().stabilizedTipPosition(), frame);
                    System.out.println("screen tap: " + appPoint.x + ", " + appPoint.y + ", " + screenTap.state());
                    Mouse.click();
                    // TODO: enseñar popup
                }

                case TYPE_KEY_TAP: {
                    /*
                    // Falla mas que las escopetillas de las ferias
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    Vector2 appPoint = toAppPoint(keyTap.pointable().stabilizedTipPosition(), frame);
                    System.out.println("key tap: " + appPoint.x + ", " + appPoint.y + ", " + keyTap.state());
                    */
                }

                case TYPE_CIRCLE: {
                    // Falla mas que las escopetillas de las ferias
                    /*
                    CircleGesture circle = new CircleGesture(gesture);
                    boolean clockwise = circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/2;
                    if (frame.fingers().extended().count() == 2 && !clockwise) {
                        System.out.println("circle");
                    }
                    //System.out.println("circle " + (clockwise ? "clockwise" : "counterclockwise"));
                    //System.out.println("circle " + frame.fingers().extended().count());
                     */
                }

                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    if (frame.timestamp() - lastSwipeTimestampMicrosecs < 1_000_000) // 1 segundo
                        break;
                    lastSwipeTimestampMicrosecs = frame.timestamp();

                    if (swipe.direction().getY() > 0.7) {
                        // TODO: subir planta (o bajar)
                        System.out.println("swipe up");
                    } else if (swipe.direction().getY() < -0.7) {
                        // TODO: subir planta (o bajar)
                        System.out.println("swipe down");
                    } else if (swipe.direction().getX() > 0.7) {
                        // TODO: quitar popup
                        System.out.println("swipe right");
                    } else if (swipe.direction().getX() < -0.7) {
                        // TODO: quitar popup
                        System.out.println("swipe left");
                    }
            }
        }

    }

}


/*
public class LeapListener extends Listener {
    // Tamaño de los planos
    final int APP_WIDTH = 800;
    final int APP_HEIGHT = 600;

    private Vector2 toAppPoint(Vector point, Frame frame) {
        InteractionBox iBox = frame.interactionBox();
        Vector normalizedPoint = iBox.normalizePoint(point);

        float appX = normalizedPoint.getX() * APP_WIDTH;
        float appY = (1 - normalizedPoint.getY()) * APP_HEIGHT;
        return new Vector2(appX, appY);
    }

    public void onInit(Controller controller) {
        System.out.println("LeapListener onInit");
    }

    public void onConnect(Controller controller) {
        System.out.println("LeapListener onConnect");
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
    }

    public void onDisconnect(Controller controller) {
        System.out.println("LeapListener onDisconnect");
    }

    public void onExit(Controller controller) {
        System.out.println("LeapListener onExit");
    }

    public void onFrame(Controller controller) {
        //System.out.println("LeapListener onFrame");

        Frame frame = controller.frame();

        if (frame.hands().count() > 0) {
            Hand hand = frame.hands().get(0);
            Finger finger = hand.fingers().get(1); // dedo indice
            if (finger.isExtended()) {
                // https://developer-archive.leapmotion.com/documentation/csharp/devguide/Leap_Coordinate_Mapping.html
                Vector2 appPoint = toAppPoint(finger.stabilizedTipPosition(), frame);
                System.out.print("\r" + appPoint.x + ", " + appPoint.y + "      ");
            }
        }

        for (Gesture gesture : frame.gestures()) {
            switch (gesture.type()) {
                case TYPE_SCREEN_TAP:
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    Vector2 appPoint = toAppPoint(screenTap.pointable().stabilizedTipPosition(), frame);
                    System.out.println("screen tap: " + appPoint.x + ", " + appPoint.y);
            }
        }
    }

}
 */