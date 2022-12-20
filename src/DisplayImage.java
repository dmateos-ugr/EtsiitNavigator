import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class DisplayImage {
    static final String[] imageFilenames = new String[]{
            "Planos/completa.jpg", "Planos/quinta.jpg", "Planos/cuarta.jpg", "Planos/tercera.jpg",
            "Planos/segunda.jpg", "Planos/primera.jpg", "Planos/baja.jpg"
    };
    static final String[] imageTitulos = new String[]{
            "Plano ETSIIT completo", "Plano ETSIIT quinta planta", "Plano ETSIIT cuarta planta", "Plano ETSIIT tercera planta",
            "Plano ETSIIT segunda planta", "Plano ETSIIT primera planta", "Plano ETSIIT planta baja",
    };

    //num de botones que tiene cada planta
    static final int[] numbotones = new int[]{2, 1, 3, 3, 2, 2, 1};

    //completa -> botones: 2
    static final String[] nomCompleta = new String[]{"ETSIIT", "ETSIIT completa"};
    static final String[] infoCompleta = new String[]{"ETSIIT", "ETSIIT completa"};

    //quinta -> botones: 1
    static final String[] nomQuinta= new String[]{ "ETSIIT quinta"};
    static final String[] infoQuinta = new String[]{"ETSIIT quinta"};

    //cuarta -> botones: 3
    static final String[] nomCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};
    static final String[] infoCuarta = new String[]{"ETSIIT", "ETSIIT cuarta", "botonInfo"};

    //tercera -> botones: 3
    static final String[] nomTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};
    static final String[] infoTercera = new String[]{"ETSIIT", "ETSIIT tercera","botonInfo"};

    //segunda -> botones: 2
    static final String[] nomSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};
    static final String[] infoSegunda = new String[]{"ETSIIT", "ETSIIT segunda"};

    //primera -> botones: 2
    static final String[] nomPrimera = new String[]{"ETSIIT", "ETSIIT primera"};
    static final String[] infoPrimera = new String[]{"ETSIIT", "ETSIIT primera"};

    //baja -> botones: 1
    static final String[] nomBaja = new String[]{"ETSIIT baja"};
    static final String[] infoBaja = new String[]{"ETSIIT baja"};

    int appWidth, appHeight;

    ImageIcon[] imgs;
    JFrame frame;
    int currentIndex=0;
    int currenBoton=0;

    infoPlanta[] botones;

    private void setImg(int pos) {
        // Obtener label y actualizar su imagen
        JLabel lbl = (JLabel)frame.getContentPane().getComponent(0);
        lbl.setIcon(imgs[pos]);

        // Actualizar titulo del frame
        frame.setTitle(imageTitulos[pos]);
        displayButtons(pos);


    }

    private void iniBotones(){
        botones=new infoPlanta[imageFilenames.length];

        infoPlanta completa=new infoPlanta(nomCompleta.length,nomCompleta, infoCompleta,frame);
        infoPlanta quinta=new infoPlanta(nomQuinta.length,nomQuinta,infoQuinta,frame);
        infoPlanta cuarta=new infoPlanta(nomCuarta.length, nomCuarta,infoCuarta,frame);
        infoPlanta tercera=new infoPlanta(nomTercera.length,nomTercera,infoTercera,frame);
        infoPlanta segunda=new infoPlanta(nomSegunda.length,nomSegunda, infoSegunda, frame);
        infoPlanta primera=new infoPlanta(nomPrimera.length, nomPrimera, infoPrimera, frame);
        infoPlanta baja=new infoPlanta(nomBaja.length,nomBaja, infoBaja,frame);

        botones[0]=completa;
        botones[1]=quinta;
        botones[2]=cuarta;
        botones[3]=tercera;
        botones[4]=segunda;
        botones[5]=primera;
        botones[6]=baja;


    }

    private void displayButtons(int pos){
        currenBoton=numbotones[pos];
        for(int i=0; i<currenBoton; i++){
            botones[currentIndex].getButton(i).setVisible(true);
            frame.add(botones[currentIndex].getButton(i));
        }

    }

    private void hidebuttons(){

        for(int i=0; i<currenBoton; i++){
            botones[currentIndex].getButton(i).setVisible(false);
        }
    }

    // Devuelve la imagen aumentada lo máximo posible de forma que quepa en la app y mantenga el mismo ratio
    private Image getScaledImg(BufferedImage img) {
        float imgRatio = (float)img.getWidth()/img.getHeight();
        float appRatio = (float)appWidth/appHeight;

        int resultWidth, resultHeight;
        if (imgRatio >= appRatio) {
            // La imagen es mas ancha que la pantalla
            resultWidth = appWidth;
            resultHeight = (int)(resultWidth/imgRatio);
        } else {
            // La imagen es mas alta que la pantalla
            resultHeight = appHeight;
            resultWidth = (int)(resultHeight*imgRatio);
        }

        return img.getScaledInstance(resultWidth, resultHeight, Image.SCALE_SMOOTH);
    }

    public DisplayImage()
    {
        // Obtenemos tamaño de la ventana maximizada. Incluye la cabecera, asi que nuestra app (y nuestras imagenes)
        // debe ser un poco más pequeña.
        Rectangle ventanaMaximizada = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        float ratio = 0.96f;
        appWidth = (int) (ventanaMaximizada.width * ratio);
        appHeight = (int) (ventanaMaximizada.height * ratio);

        // Cargar imagenes
        imgs = new ImageIcon[imageFilenames.length];
        for (int i = 0; i < imgs.length; i++) {
            try {
                BufferedImage img = ImageIO.read(new File(imageFilenames[i]));
                Image scaledImg = getScaledImg(img);
                imgs[i] = new ImageIcon(scaledImg);
            } catch (IOException e) {
                throw new RuntimeException("fallo al leer imagen " + imageFilenames[i] + ": " + e);
            }
        }



        // Crear frame
        frame = new JFrame();


        FlowLayout layout=new FlowLayout();




        frame.setLayout(layout);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        //frame.setExtendedState(JFrame.HIDE_ON_CLOSE);
        frame.add(new JLabel()); // modificado por setImg(pos)


        iniBotones();

        //displayButtons();


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // TODO: añadir rectangulos

        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent event) {
                super.keyPressed(event);
                char key = event.getKeyChar();
                if (key == '1') {
                    hidebuttons();
                    sube();

                } else if (key == '2') {
                    hidebuttons();
                    baja();

                }
            }
        });



        setImg(currentIndex);
    }


    public void sube()
    {
        currentIndex = Math.floorMod(currentIndex + 1, imgs.length);
        setImg(currentIndex);

    }

    public void baja()
    {
        currentIndex = Math.floorMod(currentIndex - 1, imgs.length);
        setImg(currentIndex);
    }
}