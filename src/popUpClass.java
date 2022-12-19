import javax.swing.*;
import java.awt.*;


public class popUpClass {

    JFrame jFrame;
    JDialog jd;
    JLabel jLabel;

    popUpClass(String texto){
        /*POPUPS*/
        jFrame = new JFrame();

        jd = new JDialog(jFrame);

        jd.setLayout(new FlowLayout());

        jd.setBounds(500, 300, 400, 300);

        jLabel = new JLabel(texto);
        jLabel.setVisible(true);

        jd.add(jLabel);
        jd.setVisible(true);
        jFrame.setVisible(false);


    }

    public void cambiarVista(boolean a){
        jFrame.setVisible(a);
    }

    public void cerrar(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
