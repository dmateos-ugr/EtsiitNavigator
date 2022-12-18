import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

        // Crear frame
        frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        frame.add(new JLabel()); // modificado por setImg(pos)
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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