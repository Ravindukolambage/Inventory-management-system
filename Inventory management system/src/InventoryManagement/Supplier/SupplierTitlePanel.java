package InventoryManagement.Supplier;

import javax.swing.*;
import java.awt.*;

public class SupplierTitlePanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
        g.drawString("Add Suppliers",185, 35);
    }

}
