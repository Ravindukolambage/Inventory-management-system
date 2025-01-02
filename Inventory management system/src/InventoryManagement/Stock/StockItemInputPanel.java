package InventoryManagement.Stock;

import javax.swing.*;
import java.awt.*;

public class StockItemInputPanel extends JPanel {
        private JLabel stockId, stockName, category, quantity;
        private JTextField stockIdField, stockNameField, categoryField, quantityField;


        public StockItemInputPanel() {
                setLayout(new GridLayout(5, 2, 6, 6));

                InitializeUI();

        }

        private void InitializeUI() {
                stockId = new JLabel("Stock Id :");
                stockName = new JLabel("Stock Name :");
                category = new JLabel("Category Number :");
                quantity = new JLabel("Quantity :");

                stockIdField = new JTextField(12);
                stockIdField.setName("stockIdFieldName");

                stockNameField = new JTextField(12);
                stockNameField.setName("stockNameFieldName");

                categoryField = new JTextField(12);
                categoryField.setName("categoryFieldName");

                quantityField = new JTextField(12);
                quantityField.setName("quantityFieldName");

                add(stockId);
                add(stockIdField);
                add(stockName); //row 1 col 1
                add(stockNameField); //row 1 col2
                add(category); //row 1 col 1
                add(categoryField); //row 1 col2
                add(quantity); //row 1 col 1
                add(quantityField); //row 1 col2

        }

        //defined getters to get these values out


        public JTextField getStockNameField() {
                return stockNameField;
        }

        public JTextField getCategoryField() {
                return categoryField;
        }

        public JTextField getQuantityField() {
                return quantityField;
        }
}
