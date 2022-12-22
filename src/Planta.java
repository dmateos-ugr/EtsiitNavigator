import javax.swing.*;
import java.awt.*;
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

            JButton jb = new HoverButton(gif);
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(true);
            jb.setBounds(rectBoton[i]);
            jb.setToolTipText(nomCompleta[i]);
            int finalI = i;
            jb.addActionListener(evt -> JOptionPane.showOptionDialog(null, infoCompleta[finalI],
                    nomCompleta[finalI], JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{}, null));
            res.add(jb, null);
        }
        return res;
    }
}
