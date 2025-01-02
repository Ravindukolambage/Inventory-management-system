package InventoryManagement.Item;

import InventoryManagement.AddPicturePanel;
import InventoryManagement.Home.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class ItemMainScreen extends JFrame {
   private JPanel titleBar;
   private JPanel inputPanel;
   private JPanel tablePanel;
   private JPanel btnPanel;
    private JPanel picturePanel;
    private Connection connection;
    private Statement statement;
    private String connectionUrl = "jdbc:mysql://localhost:3308/inventorymanagementsystem";
    private JButton addBtn, deleteBtn, updateBtn, backbtn;


    public ItemMainScreen() throws HeadlessException {
        this("Add Item");
    }

    public ItemMainScreen(String title) throws HeadlessException{
        super(title);
        titleBar = new ItemTitlePanel();
        inputPanel = new ItemInputPanel();
        tablePanel = new ItemDataTable();
        picturePanel = new AddPicturePanel();

        addBtn = new JButton("Add");
        deleteBtn = new JButton("Delete");
        updateBtn = new JButton("Update");
        backbtn = new JButton("Back");

        IntializeUI();

    }

    private void IntializeUI(){
        Container container = getContentPane();
        titleBar.setBackground(Color.gray);
        titleBar.setBounds(350, 0, 653, 61);
        add(titleBar);

        picturePanel.setBackground(Color.WHITE);
        picturePanel.setBounds(0, -1, 350, 600);
        add(picturePanel);

        inputPanel.setBounds(400,100,420,220);
        add(inputPanel);

        tablePanel.setBounds(360,400,720,75);
        //dataTablePanel.setBackground(new Color(251, 249, 241));
        tablePanel.setSize(new Dimension(515,175));
        add(tablePanel);

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        addBtn.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        updateBtn.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        deleteBtn.setFont(new Font(Font.SERIF,Font.PLAIN,20));

        addBtn.setBackground(new Color(199, 200, 204));
        addBtn.setForeground(Color.black);
        updateBtn.setBackground(new Color(199, 200, 204));
        updateBtn.setForeground(Color.black);
        deleteBtn.setBackground(new Color(199, 200, 204));
        deleteBtn.setForeground(Color.black);

        addBtn.setPreferredSize(new Dimension(100,32));
        updateBtn.setPreferredSize(new Dimension(100,32));
        deleteBtn.setPreferredSize(new Dimension(100,32));

        btnPanel.setBounds(280,350,720,45);
        add(btnPanel);

        backbtn.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        backbtn.setBackground(new Color(238, 238, 238));
        backbtn.setForeground(Color.black);
        backbtn.setPreferredSize(new Dimension(100, 32));
        backbtn.setBounds(800, 68, 80, 25);
        add(backbtn);

        //Handling add event using anonymous class
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField itemNameTextField = new JTextField();
                JTextField quantityTextField = new JTextField();
                JTextField priceTextField = new JTextField();
                JTextField categoryIdTextField = new JTextField();

                //Created a component array
                //inside this array it have 4 components (two text fields and two labels)
                Component[] components = inputPanel.getComponents();
                for (Component cmp : components) {
                    if (cmp instanceof JTextField && cmp.getName().equals("itemNameFieldName")) {
                        itemNameTextField = (JTextField) cmp;
                    }
                    else if (cmp instanceof JTextField && cmp.getName().equals("quantityFieldName")) {
                        quantityTextField = (JTextField) cmp;
                    }
                    else if (cmp instanceof JTextField && cmp.getName().equals("priceFieldName")) {
                        priceTextField = (JTextField) cmp;
                    }
                    else if (cmp instanceof JTextField && cmp.getName().equals("categoryIdFieldName")) {
                        categoryIdTextField = (JTextField) cmp;
                    }
                }
                if (itemNameTextField.getText().isEmpty() || quantityTextField.getText().isEmpty() || priceTextField.getText().isEmpty() || categoryIdTextField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(btnPanel, "Both fields must be filled");
                } else {
                    System.out.println(itemNameTextField.getText());
                    System.out.println(quantityTextField.getText());
                    System.out.println(priceTextField.getText());
                    System.out.println(categoryIdTextField.getText());

                    try {
                        String ItemName = itemNameTextField.getText().trim();
                        String Quantity = quantityTextField.getText().trim();
                        String Price = priceTextField.getText().trim();
                        String CategoryId = categoryIdTextField.getText().trim();

                        ItemDatabaseOperations itemDatabaseOperations = new ItemDatabaseOperations();
                        itemDatabaseOperations.addItems(ItemName, Quantity, Price, CategoryId);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ItemIDToUpdate = JOptionPane.showInputDialog(btnPanel, "Enter Item Id to update:");

                if (ItemIDToUpdate != null && !ItemIDToUpdate.isEmpty()) {
                    try {
                        // Prompt the user to enter the updated information
                        String updateItemName = JOptionPane.showInputDialog(btnPanel, "Enter updated Item Name:");
                        String updateQuantity = JOptionPane.showInputDialog(btnPanel, "Enter updated Quantity:");
                        String updatePrice = JOptionPane.showInputDialog(btnPanel, "Enter updated Price:");
                        String updateCategoryId = JOptionPane.showInputDialog(btnPanel, "Enter updated CategoryId:");

                        connection = DriverManager.getConnection(connectionUrl, "root", "root");

                        // Construct the SQL UPDATE statement
                        String updateQuery = "UPDATE item SET ItemName = ?, Quantity = ?, Price = ?, CategoryId = ? WHERE ItemId = ? ";
                        // Prepare the SQL statement
                        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                        preparedStatement.setString(1, updateItemName);
                        preparedStatement.setString(2, updateQuantity);
                        preparedStatement.setString(3, updatePrice);
                        preparedStatement.setString(4, updateCategoryId);
                        preparedStatement.setString(5, ItemIDToUpdate);

                        // Execute the update statement
                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(container, "Updated successfully");
                            // Optionally, update the table display to reflect the changes
                            // You might need to reload the data or refresh the displayed row in the table
                        } else {
                            JOptionPane.showMessageDialog(container, "Failed to Update. Please check the Item ID.");
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
                String ItemIDToDelete = JOptionPane.showInputDialog(btnPanel, "Enter Item ID to delete:");

                if (ItemIDToDelete != null && !ItemIDToDelete.isEmpty()) {
                    try {
                        connection = DriverManager.getConnection(connectionUrl, "root", "root");

                        // Construct the SQL DELETE statement
                        String deleteQuery = "DELETE FROM item WHERE ItemId = ? ";

                        // Prepare the SQL statement
                        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                        preparedStatement.setString(1, ItemIDToDelete);

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

    public static ItemMainScreen itemDashboardScreen() {
        // Create and configure the dashboard screen
        ItemMainScreen dashboardScreen = new ItemMainScreen();
        // Configure the dashboard screen as needed
        return itemDashboardScreen();
    }

    public static void main(String[] args){

        new ItemMainScreen().setVisible(true);

    }
}
