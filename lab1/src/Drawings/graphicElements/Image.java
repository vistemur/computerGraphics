package Drawings.graphicElements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image extends Rectangle {

    java.awt.Image image = null;

    public Image(String imagePath, int x, int y, int width, int height) {
        super(x, y, width, height);
        setImage(imagePath);
    }

    public void setImage(String imagePath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagePath));
            this.image = img;
        } catch (IOException e) {
            System.out.println("unable to load image from " + imagePath);
        }
        this.image = img;
    }

    protected void display(Graphics g) {
        java.awt.Rectangle rectangle = getPolygon().getBounds();
        if (image != null)
            g.drawImage(image, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
    }
}
