import com.leapmotion.leap.*;
import com.leapmotion.leap.Frame;

import java.awt.*;

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
}



public class LeapListener extends Listener {
    // Tamaño de los planos

    DisplayImage displayImage;

    private long lastSwipeTimestampMicrosecs;


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
                        displayImage.hidepopups();
                        System.out.println("swipe right");
                    } else if (swipe.direction().getX() < -0.7) {
                        displayImage.hidepopups();
                        System.out.println("swipe left");
                    }
            }
        }

    }
}