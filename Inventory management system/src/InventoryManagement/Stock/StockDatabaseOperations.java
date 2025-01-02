package InventoryManagement.Stock;

import javax.swing.*;
import java.sql.*;

public class StockDatabaseOperations {
    private Connection connection;
    private Statement statement;
    private String connectionUrl = "jdbc:mysql://localhost:3308/inventorymanagementsystem";

    public StockDatabaseOperations() throws SQLException {
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

    public void addStockItem(String StockName,String Category,String Quantity) {
        String sql = "INSERT INTO stock(StockName,Category,Quantity) VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;


        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,StockName);
            preparedStatement.setString(2,Category);
            preparedStatement.setString(3,Quantity);

            int rowInserted = preparedStatement.executeUpdate();
            if(rowInserted >0)
            {
                JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }
}
