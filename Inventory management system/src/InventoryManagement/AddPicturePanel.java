package InventoryManagement;

import javax.swing.*;
import java.awt.*;

public class AddPicturePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        String url = "Inventory-Management.png";
        Image image = new ImageIcon(url).getImage();

        g.drawImage(image,0,150,350,300,null);
    }
}
