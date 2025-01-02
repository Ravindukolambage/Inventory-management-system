package InventoryManagement.Supplier;
import javax.swing.*;
import java.awt.*;

public class SupplierInputPanel extends JPanel {

    private JLabel supplierId, supplierName, contactNumber, address;
    private JTextField supplierIdField, supplierNameField, contactNumberField, addressField;


    public SupplierInputPanel() {
        setLayout(new GridLayout(7, 2, 6, 6));

        InitializeUI();

    }

    private void InitializeUI() {
        supplierId = new JLabel("Supplier Id :");
        supplierName = new JLabel("Supplier Name :");
        contactNumber = new JLabel("Contact Number :");
        address = new JLabel("Address :");

        supplierIdField = new JTextField(12);
        supplierIdField.setName("supplierIdFieldName");

        supplierNameField = new JTextField(12);
        supplierNameField.setName("supplierNameFieldName");

        addressField = new JTextField(12);
        addressField.setName("addressFieldName");

        contactNumberField = new JTextField(12);
        contactNumberField.setName("contactFieldName");


        add(supplierId);
        add(supplierIdField);
        add(supplierName); //row 1 col 1
        add(supplierNameField); //row 1 col2
        add(address); //row 1 col 1
        add(addressField); //row 1 col2
        add(contactNumber); //row 1 col 1
        add(contactNumberField); //row 1 col2

    }

    //defined getters to get these values out


    public JTextField getSupplierNameField() {
        return supplierNameField;
    }

    public JTextField getContactNumberField() {
        return contactNumberField;
    }

    public JTextField getAddressField() {
        return addressField;
    }
}




