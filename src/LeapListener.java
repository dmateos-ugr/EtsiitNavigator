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

    public boolean isInsideRect(Rect rect) {
        return x >= rect.top_left.x && x <= rect.bottom_right.x &&
                y >= rect.top_left.y && y <= rect.bottom_right.y;
    }
}


//EN PROCESO
class Rect {
    Vector2 top_left, bottom_right;

    public Rect(float top, float left, float bottom, float right) {
        top_left = new Vector2(top, left);
        bottom_right = new Vector2(bottom, right);
    }

    public static Rect ofSize(float top, float left, float height, float width) {
        return new Rect(top, left, top+height, left+width);
    }
}


public class LeapListener extends Listener {
    // Tamaño de los planos

    DisplayImage displayImage;

    private long lastSwipeTimestampMicrosecs;

    private boolean insideRect;
    private long insideRectSinceTimestampMicrosecs;
    private boolean insideRectAlreadyClicked;


    // Transformar un punto 3D del frame a un punto 2D de la app
    private Vector2 toAppPoint(Vector point, Frame frame) {
        // https://developer-archive.leapmotion.com/documentation/v2/javascript/devguide/Leap_Coordinate_Mapping.html
        InteractionBox iBox = frame.interactionBox();
        Vector normalizedPoint = iBox.normalizePoint(point);

        normalizedPoint = normalizedPoint.times(2f); // scale
        normalizedPoint = normalizedPoint.minus(new Vector(.5f, .5f, .5f));

        // TODO: pensar si queremos restringir el cursor al area de las imagenes (appWidth y appHeight).
        // En ese caso necesitaremos tambien la posicion de las imagenes en la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float width = screenSize.width; // displayImage.appWidth
        float height = screenSize.height; // displayImage.appHeight
        float appX = Math.min(normalizedPoint.getX() * width, width);
        float appY = Math.min((1 - normalizedPoint.getY()) * height, height);
        return new Vector2(appX, appY);
    }

    public LeapListener(DisplayImage displayImage) {
        this.displayImage = displayImage;
    }

    public void onInit(Controller controller) {
        System.out.println("LeapListener onInit");
    }

    public void onConnect(Controller controller) {
        System.out.println("LeapListener onConnect");
        //controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
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


    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        if (frame.hands().count() > 0) {
            Hand hand = frame.hands().get(0);

            // Posicion de la palma de la mano: es lo que mejor funciona
            Vector2 p = toAppPoint(hand.stabilizedPalmPosition(), frame);
            Mouse.moveTo(p);
            System.out.print("\r" + p);

            // Ejemplo de lo del rectangulo. Lo suyo es hacer algo similar para cada rectangulo de las fotos que se
            // pueda clickar. Cuando se tengan los rectangulos hechos se piensa como generalizar esto.
            insideRect = p.isInsideRect(new Rect(800, 600,1000, 800));
            if (insideRect) {
                if (!insideRectAlreadyClicked) {
                    if (insideRectSinceTimestampMicrosecs == 0)
                        insideRectSinceTimestampMicrosecs = frame.timestamp();
                    if (frame.timestamp() - insideRectSinceTimestampMicrosecs >= 1_000_000) {
                        System.out.println("inside rect for 1s");
                        insideRectAlreadyClicked = true;
                    }
                }
            } else {
                insideRectSinceTimestampMicrosecs = 0;
                insideRectAlreadyClicked = false;
            }

            Finger finger = hand.fingers().get(1); // dedo indice
            if (finger.isExtended()) {
                // quitado (la palma de la mano funciona mejor)
            }
        }


        for (Gesture gesture : frame.gestures()) {
            switch (gesture.type()) {
                case TYPE_SCREEN_TAP: {
                    /*
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);

                    // Creo que lo mejor es ignorar la posicion del screenTap y usar solo la posicion actual del cursor
                    // sin moverlo
                    //Vector2 appPoint = toAppPoint(screenTap.position(), frame);
                    //Mouse.moveTo((int)appPoint.x, (int)appPoint.y);

                    Vector2 appPoint = toAppPoint(screenTap.pointable().stabilizedTipPosition(), frame);
                    System.out.println("screen tap: " + appPoint.x + ", " + appPoint.y + ", " + screenTap.state());
                    Mouse.click();

                     */
                    // TODO: enseñar popup
                }

                case TYPE_KEY_TAP: {
                    // Falla mas que las escopetillas de las ferias
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    Vector2 appPoint = toAppPoint(keyTap.pointable().stabilizedTipPosition(), frame);
                    System.out.println("key tap: " + appPoint.x + ", " + appPoint.y + ", " + keyTap.state());
                    Mouse.click();

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
                        displayImage.baja();
                        System.out.println("swipe up");
                    } else if (swipe.direction().getY() < -0.7) {
                        displayImage.sube();
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