
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddProductGui implements ActionListener {
    private ArrayList<Product> productList;

    JFrame addProductFrame;
    JTextField productIdTextField;
    JTextField productNametTextField;
    JTextField productPriceTextField;
    JTextField productQuantityTextField;
    JButton ediButton, ediProductButton;

    public AddProductGui(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void display() {
        addProductFrame = new JFrame("Edit Product");

        JLabel productIdLabel = new JLabel("Product ID");
        productIdLabel.setBounds(60, 100, 300, 30);
        addProductFrame.add(productIdLabel);

        productIdTextField = new JTextField("");
        productIdTextField.setBounds(210, 105, 200, 20);
        addProductFrame.add(productIdTextField);

        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(60, 150, 300, 30);
        addProductFrame.add(productNameLabel);

        productNametTextField = new JTextField("");
        productNametTextField.setBounds(210, 155, 200, 20);
        addProductFrame.add(productNametTextField);

        JLabel productPriceLabel = new JLabel("Product Price");
        productPriceLabel.setBounds(60, 200, 300, 30);
        addProductFrame.add(productPriceLabel);

        productPriceTextField = new JTextField("");
        productPriceTextField.setBounds(210, 205, 200, 20);
        addProductFrame.add(productPriceTextField);

        JLabel productQuantityLabel = new JLabel("Product Quantity");
        productQuantityLabel.setBounds(60, 250, 300, 30);
        addProductFrame.add(productQuantityLabel);

        productQuantityTextField = new JTextField("");
        productQuantityTextField.setBounds(210, 255, 200, 20);
        addProductFrame.add(productQuantityTextField);

        ediProductButton = new JButton("Add Product");
        ediProductButton.setBounds(175, 355, 150, 20);
        ediProductButton.addActionListener(this);
        addProductFrame.add(ediProductButton);

        addProductFrame.setSize(500, 500);
        addProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addProductFrame.setLayout(null);
        addProductFrame.setVisible(true);
    }

        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == ediProductButton) {
            String id = productIdTextField.getText();
            String name = productNametTextField.getText();
            double price = Double.parseDouble(productPriceTextField.getText());
            int quantity = Integer.parseInt(productQuantityTextField.getText());
            Product newProduct = new Product(id, name, price, quantity);
            productList.add(newProduct);
            JOptionPane.showMessageDialog(addProductFrame, "Successfully Add New Product");
        }
    }
}