package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Alexander Álvarez Marques
 * @date 2020-11-11
 * @version 0.1
 */
public class BackgroundImage extends JPanel {

    private BufferedImage backgroundImage;
    private boolean loadedImage = false;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    public void setImage(File selectedFile) {
        backgroundImage = scaleImage(selectedFile);
        repaint();
    }

    private BufferedImage scaleImage(File image) {

        try {
            Image img = ImageIO.read(image);
            img.getScaledInstance(1024, 1024, BufferedImage.SCALE_SMOOTH);
            loadedImage = true;
            return toBufferedImage(img);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while loading image.", "Load image error", JOptionPane.ERROR_MESSAGE);
            loadedImage = false;
            Logger.getLogger(BackgroundImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public boolean isImageLoaded() {
        return loadedImage;
    }

    /**
     * Converts a given BackgroundImage into a BufferedImage
     *
     * @param img The BackgroundImage to be converted
     * @return The converted BufferedImage
     */
    private static BufferedImage toBufferedImage(Image img) {

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics gr = bimage.createGraphics();
        gr.drawImage(img, 0, 0, null);
        gr.dispose();

        // Return the buffered image
        return bimage;
    }

}
