import java.awt.*;

public final class PlantasCompletas {

    public static Planta getPlanta(int a,Image img, Dimension _img){
        Planta res = null;
        switch(a){
            case 6: {
                //completa -> botones: 2
                String[] nomCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                String[] infoCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100)
                };
                res = new Planta(img, _img, nomCompleta, infoCompleta, rectBoton);
            }
            break;
            case 5: {
                //quinta -> botones: 1
                String[] nomQuinta= new String[]{ "ETSIIT quinta"};
                String[] infoQuinta = new String[]{"ETSIIT quinta"};

                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(100,100,100,100)
                };
                res = new Planta(img, _img, nomQuinta, infoQuinta, rectBoton);
            }
            break;
            case 4: {
                //cuarta -> botones: 3
                String[] nomCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};
                String[] infoCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};


                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100),
                        new Rectangle(50,50,50,50)
                };
                res = new Planta(img, _img, nomCuarta, infoCuarta, rectBoton);
            }
            break;
            case 3: {
                //tercera -> botones: 3
                String[] nomTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};
                String[] infoTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100),
                        new Rectangle(50,50,50,50)
                };
                res = new Planta(img, _img, nomTercera, infoTercera, rectBoton);
            }
            break;

            case 2: {
                //segunda -> botones: 2
                String[] nomSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};
                String[] infoSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};

                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100)
                };
                res = new Planta(img, _img, nomSegunda, infoSegunda, rectBoton);
            }
            break;
            case 1: {
                //primera -> botones: 2
                String[] nomPrimera = new String[]{"ETSIIT", "ETSIIT primera"};
                String[] infoPrimera = new String[]{"ETSIIT", "ETSIIT primera"};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100)
                };
                res = new Planta(img, _img, nomPrimera, infoPrimera, rectBoton);
            }
            break;

            case 0: {
                String[] nomBaja = new String[]{"ETSIIT baja"};
                String[] infoBaja = new String[]{"ETSIIT baja"};
                Rectangle[] rectBoton = new Rectangle[]{
                        new Rectangle(200,200,200,200),
                        new Rectangle(100,100,100,100)
                };
                res = new Planta(img, _img, nomBaja, infoBaja, rectBoton);
            }
            break;
        }
        return res;
    }
}
