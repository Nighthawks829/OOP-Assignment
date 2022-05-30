import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;

public class Gui {
    Record record = new Record("R0001", "10/10/10", 0, 0, 0.6);

    JFrame mainFrame;
    JTable productTable;
    // JComboBox<String> productComboBox;
    ButtonGroup payMethodButtonGroup;

    JTextField customerNameTextField;
    JTextField customerIdTextField;
    JTextField ageTextField;
    JTextField phoneNumberTextField;
    JTextField customerAddressTextField;

    JComboBox<Product> productComboBox;
    JSpinner productSpinner;
    Product product1 = new Product("A001", "Product 1 Name", 10.00);
    Product product2 = new Product("A002", "Product 2 Name", 20.00);
    Product product3 = new Product("A003", "Product 3 Name", 30.00);
    Product product4 = new Product("A004", "Product 4 Name", 40.00);
    Product product5 = new Product("A005", "Product 5 Name", 50.00);

    Product productListObject[] = { product1, product2, product3, product4, product5 };

    JLabel totalAfterTaxLabel;
    JLabel totalBeforeTaxLabel;

    JLabel cardNumberLabel;
    JTextField cardNumberTextField;
    JLabel expireDateLabel;
    JTextField expireDateTextField;
    JLabel ccvLabel;
    JTextField ccveTextField;

    JLabel payAmountLabel;
    JTextField payAmountTextField;

    public Gui() {
    }

    public void display() {
        mainFrame = new JFrame("Point-of-Sales System");

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.RED);
        titlePanel.setLayout(null);
        titlePanel.setBounds(3, 0, 630, 150);
        titlePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), ""));

        JLabel titleLabel = new JLabel("ABC Supermarket");
        titleLabel.setBounds(250, 20, 500, 30);
        titleLabel.setFont(new Font("Ubuntu", Font.BOLD, 14));
        titlePanel.add(titleLabel);

        JLabel addressLabel = new JLabel("No 100, Jalan ABC, Taman XYZ, 20000, Kuala Lumpuer, Malaysia");
        addressLabel.setBounds(100, 50, 500, 30);
        addressLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        titlePanel.add(addressLabel);

        JLabel dateLabel = new JLabel("Date: 10/10/2022");
        dateLabel.setBounds(250, 70, 500, 30);
        dateLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        titlePanel.add(dateLabel);

        JLabel recordIdLabel = new JLabel("Record ID: A01234");
        recordIdLabel.setBounds(245, 90, 500, 30);
        recordIdLabel.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        titlePanel.add(recordIdLabel);

        mainFrame.add(titlePanel);

        JPanel customerPanel = new JPanel();
        customerPanel.setBackground(Color.GREEN);
        customerPanel.setBounds(3, 150, 630, 150);
        customerPanel.setLayout(null);
        customerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Customer Information"));

        JLabel customerIdLabel = new JLabel("Customer ID");
        customerIdLabel.setBounds(30, 20, 300, 30);
        customerPanel.add(customerIdLabel);

        customerIdTextField = new JTextField();
        customerIdTextField.setBounds(130, 25, 100, 20);
        customerPanel.add(customerIdTextField);

        JLabel customerNameLabel = new JLabel("Customer Name");
        customerNameLabel.setBounds(280, 20, 300, 30);
        customerPanel.add(customerNameLabel);

        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(400, 25, 150, 20);
        customerPanel.add(customerNameTextField);

        JLabel ageLabel = new JLabel("Customer Age");
        ageLabel.setBounds(30, 50, 300, 30);
        customerPanel.add(ageLabel);

        ageTextField = new JTextField();
        ageTextField.setBounds(130, 55, 50, 20);
        customerPanel.add(ageTextField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(280, 50, 300, 30);
        customerPanel.add(phoneNumberLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(400, 55, 150, 20);
        customerPanel.add(phoneNumberTextField);

        JLabel customerAddressLabel = new JLabel("Address:");
        customerAddressLabel.setBounds(30, 80, 300, 30);
        customerPanel.add(customerAddressLabel);

        customerAddressTextField = new JTextField();
        customerAddressTextField.setBounds(130, 85, 420, 20);
        customerPanel.add(customerAddressTextField);

        mainFrame.add(customerPanel);

        JPanel productPanel = new JPanel();
        productPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Product"));
        productPanel.setBackground(Color.YELLOW);
        productPanel.setBounds(3, 300, 630, 250);
        productPanel.setLayout(null);

        JLabel selectProductLabel = new JLabel("Select Product");
        selectProductLabel.setBounds(30, 20, 300, 30);
        productPanel.add(selectProductLabel);

        String productList[] = { "Product1", "Product2", "Product3", "Product4", "Product5" };
        // productComboBox = new JComboBox<Product>(productListObject);
        productComboBox = new JComboBox<Product>(productListObject);
        productComboBox.setBounds(130, 25, 200, 20);
        productPanel.add(productComboBox);

        SpinnerModel value = new SpinnerNumberModel(1, 1, 100, 1);
        productSpinner = new JSpinner(value);
        productSpinner.setBounds(360, 25, 50, 20);
        productPanel.add(productSpinner);

        JButton addProductButton = new JButton("Add Product");
        addProductButton.setBounds(460, 25, 150, 20);
        addProductButton.addActionListener(new addProductButton());
        productPanel.add(addProductButton);

        String data[][] = {};
        String tableColumns[] = { "Product ID", "Product Name", "Unit Price", "Quantity", "Total" };
        // JTable productTable = new JTable(data,tableColumns);

        productTable = new JTable();
        productTable.setModel(new DefaultTableModel(data, tableColumns) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        productTable.getColumnModel().getColumn(0).setResizable(false);
        productTable.getColumnModel().getColumn(1).setResizable(false);
        productTable.getColumnModel().getColumn(2).setResizable(false);
        productTable.getColumnModel().getColumn(3).setResizable(false);
        productTable.getColumnModel().getColumn(4).setResizable(false);
        productTable.getTableHeader().setReorderingAllowed(false);

        // productTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        // productTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(0);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(0);
        productTable.getColumnModel().getColumn(4).setPreferredWidth(0);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        productTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        productTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JScrollPane productTableScrollPane = new JScrollPane(productTable);

        productTableScrollPane.setBounds(30, 70, 550, 120);
        productPanel.add(productTableScrollPane);

        totalBeforeTaxLabel = new JLabel("Total Before Tax: RM10.00");
        totalBeforeTaxLabel.setBounds(420, 200, 300, 30);
        productPanel.add(totalBeforeTaxLabel);

        totalAfterTaxLabel = new JLabel("Total After Tax (6%): RM10.00");
        totalAfterTaxLabel.setBounds(405, 220, 300, 30);
        productPanel.add(totalAfterTaxLabel);

        mainFrame.add(productPanel);

        JPanel payPanel = new JPanel();
        payPanel.setBackground(Color.PINK);
        payPanel.setBounds(3, 550, 630, 220);
        payPanel.setLayout(null);
        payPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Payment"));

        JRadioButton creditCardRadioButton = new JRadioButton("Credit Card");
        creditCardRadioButton.setBounds(30, 30, 100, 30);
        payPanel.add(creditCardRadioButton);

        JRadioButton debitCardRadioButton = new JRadioButton("Debit Card");
        debitCardRadioButton.setBounds(30, 60, 100, 30);
        payPanel.add(debitCardRadioButton);

        JRadioButton cashRadioButton = new JRadioButton("Cash");
        cashRadioButton.setBounds(30, 90, 100, 30);
        payPanel.add(cashRadioButton);

        payMethodButtonGroup = new ButtonGroup();
        payMethodButtonGroup.add(creditCardRadioButton);
        payMethodButtonGroup.add(debitCardRadioButton);
        payMethodButtonGroup.add(cashRadioButton);
        creditCardRadioButton.setActionCommand("creditCard");
        debitCardRadioButton.setActionCommand("debitCard");
        cashRadioButton.setActionCommand("cash");

        creditCardRadioButton.addActionListener(new creditCardListsner());
        debitCardRadioButton.addActionListener(new debitCardListener());
        cashRadioButton.addActionListener(new cashListener());

        cardNumberLabel = new JLabel("Card Number");
        cardNumberLabel.setBounds(180, 30, 300, 30);
        cardNumberLabel.setVisible(false);
        payPanel.add(cardNumberLabel);

        cardNumberTextField = new JTextField();
        cardNumberTextField.setBounds(280, 35, 150, 20);
        cardNumberTextField.setVisible(false);
        payPanel.add(cardNumberTextField);

        payAmountLabel = new JLabel("Pay Amount");
        payAmountLabel.setBounds(180, 30, 300, 30);
        payAmountLabel.setVisible(false);
        payPanel.add(payAmountLabel);

        payAmountTextField = new JTextField();
        payAmountTextField.setBounds(280, 35, 150, 20);
        payAmountTextField.setVisible(false);
        payPanel.add(payAmountTextField);

        expireDateLabel = new JLabel("Expire Date");
        expireDateLabel.setBounds(180, 60, 300, 30);
        expireDateLabel.setVisible(false);
        payPanel.add(expireDateLabel);

        expireDateTextField = new JTextField();
        expireDateTextField.setBounds(280, 65, 150, 20);
        expireDateTextField.setVisible(false);
        payPanel.add(expireDateTextField);

        ccvLabel = new JLabel("CCV");
        ccvLabel.setBounds(180, 90, 300, 30);
        ccvLabel.setVisible(false);
        payPanel.add(ccvLabel);

        ccveTextField = new JTextField();
        ccveTextField.setBounds(280, 95, 150, 20);
        ccveTextField.setVisible(false);
        payPanel.add(ccveTextField);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(250, 145, 150, 25);
        payButton.addActionListener(new payButtonListener());
        payPanel.add(payButton);

        JLabel payResultLabel = new JLabel("Pay Succseefully: Change is RM5.00");
        payResultLabel.setBounds(100, 175, 300, 25);
        payPanel.add(payResultLabel);

        JButton printReceiptButton = new JButton("Print Receipt");
        printReceiptButton.setBounds(350, 175, 150, 25);
        payPanel.add(printReceiptButton);
        printReceiptButton.addActionListener(new printReceiptButtonListener());

        mainFrame.add(payPanel);

        mainFrame.setSize(650, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }

    class addProductButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product selectedProduct = (Product) productComboBox.getSelectedItem();

            String productId = selectedProduct.getId();
            String name = selectedProduct.getName();
            double price = selectedProduct.getPrice();

            int quantity = (Integer) productSpinner.getValue();
            double total = quantity * price;

            System.out.println(productId);
            System.out.println("Name" + name);
            System.out.println("Quantity:" + quantity);
            System.out.println("Total" + total);

            Object[] row = { productId, name, "RM" + price, quantity, "RM" + total };
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.addRow(row);

            // record.addProduct(selectedProduct);

            record.calculateTotal(selectedProduct, quantity);
            totalBeforeTaxLabel.setText("Total Before Tax: RM" + String.valueOf(record.getTotalBeforeTax()));
            totalAfterTaxLabel.setText(
                    "Total After Tax (6%): RM" + String.valueOf(Math.round(record.getTotalAfterTax() * 100.0) / 100.0));
        }
    }

    class payButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String paymentMethod = payMethodButtonGroup.getSelection().getActionCommand();
            String cardNumber = "";
            String expireDate = "";
            String ccv = "";
            double cash = 0.0;
            if (paymentMethod == "creditCard") {
                cardNumber = cardNumberTextField.getText();
                expireDate = expireDateTextField.getText();
                ccv = ccveTextField.getText();
                record.setCreditCard(cardNumber, expireDate, ccv);
            }
            if (paymentMethod == "debitCard") {
                cardNumber = cardNumberTextField.getText();
                expireDate = expireDateTextField.getText();
                ccv = ccveTextField.getText();
                record.setDebitCard(cardNumber, expireDate, ccv, cash);
            }
            if (paymentMethod == "cash") {
                cash = Double.parseDouble(payAmountTextField.getText());
                record.setCash(cash);
            }
            record.setPaymentMethod(paymentMethod);
            System.out.println(paymentMethod);
            System.out.println(cardNumber);
            System.out.println(expireDate);
            System.out.println(ccv);
            System.out.println(cash);

            record.processOrder();
            JOptionPane.showMessageDialog(mainFrame, "Successfully add new record");
        }
    }

    class creditCardListsner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardNumberLabel.setVisible(true);
            cardNumberTextField.setVisible(true);
            expireDateLabel.setVisible(true);
            expireDateTextField.setVisible(true);
            ccvLabel.setVisible(true);
            ccveTextField.setVisible(true);

            payAmountLabel.setVisible(false);
            payAmountTextField.setVisible(false);
        }
    }

    class debitCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardNumberLabel.setVisible(true);
            cardNumberTextField.setVisible(true);
            expireDateLabel.setVisible(true);
            expireDateTextField.setVisible(true);
            ccvLabel.setVisible(true);
            ccveTextField.setVisible(true);

            payAmountLabel.setVisible(false);
            payAmountTextField.setVisible(false);
        }
    }

    class cashListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardNumberLabel.setVisible(false);
            cardNumberTextField.setVisible(false);
            expireDateLabel.setVisible(false);
            expireDateTextField.setVisible(false);
            ccvLabel.setVisible(false);
            ccveTextField.setVisible(false);

            payAmountLabel.setVisible(true);
            payAmountTextField.setVisible(true);
        }
    }

    class printReceiptButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WriteFile writeFile = new WriteFile();
            String name = customerNameTextField.getText();
            String id = customerIdTextField.getText();
            String age = ageTextField.getText();
            String address = customerAddressTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            String data = "Name: " + name + " ID: " + id + " Age: " + age + " Phone Number: " + phoneNumber
                    + " Address: " + address + "\n";
            writeFile.write(data);
        }
    }

    

}
