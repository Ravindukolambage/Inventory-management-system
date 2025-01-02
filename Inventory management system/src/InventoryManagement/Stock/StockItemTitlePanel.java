package InventoryManagement.Stock;

import javax.swing.*;
import java.awt.*;

public class StockItemTitlePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        g.drawString("Add Stock Items",185, 35);
    }
}
