import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class RoundedBorder implements Border {
    // https://stackoverflow.com/questions/4219511/draw-rectangle-border-thickness

    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Si es un boton, cambiar el color y la anchura del borde en funcion de si el ratón está encima o no
        Color prevColor = g2d.getColor();
        Stroke prevStroke = g2d.getStroke();
        if (c instanceof JButton && ((JButton)c).getModel().isRollover()) {
            g2d.setColor(Color.gray);
            g2d.setStroke(new BasicStroke(4));
        } else {
            g2d.setColor(Color.black);
            g2d.setStroke(new BasicStroke(2));
        }
        g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
        g2d.setColor(prevColor);
        g2d.setStroke(prevStroke);
    }
}


public class Planta {

    public static Dimension _canvas;
    private final Rectangle[] rectBoton;
    public Dimension _img;
    public Dimension _old;

    //completa -> botones: 2
    public Image imagen;
    public String[] nomCompleta;
    public String[] infoCompleta;

    static ImageIcon gifImg = new ImageIcon("Planos/114_2.gif");

    public Planta(Image imagen, Dimension img,Dimension old, String[] nomCompleta,
                  String[] infoCompleta, Rectangle[] botones) {
        this.imagen = imagen;
        this.nomCompleta = nomCompleta;
        this.infoCompleta = infoCompleta;
        this.rectBoton = botones;
        this._img = img;
        this._old = old;
    }

    public Rectangle scaledRectangle(Rectangle r) {
        Point p = new Point(_canvas.width / 2 - _img.width / 2, _canvas.height / 2 - _img.height / 2);
        r.x = (int) (p.x + (_img.width  * ( (float) r.x /_old.width)));
        r.y = (int) (p.y + (_img.height * ( (float) r.y /_old.height)));
        r.width = (int) (_img.width * ((float)r.width/_old.width));
        r.height = (int) (_img.height * ((float)r.height/_old.height));
        return r;
    }

    public ImagePanel getImagePanel() {
        ImagePanel res = new ImagePanel(imagen, _canvas, _img);
        res.setLayout(null);
        for (int i = 0; i < nomCompleta.length; ++i) {
            rectBoton[i] = scaledRectangle(rectBoton[i]);
            JLabel gif = new JLabel(gifImg);
            gif.setBounds(0, 0, 100, 100);
            gif.setVisible(false);
            res.add(gif);

            float scaleRatio =(float)_img.width/_old.width;
            JButton jb = new HoverButton(gif);
            jb.setName(nomCompleta[i]);
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(true);
            jb.setBounds(rectBoton[i]);
            jb.setBorder(new RoundedBorder((int)(30*scaleRatio)));
            //jb.setToolTipText(nomCompleta[i]);
            int finalI = i;
            jb.addActionListener(evt -> Popup.show(nomCompleta[finalI], infoCompleta[finalI], scaleRatio));
            res.add(jb, null);
        }
        return res;
    }
}
