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

    int appWidth, appHeight;

    ImageIcon[] imgs;
    JFrame frame;
    int currentIndex=0;
    JButton b;

    popUpClass popupD, popupB, popupC;



    private void setImg(int pos) {
        // Obtener label y actualizar su imagen
        JLabel lbl = (JLabel)frame.getContentPane().getComponent(0);
        lbl.setIcon(imgs[pos]);

        // Actualizar titulo del frame
        frame.setTitle(imageTitulos[pos]);


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







        b=new JButton("B");
        JButton c=new JButton("C");
        JButton d=new JButton("D");

        /*
        b.setBounds(392,190,14,14);
        c.setBounds(154,461,14,14);
        d.setBounds(355,283,14,14);
         */

        popupB= new popUpClass("botonB");
        popupC= new popUpClass("botonC");
        popupD= new popUpClass("botonD");

        // Crear frame
        frame = new JFrame();




        frame.setLayout(new FlowLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        frame.add(new JLabel()); // modificado por setImg(pos)
        frame.add(b);
        frame.add(c);
        frame.add(d);
        //frame.add(jFrame);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        final int[] contador1 = {0};
        final int[] contador2 = {0};
        final int[] contador3 = {0};


        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador1[0] = contador1[0] +1;
                if((contador1[0]%2)==0){
                    popupB.cambiarVista(false);
                    popupB.cerrar(); //NO TOY SEGURA DE ESTO
                    frame.requestFocus();
                }else{
                    popupB.cambiarVista(true);
                    frame.requestFocus();
                }
            }
        });

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador2[0] = contador2[0] +1;
                if((contador2[0]%2)==0){
                    popupC.cambiarVista(false);
                    popupC.cerrar();
                    frame.requestFocus();
                }else{
                    popupC.cambiarVista(true);
                    frame.requestFocus();
                }
            }
        });
        d.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contador3[0] = contador3[0] +1;
                if((contador3[0]%2)==0){
                    popupD.cambiarVista(false);
                    popupD.cerrar();
                    frame.requestFocus();
                }else{
                    popupD.cambiarVista(true);
                    frame.requestFocus();
                }
            }
        });

        frame.requestFocus(); //IMPORTANTE PARA QUE CUANDO AÑADAMOS EL BOTON EL PROGRAMA SIGA HACIENDO CASO

        // TODO: añadir rectangulos

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                super.keyPressed(event);
                char key = event.getKeyChar();
                if (key == '1') {
                    sube();
                } else if (key == '2') {
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