package InventoryManagement.Item;
import javax.swing.*;
import java.sql.*;

public class ItemDatabaseOperations {
    private Connection connection;
    private Statement statement;
    private String connectionUrl = "jdbc:mysql://localhost:3308/inventorymanagementsystem";

    public ItemDatabaseOperations() throws SQLException {
        connect();
    }

    private void connect() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(connectionUrl, "root", "root");
            this.statement = this.connection.createStatement();
            System.out.println("Database connected...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItems(String ItemName, String Quantity, String Price, String CategoryId) {
        String sql = "INSERT INTO item(ItemName,Quantity,Price,CategoryId) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ItemName);
            preparedStatement.setString(2, Quantity);
            preparedStatement.setString(3, Price);
            preparedStatement.setString(4, CategoryId);


            int rowInserted = preparedStatement.executeUpdate();
            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}


