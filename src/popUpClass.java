import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class popUpClass {

    JFrame jFrame;
    JFrame focus;
    JDialog jd;
    JLabel jLabel;
    public JButton b;

    final int[] contador1 = {0};
    popUpClass(String textoboton, String textoframe, JFrame sup){

        focus=sup;
        b=new JButton(textoboton);
        b.setVisible(true);
        /*POPUPS*/
        jFrame = new JFrame();

        jd = new JDialog(jFrame);

        jd.setLayout(new FlowLayout());
        jLabel = new JLabel(textoframe);

        jd.setBounds(500, 300, 400, 300);


        jd.add(jLabel);
        jd.setVisible(false);
        jFrame.setVisible(false);
        b.setVisible(true);
        click();

    }

    void click(){
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador1[0] = contador1[0] +1;
                if((contador1[0]%2)==0){
                    //jFrame.setVisible(false);
                    jd.setVisible(false);
                    //popupB.cerrar(); //NO TOY SEGURA DE ESTO
                   focus.requestFocus();
                }else{
                    //jFrame.setVisible(true);
                    jd.setVisible(true);
                    focus.requestFocus();
                }
            }
        });

    }

    public void esconder(){
        jFrame.setVisible(false);
    }

    public void cambiarVista(boolean a){
        jFrame.setVisible(a);
    }

    public void cerrar(){
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    JButton Button(){
        return b;
    }

}
