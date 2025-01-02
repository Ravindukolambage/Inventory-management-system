package InventoryManagement.Login;

import InventoryManagement.AddPicturePanel;
import InventoryManagement.Home.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPanel extends JFrame {

    private JPanel titleBar;
    private JPanel picturePanel;
    private JButton btnLogin;
    private JPasswordField textPassWord;
    private JTextField textUserName;

    public LoginPanel() throws HeadlessException {
        this("Login");
    }

    public LoginPanel(String title) throws HeadlessException{
        super(title);
        titleBar = new LoginTitlePanel();
        picturePanel = new AddPicturePanel();

        titleBar.setBackground(Color.gray);
        titleBar.setBounds(350, 0, 653, 61);
        add(titleBar);

        picturePanel.setBackground(Color.WHITE);
        picturePanel.setBounds(0, -1, 350, 600);
        add(picturePanel);

        btnLogin = new JButton("Login");

        JLabel username = new JLabel("User Name : ");
        username.setBounds(420, 210, 140, 30);
        add(username);

        textUserName = new JTextField();
        textUserName.setBounds(570, 210, 250, 30);
        add(textUserName);

        JLabel passWord = new JLabel("Password : ");
        passWord.setBounds(420, 260, 140, 30);
        add(passWord);

        textPassWord = new JPasswordField();
        textPassWord.setBounds(570, 260, 250, 30);
        add(textPassWord);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(900, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        //Add button to the frame
        btnLogin.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        btnLogin.setPreferredSize(new Dimension(250, 32));
        btnLogin.setBackground(new Color(199, 200, 204));
        btnLogin.setForeground(Color.black);
        btnLogin.setBounds(570, 370, 100, 32); // Set button position and size
        add(btnLogin); // Add button to the frame

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {
                    try {
                        String username = textUserName.getText();
                        String password = new String(textPassWord.getPassword());

                        // Establish database connection
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/inventorymanagementsystem", "root", "root");

                        String query = "SELECT * FROM admininventory WHERE UserName = ? AND APassword = ? ";

                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "Login successful");
                            openHome();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                        }

                        preparedStatement.close();
                        conn.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void openHome() {
        // Open the patient screen
        Home home = new Home();
        home.setVisible(true);

        // Close the login screen
        dispose();
    }
    public static void main(String[] args){

        new LoginPanel().setVisible(true);

    }

}
