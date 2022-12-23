import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class DisplayImage {

    private int resultWidth, resultHeight;

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

    // Ojo que estos tienen que tener el mismo orden que en PlantasCompletas
    static final String[] imageFilenames = new String[]{
            "Planos/sotano.jpg","Planos/baja.jpg", "Planos/primera.jpg", "Planos/segunda.jpg", "Planos/tercera.jpg",
            "Planos/cuarta.jpg", "Planos/quinta.jpg", "Planos/completa.jpg",
    };
    static final String[] imageTitulos = new String[]{
            "Plano ETSIIT sotano","Plano ETSIIT planta baja", "Plano ETSIIT primera planta", "Plano ETSIIT segunda planta", "Plano ETSIIT tercera planta",
            "Plano ETSIIT cuarta planta",  "Plano ETSIIT quinta planta", "Plano ETSIIT completo",

    };

    int appWidth, appHeight;
    JFrame frame;
    JPanel cards;
    int currentIndex=0;

    private void setPlanta(int pos) throws IOException {
        currentIndex = pos;
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, String.valueOf(pos));
        // Actualizar titulo del frame
        frame.setTitle(imageTitulos[pos]);
    }

    public static Dimension getRatio(Dimension app, Dimension img){
        float imgRatio = (float)img.width/img.height;
        float appRatio = (float)app.width/app.height;
        int width, height;
        if (imgRatio >= appRatio) {
            // La imagen es mas ancha que la pantalla
            width = app.width;
            height = (int)(width / imgRatio);
        } else {
            // La imagen es mas alta que la pantalla
            height = app.height;
            width = (int)(app.height * imgRatio);
        }
        return new Dimension(width,height);
    }

    // Devuelve la imagen aumentada lo máximo posible de forma que quepa en la app y mantenga el mismo ratio
    private Image getScaledImg(BufferedImage img) {
        Dimension temp = getRatio(new Dimension(appWidth,appHeight),new Dimension(img.getWidth(),img.getHeight()));
        resultHeight = temp.height;
        resultWidth = temp.width;
        return img.getScaledInstance(temp.width, temp.height, Image.SCALE_SMOOTH);
    }

    public DisplayImage() throws IOException {
        // Obtenemos tamaño de la ventana maximizada. Incluye la cabecera, asi que nuestra app (y nuestras imagenes)
        // debe ser un poco más pequeña.
        Rectangle ventanaMaximizada = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        float ratio = 0.96f;
        appWidth = (int) (ventanaMaximizada.width * ratio);
        appHeight = (int) (ventanaMaximizada.height * ratio);
        Planta._canvas = new Dimension(appWidth,appHeight);

        frame = new JFrame();
        cards = new JPanel(new CardLayout());

        for(int i=0;i<imageFilenames.length;++i){
            BufferedImage img = ImageIO.read(new File(imageFilenames[i]));
            Dimension _old = new Dimension(img.getWidth(),img.getHeight());
            Image scaledImg = getScaledImg(img);
            Dimension _img = new Dimension(resultWidth,resultHeight);
            ImagePanel plantaPanel = PlantasCompletas.getPlanta(i,scaledImg,_img,_old).getImagePanel();
            plantaPanel.setName(String.valueOf(i));
            cards.add(plantaPanel, String.valueOf(i));
        }
        frame.add(cards,BorderLayout.CENTER);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // ventana maximizada
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
        setPlanta(7); // completa
    }

    private ImagePanel getCurrentCard() {
        for (Component c : cards.getComponents()) {
            if (c.getName().equals(String.valueOf(currentIndex))) {
                return (ImagePanel)c;
            }
        }
        return null;
    }

    private void desactivarTimers() {
        ImagePanel panel = getCurrentCard();
        for (Component c : panel.getComponents()) {
            if (c.getClass() == HoverButton.class) {
                HoverButton btn = (HoverButton)c;
                btn.stopTimer();
            }
        }
    }

    public void sube()
    {
        desactivarTimers();
        currentIndex = Math.floorMod(currentIndex + 1, imageFilenames.length);
        try {
            setPlanta(currentIndex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void baja()
    {
        desactivarTimers();
        currentIndex = Math.floorMod(currentIndex - 1, imageFilenames.length);
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