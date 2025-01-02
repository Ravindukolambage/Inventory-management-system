package InventoryManagement.Login;
import javax.swing.*;
import java.awt.*;

public class LoginTitlePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        g.drawString("Welcome",185, 35);
    }
}
