package InventoryManagement.Item;

import javax.swing.*;
import java.awt.*;

public class ItemInputPanel extends JPanel {
    private JLabel itemId, itemName, quantity, price, categoryId;
    private JTextField itemIdField, itemNameField, quantityField, priceField, categoryIdField;


    public ItemInputPanel() {
        setLayout(new GridLayout(5, 2, 6, 6));

        InitializeUI();

    }

    private void InitializeUI() {

        itemId = new JLabel("Item Id :");
        itemName = new JLabel("Item Name :");
        quantity = new JLabel("Quantity :");
        price = new JLabel("Price :");
        categoryId = new JLabel("Category ID :");

        itemIdField = new JTextField(12);
        itemIdField.setName("itemIdFieldName");

        itemNameField = new JTextField(12);
        itemNameField.setName("itemNameFieldName");

        quantityField = new JTextField(12);
        quantityField.setName("quantityFieldName");

        priceField = new JTextField(12);
        priceField.setName("priceFieldName");

        categoryIdField = new JTextField(12);
        categoryIdField.setName("categoryIdFieldName");

        add(itemId);
        add(itemIdField);
        add(itemName);
        add(itemNameField );
        add(quantity);
        add(quantityField);
        add(price);
        add(priceField);
        add(categoryId);
        add(categoryIdField);

    }

    //defined getters to get these values out


    public JTextField getItemIdField() {
        return itemIdField;
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getQuantityField() {
        return quantityField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getCategoryIDField() {
        return categoryIdField;
    }

}
