import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Start
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            FontManager.loadFonts();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
