package InventoryManagement.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ItemDataTable extends JPanel {
    private JTable table;
    public ItemDataTable() {

        setLayout(new BorderLayout());
        // Create a DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Set column names
        model.setColumnIdentifiers(new Object[]{"ItemId","ItemName", "Quantity", "Price", "CategoryId"});

        // Retrieve data from the database
        fetchDataFromDatabase(model);

        // Create the JTable with the DefaultTableModel
        table = new JTable(model);
        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the JScrollPane to the frame
        add(scrollPane,BorderLayout.CENTER);
        // Display the frame
        setVisible(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private void fetchDataFromDatabase(DefaultTableModel model) {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3308/inventorymanagementsystem";
        String username = "root";
        String password = "root";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query to retrieve data
            ResultSet rs = statement.executeQuery("SELECT * FROM item");

            // Iterate through the result set and add data to the model
            while (rs.next()) {

                String itemId;
                String itemName;
                String quantity;
                String price;
                String categoryId;

                model.addRow(new Object[]{
                        itemId = rs.getString("ItemId"),
                        itemName = rs.getString("ItemName"),
                        quantity = rs.getString("Quantity"),
                        price = rs.getString("Price"),
                        categoryId = rs.getString("CategoryId"),
                });
            }

            // Close the resources
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
     }

}

}
