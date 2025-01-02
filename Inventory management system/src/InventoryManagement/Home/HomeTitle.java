package InventoryManagement.Home;

import javax.swing.*;
import java.awt.*;

public class HomeTitle extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        g.drawString("Home",185, 35);
    }
}
