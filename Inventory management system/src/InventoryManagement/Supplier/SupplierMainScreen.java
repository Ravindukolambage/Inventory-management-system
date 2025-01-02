package InventoryManagement.Supplier;


import InventoryManagement.AddPicturePanel;
import InventoryManagement.Home.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SupplierMainScreen extends JFrame {

    private JPanel titleBar;
    private JPanel inputPanel;
    private JPanel tablePanel;
    private JPanel btnPanel;
    private JPanel picturePanel;
    private Connection connection;
    private Statement statement;
    private String connectionUrl = "jdbc:mysql://localhost:3308/inventorymanagementsystem";
    private JButton addBtn, deleteBtn, updateBtn, backbtn;

    public SupplierMainScreen() throws HeadlessException {
        this("Add Supplier");
    }

    public SupplierMainScreen(String title) throws HeadlessException{
        super(title);
        titleBar = new SupplierTitlePanel();
        inputPanel = new SupplierInputPanel();
        tablePanel = new SupplierDataTable();
        picturePanel = new AddPicturePanel();

        addBtn = new JButton("Add");
        deleteBtn = new JButton("Delete");
        updateBtn = new JButton("Update");
        backbtn = new JButton("Back");

        IntializeUI();

    }

    private void IntializeUI() {
        Container container = getContentPane();
        titleBar.setBackground(Color.gray);
        titleBar.setBounds(350, 0, 653, 61);
        add(titleBar);

        picturePanel.setBackground(Color.WHITE);
        picturePanel.setBounds(0, -1, 350, 600);
        add(picturePanel);

        inputPanel.setBounds(400, 100, 420, 220);
        add(inputPanel);

        tablePanel.setBounds(360, 400, 720, 75);
        //dataTablePanel.setBackground(new Color(251, 249, 241));
        tablePanel.setSize(new Dimension(515, 175));
        add(tablePanel);

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        addBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        updateBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        deleteBtn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        addBtn.setBackground(new Color(199, 200, 204));
        addBtn.setForeground(Color.black);
        updateBtn.setBackground(new Color(199, 200, 204));
        updateBtn.setForeground(Color.black);
        deleteBtn.setBackground(new Color(199, 200, 204));
        deleteBtn.setForeground(Color.black);

        addBtn.setPreferredSize(new Dimension(100, 32));
        updateBtn.setPreferredSize(new Dimension(100, 32));
        deleteBtn.setPreferredSize(new Dimension(100, 32));

        btnPanel.setBounds(280, 350, 720, 45);
        add(btnPanel);

        backbtn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        backbtn.setBackground(new Color(238, 238, 238));
        backbtn.setForeground(Color.black);
        backbtn.setPreferredSize(new Dimension(100, 32));
        backbtn.setBounds(780, 68, 80, 25);
        add(backbtn);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField supplierNameTextField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField contactTextField = new JTextField();

                //Created a component array
                Component[] components = inputPanel.getComponents();
                for (Component cmp : components) {
                    if (cmp instanceof JTextField && cmp.getName().equals("supplierNameFieldName")) {
                        supplierNameTextField = (JTextField) cmp;
                    }
                    else if (cmp instanceof JTextField && cmp.getName().equals("addressFieldName")) {
                        addressField = (JTextField) cmp;
                    }
                    else if (cmp instanceof JTextField && cmp.getName().equals("contactFieldName")) {
                        contactTextField = (JTextField) cmp;
                    }
                }
                if (supplierNameTextField.getText().isEmpty() || contactTextField.getText().isEmpty() || addressField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(btnPanel, "Both fields must be filled");
                } else {
                    System.out.println(supplierNameTextField.getText());
                    System.out.println(contactTextField.getText());
                    System.out.println(addressField.getText());

                    try {
                        String SupName = supplierNameTextField.getText().trim();
                        String CNo = contactTextField.getText().trim();
                        String Address = addressField.getText().trim();

                        SupplierDatabaseOperations supplierDatabaseOperations = new SupplierDatabaseOperations();
                        supplierDatabaseOperations.addSupplier(SupName,CNo,Address);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }


            }
        });

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierIDToUpdate = JOptionPane.showInputDialog(btnPanel, "Enter Supplier ID to update:");

                if (supplierIDToUpdate != null && !supplierIDToUpdate.isEmpty()) {
                    try {
                        // Prompt the user to enter the updated information
                        String updateSupplierName = JOptionPane.showInputDialog(btnPanel, "Enter updated Supplier Name:");
                        String updateContactNumber = JOptionPane.showInputDialog(btnPanel, "Enter updated Contact Number:");
                        String updateAddress = JOptionPane.showInputDialog(btnPanel, "Enter updated Address:");

                        connection = DriverManager.getConnection(connectionUrl, "root", "root");

                        // Construct the SQL UPDATE statement
                        String updateQuery = "UPDATE supplier SET SupName = ?, CNo = ?, Address = ? WHERE SupId = ? ";

                        // Prepare the SQL statement
                        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                        preparedStatement.setString(1, updateSupplierName);
                        preparedStatement.setString(2, updateContactNumber);
                        preparedStatement.setString(3, updateAddress);
                        preparedStatement.setString(4, supplierIDToUpdate);


                        // Execute the update statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(container, "Updated successfully");
                            // Optionally, update the table display to reflect the changes
                            // You might need to reload the data or refresh the displayed row in the table
                        } else {
                            JOptionPane.showMessageDialog(container, "Failed to Update. Please check the InventoryManagement.Supplier ID.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(container, "Error occurred: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        // Delete button event handling
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the Route ID to delete
                String SupIDToDelete = JOptionPane.showInputDialog(btnPanel, "Enter Stock ID to delete:");

                if (SupIDToDelete != null && !SupIDToDelete.isEmpty()) {
                    try {
                        connection = DriverManager.getConnection(connectionUrl, "root", "root");

                        // Construct the SQL DELETE statement
                        String deleteQuery = "DELETE FROM Supplier WHERE SupId = ? ";

                        // Prepare the SQL statement
                        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                        preparedStatement.setString(1, SupIDToDelete);

                        // Execute the delete statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(container, "Deleted Successfully");
                            // Optionally, update the table display to reflect the changes
                            // You might need to reload the data or refresh the displayed row in the table
                        } else {
                            JOptionPane.showMessageDialog(container, "Failed to Delete. Please check the Route ID.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(container, "Error occurred: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        });

        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home();
                home.setVisible(true);
                dispose();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 620);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public static SupplierMainScreen supplierDashboardScreen() {
        // Create and configure the dashboard screen
        SupplierMainScreen dashboardScreen = new SupplierMainScreen();
        // Configure the dashboard screen as needed
        return supplierDashboardScreen();
    }

    public static void main(String[] args){

        new SupplierMainScreen().setVisible(true);

    }

}







