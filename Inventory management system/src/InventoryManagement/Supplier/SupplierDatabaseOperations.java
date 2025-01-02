package InventoryManagement.Supplier;

import javax.swing.*;
import java.sql.*;

public class SupplierDatabaseOperations {
    private Connection connection;
    private Statement statement;
    private String connectionUrl = "jdbc:mysql://localhost:3308/inventorymanagementsystem";

    public SupplierDatabaseOperations() throws SQLException {
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

    public void addSupplier(String SupName,String CNo,String Address) {
        String sql = "INSERT INTO supplier(SupName,CNo,Address) VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;

        SupplierInputPanel inputPanel = new SupplierInputPanel();

        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,SupName);
            preparedStatement.setString(2,CNo);
            preparedStatement.setString(3,Address);


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
