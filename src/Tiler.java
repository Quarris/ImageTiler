import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiler extends JPanel {

    private BufferedImage image;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image == null) {
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            this.setBackground(null);
            this.setBorder(null);
            float scaleX = this.getWidth() / (float)image.getWidth();
            float scaleY = this.getHeight() / (float)image.getHeight();
            float scale = Math.min(scaleX, scaleY);
            int imgWidth = (int)(image.getWidth() * scale)/3;
            int imgHeight = (int)(image.getHeight() * scale)/3;
            for(int x = 0; x < 3; ++x) {
                for(int y = 0; y < 3; ++y) {
                    g.drawImage(this.image, this.getWidth() / 2 - 3 * imgWidth / 2 + imgWidth * x, this.getHeight() / 2 - 3 * imgHeight / 2 + imgHeight * y, imgWidth, imgHeight, null);
                }
            }
            g.setColor(Color.BLACK);
            g.drawRect(this.getWidth() / 2 - 3 * imgWidth / 2, this.getHeight() / 2 - 3 * imgHeight / 2, imgWidth * 3 - 1, imgHeight * 3 - 1);
            for (int i = 1; i <= 2; i++) {
                int lineX = this.getWidth() / 2 - 3 * imgWidth / 2 + imgWidth * i;
                int lineY = this.getHeight() / 2 - 3 * imgHeight / 2 + imgHeight * i;
                g.drawLine(this.getWidth() / 2 - 3 * imgWidth / 2, lineY, this.getWidth() / 2 + 3 * imgWidth / 2, lineY);
                g.drawLine(lineX, this.getHeight() / 2 - 3 * imgHeight / 2, lineX, this.getHeight() / 2 + 3 * imgHeight / 2);
            }
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void reload(String imagePath) {
        try {
            setImage(ImageIO.read(new File(imagePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
