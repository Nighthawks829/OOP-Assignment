
import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import java.util.ArrayList;

public class Gui implements ActionListener {
    
    // gui attributes
    JFrame mainFrame;

    JTextField customerNameTextField;
    JTextField customerIdTextField;
    JTextField ageTextField;
    JTextField phoneNumberTextField;
    JTextField customerAddressTextField;
    JButton firstPageButton;
    JMenuItem loadData, editProduct, addProduct, exit;
    //ImageIcon supermarketIcon;
    //JLabel displayImage;
    
    // non-gui attributes
    Customer newCustomer;
    Record newRcord;
    private String title = "Supermarket Point-of-Sales System";   // title, modify this value to change the title
    ArrayList<Product> productListObject;

    public Gui(ArrayList<Product> products) {
        Random rand = new Random();
        this.productListObject = products;
        DateTimeFormatter receiptFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        DateTimeFormatter recordFormat = DateTimeFormatter.ofPattern("ddMMuuuu");
        LocalDate localDate = LocalDate.now();
        String date = receiptFormat.format(localDate);
        String idDate = recordFormat.format(localDate);
        String prefix = Character.toString((char) (rand.nextInt(26) + 'a'))
                + Character.toString((char) (rand.nextInt(26) + 'a'))
                + Character.toString((char) (rand.nextInt(26) + 'a'));
        String recordId = prefix.toUpperCase() + idDate + String.valueOf(rand.nextInt(10000) + 1);
        newRcord = new Record(recordId, date, 0.0, 0.0, 0.06);
    }

    public void display() {
        mainFrame = new JFrame(title);

        // GUI menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        // Menu bar item
        loadData = new JMenuItem("Load Data");
        editProduct = new JMenuItem("Edit Product");
        addProduct = new JMenuItem("Add Product");
        exit = new JMenuItem("Exit");

        loadData.addActionListener(this);
        exit.addActionListener(this);
        editProduct.addActionListener(this);
        addProduct.addActionListener(this);

        menu.add(loadData);
        menu.add(editProduct);
        menu.add(addProduct);
        menu.add(exit);

        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
        
        JPanel customerPanel = new JPanel();
        customerPanel.setBackground(Color.RED);
        customerPanel.setLayout(null);
        customerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Customer Information"));

        mainFrame.add(customerPanel);
        
        //supermarketIcon = new ImageIcon(this.getClass().getResource("Supermarket.jpg"));
        //displayImage = new JLabel(supermarketIcon);
        //displayImage.setSize(200,200);
        //displayImage.add(customerPanel);
        
        JLabel recordIdLabel = new JLabel("Record ID: " + newRcord.getRecordId());
        recordIdLabel.setBounds(175, 20, 300, 30);
        customerPanel.add(recordIdLabel);

        JLabel customerIdLabel = new JLabel("Customer ID");
        customerIdLabel.setBounds(100, 60, 300, 30);
        customerPanel.add(customerIdLabel);

        customerIdTextField = new JTextField();
        customerIdTextField.setBounds(200, 65, 100, 20);
        customerPanel.add(customerIdTextField);

        JLabel customerNameLabel = new JLabel("Customer Name");
        customerNameLabel.setBounds(100, 110, 300, 30);
        customerPanel.add(customerNameLabel);

        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(200, 115, 200, 20);
        customerPanel.add(customerNameTextField);

        JLabel ageLabel = new JLabel("Customer Age");
        ageLabel.setBounds(100, 160, 300, 30);
        customerPanel.add(ageLabel);

        ageTextField = new JTextField();
        ageTextField.setBounds(200, 165, 50, 20);
        customerPanel.add(ageTextField);

        JLabel phoneNumberLabel = new JLabel("Phone Number");
        phoneNumberLabel.setBounds(100, 210, 300, 30);
        customerPanel.add(phoneNumberLabel);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(200, 215, 200, 20);
        customerPanel.add(phoneNumberTextField);

        JLabel customerAddressLabel = new JLabel("Address");
        customerAddressLabel.setBounds(100, 260, 300, 30);
        customerPanel.add(customerAddressLabel);

        customerAddressTextField = new JTextField();
        customerAddressTextField.setBounds(200, 265, 200, 20);
        customerPanel.add(customerAddressTextField);

        firstPageButton = new JButton("Next");
        firstPageButton.setBounds(200, 360, 100, 20);

        customerPanel.add(firstPageButton);
        firstPageButton.addActionListener(this);

        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loadData) {
        	JFrame readFrame = new JFrame("Record");

            JTextArea outpuTextArea = new JTextArea("output");
            outpuTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
            outpuTextArea.setFont(new Font("Ubuntu", Font.BOLD, 12));
            outpuTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outpuTextArea);
            readFrame.add(scrollPane);
            scrollPane.setBounds(50, 50, 400, 350);

            ReadFile readRecord = new ReadFile();
            String data = readRecord.readData("Record.txt");

            outpuTextArea.setText(data);

            readFrame.setSize(500, 500);
            readFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            readFrame.setLayout(null);
            readFrame.setVisible(true);
        }
    
        if(e.getSource() == editProduct) {
            LoginGui loginGui = new LoginGui(productListObject, "editProduct");
            loginGui.display();
        }

        if(e.getSource() == addProduct) {
            LoginGui loginGui = new LoginGui(productListObject, "addProduct");
            loginGui.display();
        }

        if(e.getSource() == exit) {
            int confirmed = JOptionPane.showConfirmDialog(mainFrame, "Do you want to exit?");
            if (confirmed == JOptionPane.YES_OPTION) {
                mainFrame.dispose();
            }
        }
        if(e.getSource() == firstPageButton){
            createCustomer();
            mainFrame.dispose();
            ProductGui productGui = new ProductGui(newRcord, productListObject);
            productGui.display();
        }
    }

    public void createCustomer() {
        try {
            String id = customerIdTextField.getText();
            String name = customerNameTextField.getText();
            int age = Integer.parseInt(ageTextField.getText());
            String phoneNumber = phoneNumberTextField.getText();
            String address = customerAddressTextField.getText();
            newCustomer = new Customer(id, name, (byte) age, address, phoneNumber);
            newRcord.setCustomer(newCustomer);
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println("Error Data Type");
        }
    }
}