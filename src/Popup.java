import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Popup extends JDialog implements MouseListener {
    public Popup(String title, String text) {
        // Process text
        text = "<html><div style='text-align: center;'>" + text.replace("\n", "<br>") + "</div></html>";

        setUndecorated(true);
        setBackground(new Color(100, 100, 100, 150));

        // background paint
        //lpg = new LinearGradientPaint(0, 0, 0, getHeight() / 2, new float[] { 0f,
        //        0.3f, 1f }, new Color[] { new Color(0.8f, 0.8f, 1f),
        //        new Color(0.7f, 0.7f, 1f), new Color(0.6f, 0.6f, 1f) });

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 3), // borde exterior: linea negra
                BorderFactory.createEmptyBorder(10, 10, 10, 10)) // borde interior: vacio, margenes
        );
        //panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Queremos texto negro sin transparencia, pero fondo transparente para que coja el del pane
        JLabel titleLbl = new JLabel(title);
        titleLbl.setOpaque(true);
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setBackground(new Color(255, 255, 255, 0)); // transparente
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLbl.setFont(new Font(titleLbl.getFont().getName(), Font.BOLD, 20));
        panel.add(titleLbl);


        panel.add(Box.createRigidArea(new Dimension(0, 10))); // padding

        JLabel txtLbl = new JLabel(text);
        txtLbl.setOpaque(true);
        txtLbl.setForeground(Color.BLACK);
        txtLbl.setBackground(new Color(255, 255, 255, 0)); // transparente
        txtLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtLbl.setFont(new Font(titleLbl.getFont().getName(), Font.PLAIN, 15));
        panel.add(txtLbl);

        setContentPane(panel);
        pack();

        // set location
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2 - getWidth()/2, screenSize.height/2 - getHeight()/2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void show(String title, String text) {
        Popup f = new Popup(title, text);
        f.setVisible(true);
    }
}
