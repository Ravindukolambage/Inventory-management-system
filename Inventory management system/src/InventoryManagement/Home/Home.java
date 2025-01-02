package InventoryManagement.Home;

import InventoryManagement.*;
import InventoryManagement.Item.ItemMainScreen;
import InventoryManagement.Login.LoginPanel;
import InventoryManagement.Login.LoginTitlePanel;
import InventoryManagement.Stock.StockItemMainScreen;
import InventoryManagement.Supplier.SupplierMainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JPanel titleBar;
    private JPanel picturePanel;
    private JButton btnItem,btnStock,btnSupplier,logout;

    public Home() throws HeadlessException {
        this("Inventory Management System.Home");
    }
    public Home(String title) throws HeadlessException {
        super(title);
        titleBar = new HomeTitle();
        picturePanel = new AddPicturePanel();

        titleBar.setBackground(Color.gray);
        titleBar.setBounds(350, 0, 653, 61);
        add(titleBar);

        picturePanel.setBackground(Color.WHITE);
        picturePanel.setBounds(0, -1, 350, 600);
        add(picturePanel);

        btnItem = new JButton("Item");
        btnStock = new JButton("Stock");
        btnSupplier = new JButton("Supplier");
        logout = new JButton("Logout");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        btnItem.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        btnItem.setPreferredSize(new Dimension(250, 32));
        btnItem.setBackground(new Color(199, 200, 204));
        btnItem.setForeground(Color.black);
        btnItem.setBounds(410, 270, 120, 32); // Set button position and size
        add(btnItem);

        btnStock.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        btnStock.setPreferredSize(new Dimension(250, 32));
        btnStock.setBackground(new Color(199, 200, 204));
        btnStock.setForeground(Color.black);
        btnStock.setBounds(550, 270, 120, 32); // Set button position and size
        add(btnStock);

        btnSupplier.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        btnSupplier.setPreferredSize(new Dimension(250, 32));
        btnSupplier.setBackground(new Color(199, 200, 204));
        btnSupplier.setForeground(Color.black);
        btnSupplier.setBounds(690, 270, 120, 32); // Set button position and size
        add(btnSupplier);

        logout.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        logout.setBackground(new Color(238, 238, 238));
        logout.setForeground(Color.black);
        logout.setBorderPainted(false);
        logout.setPreferredSize(new Dimension(100, 32));
        logout.setBounds(780, 68, 120, 32);
        add(logout);

        btnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemMainScreen itemMainScreen = new ItemMainScreen();
                itemMainScreen.setVisible(true);
                dispose();
            }
        });
        btnStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockItemMainScreen stockItemMainScreen = new StockItemMainScreen();
                stockItemMainScreen.setVisible(true);
                dispose();
            }
        });
        btnSupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierMainScreen supplierMainScreen = new SupplierMainScreen();
                supplierMainScreen.setVisible(true);
                dispose();
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel loginPanel = new LoginPanel();
                loginPanel.setVisible(true);
                dispose();
            }
        });

    }

    public static Home homeDashboardScreen() {
        // Create and configure the dashboard screen
        Home dashboardScreen = new Home();
        // Configure the dashboard screen as needed
        return dashboardScreen;
    }

    public static void main(String[] args) {
        new Home().setVisible(true);
    }
}
