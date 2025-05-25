import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints; // For smooth image rendering
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel
{
    private Image backgroundImage;

    public BackgroundPanel(String imagePath)
    {
        try
        {
            backgroundImage = ImageIO.read(new File(imagePath));
        }
        catch (IOException e)
        {
            System.err.println("Could not load background image: " + e.getMessage());
            backgroundImage = null;
        }
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); // this clears the panel and prepares it for drawing

        if (backgroundImage != null)
        {
            Graphics2D g2d = (Graphics2D) g.create(); // this will draw background image
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // makes the image smooth.
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); //scaling the image

            g2d.setColor(new Color(255, 255, 255, 150));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
        else
        {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(66, 135, 245);
            Color color2 = new Color(240, 248, 255);
            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
            g2d.dispose();
        }
    }
}
