import com.leapmotion.leap.Controller;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       DisplayImage displayImage = new DisplayImage();
        LeapListener listener = new LeapListener(displayImage);
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }

}