import java.awt.*;

public final class PlantasCompletas {

    public static Planta getPlanta(int a,Image img, Dimension _img){
        Planta res = null;
        switch(a){
            case 6: {
                //completa -> botones: 2
                String[] nomCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                String[] infoCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100)
                };
                res = new Planta(img, _img, nomCompleta, infoCompleta, posicionBotones, dimensionBotones);
            }
            break;
            case 5: {
                //quinta -> botones: 1
                String[] nomQuinta= new String[]{ "ETSIIT quinta"};
                String[] infoQuinta = new String[]{"ETSIIT quinta"};


                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100)
                };
                res = new Planta(img, _img, nomQuinta, infoQuinta, posicionBotones, dimensionBotones);
            }
            break;
            case 4: {
                //cuarta -> botones: 3
                String[] nomCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};
                String[] infoCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};


                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100),
                        new Dimension(300,300)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100),
                        new Dimension(100,100)

                };
                res = new Planta(img, _img, nomCuarta, infoCuarta, posicionBotones, dimensionBotones);
            }
            break;
            case 3: {
                //tercera -> botones: 3
                String[] nomTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};
                String[] infoTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};
                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100),
                        new Dimension(300,300)

                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100),
                        new Dimension(100,100)

                };
                res = new Planta(img, _img, nomTercera, infoTercera, posicionBotones, dimensionBotones);
            }
            break;

            case 2: {
                //segunda -> botones: 2
                String[] nomSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};
                String[] infoSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};

                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100)
                };
                res = new Planta(img, _img, nomSegunda, infoSegunda, posicionBotones, dimensionBotones);
            }
            break;
            case 1: {
                //primera -> botones: 2
                String[] nomPrimera = new String[]{"ETSIIT", "ETSIIT primera"};
                String[] infoPrimera = new String[]{"ETSIIT", "ETSIIT primera"};
                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100)
                };
                res = new Planta(img, _img, nomPrimera, infoPrimera, posicionBotones, dimensionBotones);
            }
            break;

            case 0: {
                String[] nomBaja = new String[]{"ETSIIT baja"};
                String[] infoBaja = new String[]{"ETSIIT baja"};
                Dimension[] posicionBotones = new Dimension[]{
                        new Dimension(200, 200),
                        new Dimension(100, 100)
                };
                Dimension[] dimensionBotones = new Dimension[]{
                        new Dimension(100, 100),
                        new Dimension(100, 100)
                };
                res = new Planta(img, _img, nomBaja, infoBaja, posicionBotones, dimensionBotones);
            }
            break;
        }
        return res;
    }
}
