
import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;
public class EditProductGui implements ActionListener {

    private ArrayList<Product> productList;
    JFrame editProductFrame;
    JComboBox<Product> productComboBox;
    JTextField productIdTextField;
    JTextField productNametTextField;
    JTextField productPriceTextField;
    JTextField productQuantityTextField;
    JButton ediButton, ediProductButton;
    
    public EditProductGui(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void display() {
        editProductFrame = new JFrame("Edit Product");

        JLabel selectProductLabel = new JLabel("Select Product");
        selectProductLabel.setBounds(30, 50, 300, 30);
        editProductFrame.add(selectProductLabel);

        // productComboBox = new JComboBox<Product>(productList.toArray());
        productComboBox = new JComboBox<Product>(new Vector<Product>(productList));
        productComboBox.setBounds(180, 55, 250, 20);
        productComboBox.addActionListener(this);
        editProductFrame.add(productComboBox);

        JLabel productIdLabel = new JLabel("Product ID");
        productIdLabel.setBounds(30, 100, 300, 30);
        editProductFrame.add(productIdLabel);

        productIdTextField = new JTextField("");
        productIdTextField.setBounds(180, 105, 200, 20);
        editProductFrame.add(productIdTextField);

        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(30, 150, 300, 30);
        editProductFrame.add(productNameLabel);

        productNametTextField = new JTextField("");
        productNametTextField.setBounds(180, 155, 200, 20);
        editProductFrame.add(productNametTextField);

        JLabel productPriceLabel = new JLabel("Product Price");
        productPriceLabel.setBounds(30, 200, 300, 30);
        editProductFrame.add(productPriceLabel);

        productPriceTextField = new JTextField("");
        productPriceTextField.setBounds(180, 205, 200, 20);
        editProductFrame.add(productPriceTextField);

        JLabel productQuantityLabel = new JLabel("Product Quantity");
        productQuantityLabel.setBounds(30, 250, 300, 30);
        editProductFrame.add(productQuantityLabel);

        productQuantityTextField = new JTextField("");
        productQuantityTextField.setBounds(180, 255, 200, 20);
        editProductFrame.add(productQuantityTextField);

        ediProductButton = new JButton("Edit Product");
        ediProductButton.setBounds(175, 355, 150, 20);
        ediProductButton.addActionListener(this);
        editProductFrame.add(ediProductButton);

        editProductFrame.setSize(500, 500);
        editProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editProductFrame.setLayout(null);
        editProductFrame.setVisible(true);
    }

        public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == productComboBox) {
            Product selectedProduct = (Product) productComboBox.getSelectedItem();
            String id = selectedProduct.getId();
            String name = selectedProduct.getName();
            double price = selectedProduct.getPrice();
            int quantity = selectedProduct.getQuantity();

            productIdTextField.setText(id);
            productNametTextField.setText(name);
            productPriceTextField.setText(String.valueOf(price));
            productQuantityTextField.setText(String.valueOf(quantity));
        }
        
        if(e.getSource() == ediProductButton){
            Product selectedProduct = (Product) productComboBox.getSelectedItem();
            String id = productIdTextField.getText();
            String name = productNametTextField.getText();
            double price = Double.parseDouble(productPriceTextField.getText());
            int quantity = Integer.parseInt(productQuantityTextField.getText());
            selectedProduct.setId(id);
            selectedProduct.setName(name);
            selectedProduct.setPrice(price);
            selectedProduct.setQuantity(quantity);
            JOptionPane.showMessageDialog(editProductFrame, "Successfully Update Product");
        }
        }
}