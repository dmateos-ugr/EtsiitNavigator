import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    static Robot robot;
    private static Robot getRobot() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (AWTException e) {
                throw new RuntimeException(e);
            }
        }
        return robot;
    }
    public static void moveTo(Vector2 pos) {
        getRobot().mouseMove((int)pos.x, (int)pos.y);
    }

    public static void click() {
        getRobot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        getRobot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
