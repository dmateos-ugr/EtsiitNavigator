import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class popupButton(int pos) throws IOException extends JFrame implements ActionListener {

    private DisplayImage displayImage;
// popup
    Popup p;

    // constructor
    pop(){

        if (displayImage.getCurrentIndex() == 0){
            JFrame f = new JFrame(new File("Planos/completa.jpg"));

            //Butón de biblioteca:
            JButton butonBiblioteca = new JButton(new ImageIcon("Planos/dot.png"));
            butonBiblioteca.setBounds(392,190,14,14);
            JLabel l = new JLabel("Biblioteca");
            JPanel p = new JPanel();
            p.setBackground(Color.blue);
            p.add(l);
            PopupFactory pf = new PopupFactory();
            p = pf.getPopup(f, p, 180, 100);
            butonBiblioteca.addActionListener(this);
            f.add(butonBiblioteca);
            f.show();

            //Butón de secretaria:
            JButton butonBiblioteca = new JButton(new ImageIcon("Planos/dot.png"));
            butonBiblioteca.setBounds(154,461,14,14);
            JLabel l = new JLabel("Secretaria");
            JPanel p = new JPanel();
            p.setBackground(Color.blue);
            p.add(l);
            PopupFactory pf = new PopupFactory();
            p = pf.getPopup(f, p, 180, 100);
            butonBiblioteca.addActionListener(this);
            f.add(butonBiblioteca);
            f.show();

            //Butón de cafeteria:
            JButton butonBiblioteca = new JButton(new ImageIcon("Planos/dot.png"));
            butonBiblioteca.setBounds(355,283,14,14);
            JLabel l = new JLabel("Cafeteria");
            JPanel p = new JPanel();
            p.setBackground(Color.blue);
            p.add(l);
            PopupFactory pf = new PopupFactory();
            p = pf.getPopup(f, p, 180, 100);
            butonBiblioteca.addActionListener(this);
            f.add(butonBiblioteca);
            f.show();
        }
        if (displayImage.getCurrentIndex() == 1){
            JFrame f = new JFrame(new File("Planos/quinta.jpg"));
        }
        if (displayImage.getCurrentIndex() == 2){
            JFrame f = new JFrame(new File("Planos/cuarta.jpg"));
        }
        if (displayImage.getCurrentIndex() == 3){
            JFrame f = new JFrame(new File("Planos/tercera.jpg"));
        }
        if (displayImage.getCurrentIndex() == 4){
            JFrame f = new JFrame(new File("Planos/segunda.jpg"));
        }
        if (displayImage.getCurrentIndex() == 5){
            JFrame f = new JFrame(new File("Planos/primera.jpg"));
        }
        if (displayImage.getCurrentIndex() == 6){
            JFrame f = new JFrame(new File("Planos/baja.jpg"));
        }
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