import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class PlantasCompletas {

    public static Planta getPlanta(int a,Image img){
        Planta res = null;
        switch(a){
            case 0:  //completa -> botones: 2
            default:
                String[] nomCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                String[] infoCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(50,50),
                        new Dimension(50,50)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100,100),
                        new Dimension(100,100)
                };
                res = new Planta(img,nomCompleta,infoCompleta,posicionBotones,dimensionBotones);
        }
        return res;
    }
}
