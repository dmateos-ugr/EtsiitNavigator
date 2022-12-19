import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class popupButton(int pos) throws IOException extends JFrame implements ActionListener {

// popup
    Popup p;

    // constructor
    pop(){
        // create a frame
        JFrame f = new JFrame(new File("Planos/completa.jpg"));

        // create a label
        JLabel l = new JLabel("Secretaria");

        f.setSize(400, 400);

        PopupFactory pf = new PopupFactory();

        // create a panel
        JPanel p2 = new JPanel();

        // set Background of panel
        p2.setBackground(Color.blue);

        p2.add(l);

        // create a popup
        p = pf.getPopup(f, p2, 180, 100);

        // create a button
        JButton b = new JButton(new ImageIcon("Planos/dot.png"));

        // add action listener
        b.addActionListener(this);

        // create a panel
        JPanel p1 = new JPanel();

        p1.add(b);
        f.add(p1);
        f.show();
    }

    // if the button is pressed
    public void actionPerformed(ActionEvent e){
        p.show();
    }
    // main class
    public static void main(String args[]){
        pop p = new pop();
    }
}