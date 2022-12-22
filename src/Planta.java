import javax.swing.*;
import java.awt.*;

public class Planta {

    public static Dimension _canvas;
    public Dimension _img;

    //completa -> botones: 2
    public Image imagen;
    public String[] nomCompleta;
    public String[] infoCompleta;
    public Dimension[] posicionBotones;
    public Dimension[] dimensionBotones;

    public Planta(Image imagen, Dimension img, String[] nomCompleta, String[] infoCompleta, Dimension[] posicionBotones, Dimension[] dimensionBotones) {
        this.imagen = imagen;
        this.nomCompleta = nomCompleta;
        this.infoCompleta = infoCompleta;
        this.posicionBotones = posicionBotones;
        this.dimensionBotones = dimensionBotones;
        this._img = img;
    }

    public ImagePanel getImagePanel(){
        ImagePanel res = new ImagePanel(imagen, _canvas, _img);
        res.setLayout(null);
        for(int i=0;i<nomCompleta.length;++i){
            JButton jb = new JButton();
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(true);
            jb.setBounds(posicionBotones[i].width,posicionBotones[i].height,
                    dimensionBotones[i].width,dimensionBotones[i].height);
            jb.setToolTipText(nomCompleta[i]);
            int finalI = i;
            jb.addActionListener(evt -> JOptionPane.showOptionDialog(null, infoCompleta[finalI],nomCompleta[finalI], JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null));
            res.add(jb, null);
        }
        return res;
    }
}
