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

    //num de botones que tiene cada planta
    static final int[] numbotones = new int[]{2, 1, 3, 3, 2, 2, 1};

    int appWidth, appHeight;
    Image[] imgs;
    JFrame frame;
    int currentIndex=0;
    int currenBoton=0;

    private int resultWidth, resultHeight;

    private void setPlanta(int pos) throws IOException {
        // Obtener label y actualizar su imagen
        Component comp = frame.getContentPane().getComponent(0);
        BufferedImage img = ImageIO.read(new File(imageFilenames[currentIndex]));
        Image scaledImg = getScaledImg(img);
        comp = PlantasCompletas.getPlanta(currentIndex,scaledImg).getImagePanel();

        // Actualizar titulo del frame
        frame.setTitle(imageTitulos[pos]);
    }


    // Devuelve la imagen aumentada lo máximo posible de forma que quepa en la app y mantenga el mismo ratio
    private Image getScaledImg(BufferedImage img) {
        float imgRatio = (float)img.getWidth()/img.getHeight();
        float appRatio = (float)appWidth/appHeight;

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

    public DisplayImage() throws IOException {
        // Obtenemos tamaño de la ventana maximizada. Incluye la cabecera, asi que nuestra app (y nuestras imagenes)
        // debe ser un poco más pequeña.
        Rectangle ventanaMaximizada = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        float ratio = 0.96f;
        appWidth = (int) (ventanaMaximizada.width * ratio);
        appHeight = (int) (ventanaMaximizada.height * ratio);

        frame = new JFrame();

        BufferedImage img = ImageIO.read(new File(imageFilenames[0]));
        Image scaledImg = getScaledImg(img);
        ImagePanel default_panel = PlantasCompletas.getPlanta(0,scaledImg).getImagePanel();
        default_panel.setVisible(true);

        frame.add(default_panel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
    }

    public void sube()
    {
        currentIndex = Math.floorMod(currentIndex + 1, imgs.length);
        try {
            setPlanta(currentIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void baja()
    {
        currentIndex = Math.floorMod(currentIndex - 1, imgs.length);
        try {
            setPlanta(currentIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}