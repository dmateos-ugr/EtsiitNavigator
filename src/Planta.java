import javax.swing.*;
import java.awt.*;

public class Planta {

    public static Dimension _canvas;
    private final Rectangle[] rectBoton;
    public Dimension _img;

    //completa -> botones: 2
    public Image imagen;
    public String[] nomCompleta;
    public String[] infoCompleta;

    public Planta(Image imagen, Dimension img, String[] nomCompleta,
                  String[] infoCompleta, Rectangle[] botones) {
        this.imagen = imagen;
        this.nomCompleta = nomCompleta;
        this.infoCompleta = infoCompleta;
        this.rectBoton = botones;
        this._img = img;
    }

    public ImagePanel getImagePanel(){
        ImagePanel res = new ImagePanel(imagen, _canvas, _img);
        res.setLayout(null);
        for(int i=0;i<nomCompleta.length;++i){
            JButton jb = new JButton();
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(true);
            jb.setBounds(rectBoton[i]);
            jb.setToolTipText(nomCompleta[i]);
            int finalI = i;
            jb.addActionListener(evt -> JOptionPane.showOptionDialog(null, infoCompleta[finalI],
                    nomCompleta[finalI], JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    null, new Object[]{}, null));
            res.add(jb, null);
        }
        return res;
    }
}
