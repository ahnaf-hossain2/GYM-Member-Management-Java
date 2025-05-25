import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GlassPanel extends JPanel
{
    public GlassPanel()
    {
        setOpaque(false); // Transparency.
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // top, left, bottom, right -> invisible border
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // glass effect with rounded corner
        g2d.setColor(new Color(255, 255, 255, 220)); // last one is alpha value for transparency level. low = more transparent.
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        // Border part of the glass
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        // Shadow effect
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.drawRoundRect(2, 2, getWidth() - 1, getHeight() - 1, 20, 20);
        g2d.dispose();
    }
}
