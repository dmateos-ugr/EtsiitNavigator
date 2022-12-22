import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Planta {
    //completa -> botones: 2
    public Image imagen;
    public String[] nomCompleta;
    public String[] infoCompleta;
    public Dimension[] posicionBotones;
    public Dimension[] dimensionBotones;

    public Planta(Image imagen, String[] nomCompleta, String[] infoCompleta, Dimension[] posicionBotones, Dimension[] dimensionBotones) {
        this.imagen = imagen;
        this.nomCompleta = nomCompleta;
        this.infoCompleta = infoCompleta;
        this.posicionBotones = posicionBotones;
        this.dimensionBotones = dimensionBotones;
    }

    public ImagePanel getImagePanel(){
        ImagePanel res = new ImagePanel(imagen);
        res.setLayout(null);
        for(int i=0;i<nomCompleta.length;++i){
            JButton jb = new JButton();
            jb.setContentAreaFilled(false);
            jb.setFocusPainted(true);
            jb.setBounds(posicionBotones[i].width,posicionBotones[i].height,
                    dimensionBotones[i].width,dimensionBotones[i].height);
            jb.setToolTipText(nomCompleta[i]);
            int finalI = i;
            jb.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JOptionPane.showOptionDialog(null, infoCompleta[finalI],nomCompleta[finalI], JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
                }
            });
            res.add(jb, null);
        }
        return res;
    }
}
