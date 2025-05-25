import java.awt.Image;

import javax.swing.ImageIcon;

public class IconManager
{
    // icons with its locations.
    public static final String MEMBER_ICON = "resources/member_icon.png";
    public static final String ADD_ICON = "resources/add_icon.png";
    public static final String UPDATE_ICON = "resources/update_icon.png";
    public static final String DELETE_ICON = "resources/delete_icon.png";
    public static final String CLEAR_ICON = "resources/clear_icon.png";
    public static final String PROFILE_ICON = "resources/profile_icon.png";
    public static final String APP_ICON = "resources/app_icon.png";

    public static ImageIcon getIcon(String path, int width, int height) {
        try
        {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        
        catch (Exception e)
        {
            System.err.println("Could not load icon: " + path);
            return new ImageIcon();
        }
    }
}
