import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class DisplayImage {

    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if(e.getKeyChar() == '1' && e.getID() == KeyEvent.KEY_PRESSED){
                sube();
            }else if(e.getKeyChar() == '2' && e.getID() == KeyEvent.KEY_PRESSED){
                baja();
            }else if(e.getKeyChar() == 'd' && e.getID() == KeyEvent.KEY_PRESSED){
                hidepopups();
            }
            return false;
        }
    }

    static final String[] imageFilenames = new String[]{
            "Planos/completa.jpg", "Planos/quinta.jpg", "Planos/cuarta.jpg", "Planos/tercera.jpg",
            "Planos/segunda.jpg", "Planos/primera.jpg", "Planos/baja.jpg"
    };
    static final String[] imageTitulos = new String[]{
            "Plano ETSIIT completo", "Plano ETSIIT quinta planta", "Plano ETSIIT cuarta planta", "Plano ETSIIT tercera planta",
            "Plano ETSIIT segunda planta", "Plano ETSIIT primera planta", "Plano ETSIIT planta baja",
    };

    int appWidth, appHeight;
    JFrame frame;
    JPanel cards;
    int num_imgs = 7;
    int currentIndex=0;

    private void setPlanta(int pos) throws IOException {
        currentIndex = pos;
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, String.valueOf(pos));
        // Actualizar titulo del frame
        frame.setTitle(imageTitulos[pos]);
    }


    // Devuelve la imagen aumentada lo máximo posible de forma que quepa en la app y mantenga el mismo ratio
    private Image getScaledImg(BufferedImage img) {
        float imgRatio = (float)img.getWidth()/img.getHeight();
        float appRatio = (float)appWidth/appHeight;

        int resultWidth;
        int resultHeight;
        if (imgRatio >= appRatio) {
            // La imagen es mas ancha que la pantalla
            resultWidth = appWidth;
            resultHeight = (int)(resultWidth /imgRatio);
        } else {
            // La imagen es mas alta que la pantalla
            resultHeight = appHeight;
            resultWidth = (int)(resultHeight *imgRatio);
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
        cards = new JPanel(new CardLayout());

        for(int i=0;i<num_imgs;++i){
            BufferedImage img = ImageIO.read(new File(imageFilenames[i]));
            Image scaledImg = getScaledImg(img);
            cards.add(PlantasCompletas.getPlanta(i,scaledImg).getImagePanel(),String.valueOf(i));
        }

        frame.add(cards,BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }

    public void sube()
    {
        currentIndex = Math.floorMod(currentIndex + 1, 6);
        try {
            setPlanta(currentIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void baja()
    {
        currentIndex = Math.floorMod(currentIndex - 1, 6);
        try {
            setPlanta(currentIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hidepopups(){
        JOptionPane.getRootFrame().dispose();
    }

}