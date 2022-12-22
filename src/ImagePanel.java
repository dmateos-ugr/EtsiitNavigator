import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {
    private final Dimension canvas, img;
    private final Image image;
    public ImagePanel(Image image, Dimension canvas, Dimension img) {
        this.image = image;
        this.canvas = canvas;
        this.img = img;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, canvas.width/2 - img.width/2, canvas.height/2 - img.height/2, img.width,img.height, this);
    }
}