import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class FontManager
{
    public static final float SMALL = 12f;
    public static final float MEDIUM = 16f;
    public static final float LARGE = 20f;
    public static final float EXTRA_LARGE = 24f;

    private static String primaryFontFamily = "Arial";
    private static boolean fontsLoaded = false;

    public static void loadFonts()
    {
        if (fontsLoaded)
            return;

        try {
            Font sfPro = Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/SFPRODISPLAYMEDIUM.OTF"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sfPro);
            primaryFontFamily = sfPro.getFamily();
            fontsLoaded = true;
            System.out.println("SF Pro Display font loaded successfully: " + primaryFontFamily);
        }

        catch (IOException | FontFormatException e)
        {
            System.err.println("Error loading SF Pro Display font: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Font getPrimaryFont(float size, int style)
    {
        return new Font(primaryFontFamily, style, (int)size);
    }

    public static Font getPrimaryRegular(float size)
    {
        return getPrimaryFont(size, Font.PLAIN);
    }

    public static Font getPrimaryBold(float size)
    {
        return getPrimaryFont(size, Font.BOLD);
    }

    public static Font getPrimaryItalic(float size)
    {
        return getPrimaryFont(size, Font.ITALIC);
    }

    public static Font getSecondaryRegular(float size)
    {
        return getPrimaryRegular(size);
    }

    public static Font getSecondaryBold(float size)
    {
        return getPrimaryBold(size);
    }
}
// loadFonts() -> loads custom fonts
// getPrimaryFont() -> base font creation
// getPrimaryRegular -> comonly used
// getPrimaryBold() -> used in headings
