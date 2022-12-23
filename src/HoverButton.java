import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class HoverMouseListener implements MouseListener {
    Timer timer;
    JButton button;
    JLabel gif;
    HoverMouseListener(Timer timer, JButton button, JLabel gif) {
        this.timer = timer;
        this.button = button;
        this.gif = gif;
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        timer.stop();
        gif.setVisible(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        timer.restart();
        gif.setVisible(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        timer.stop();
        gif.setVisible(false);

        // TODO que se haga invisible de verdad cuando justo despues entra en otro rectangulo. Esto no funciona.
        System.out.println("mouseExited " + this.button.getName());
        gif.invalidate();
        gif.revalidate();
        gif.repaint();

        gif.getParent().invalidate();
        gif.getParent().revalidate();
        gif.getParent().repaint();

        gif.getParent().getParent().invalidate();
        gif.getParent().getParent().revalidate();
        gif.getParent().getParent().repaint();

        gif.getRootPane().invalidate();
        gif.getRootPane().revalidate();
        gif.getRootPane().repaint();

    }
}
public class  HoverButton extends JButton {
    Timer timer;

    HoverButton(JLabel gif) {
        super();
        timer = new Timer(2280, e -> {
            gif.setVisible(false);
            doClick();
        });
        timer.setRepeats(false);

        JButton this2 = this;

        addMouseListener(new HoverMouseListener(timer, this, gif));
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = SwingUtilities.convertPoint(this2, e.getX(), e.getY(), gif.getParent());
                Rectangle currBounds = gif.getBounds();
                gif.setBounds(p.x - currBounds.width/2, p.y - currBounds.height/2, currBounds.width, currBounds.height);
            }
        });
    }

    public void stopTimer(){
        timer.stop();
    }
}
