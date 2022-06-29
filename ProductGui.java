
import javax.swing.*;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class ProductGui implements ActionListener {

    Record newRecord;

    JFrame productFrame;
    JComboBox<Product> productComboBox;
    JSpinner productSpinner;
    ArrayList<Product> productListObject;
    JLabel quantityLabel;
    JTable productTable;
    JLabel totalAfterTaxLabel;
    JLabel totalBeforeTaxLabel;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem exit;
    JButton addProductButton, payFrameButton;
    
    public ProductGui(Record newRecord, ArrayList<Product> productListObject) {
        this.newRecord = newRecord;
        this.productListObject = productListObject;
    }

    public void display() {
        productFrame = new JFrame("Supermarket Point-of-Sales System");

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);

        menu.add(exit);
        menuBar.add(menu);
        productFrame.setJMenuBar(menuBar);

        JPanel productPanel = new JPanel();
        productPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Product List"));
        productPanel.setBackground(Color.YELLOW);
        productPanel.setBounds(3, 300, 630, 250);
        productPanel.setLayout(null);

        JLabel selectProductLabel = new JLabel("Select Product");
        selectProductLabel.setBounds(30, 30, 300, 30);
        productPanel.add(selectProductLabel);

        productComboBox = new JComboBox<Product>(new Vector<Product>(productListObject));
        productComboBox.setBounds(130, 35, 250, 20);
        productComboBox.addActionListener(this);
        productPanel.add(productComboBox);

        SpinnerModel value = new SpinnerNumberModel(1, 1, 100, 1);
        productSpinner = new JSpinner(value);
        productSpinner.setBounds(395, 35, 50, 20);
        productPanel.add(productSpinner);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(460, 35, 150, 20);
        addProductButton.addActionListener(this);
        productPanel.add(addProductButton);

        quantityLabel = new JLabel("Quantity Left:");
        quantityLabel.setBounds(30, 60, 300, 30);
        productPanel.add(quantityLabel);

        String data[][] = {};
        String tableColumns[] = { "Product ID", "Product Name", "Unit Price", "Quantity", "Total" };

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

        productTableScrollPane.setBounds(30, 100, 550, 250);
        productPanel.add(productTableScrollPane);

        totalBeforeTaxLabel = new JLabel("Total Before Tax: RM0.00");
        totalBeforeTaxLabel.setBounds(420, 350, 300, 30);
        productPanel.add(totalBeforeTaxLabel);

        totalAfterTaxLabel = new JLabel(
                "Total After Tax (" + String.valueOf(Math.round(newRecord.getTax() * 100.0)) + "%): RM0.00");
        totalAfterTaxLabel.setBounds(405, 380, 300, 30);
        productPanel.add(totalAfterTaxLabel);

        payFrameButton = new JButton("Pay");
        payFrameButton.setBounds(225, 410, 150, 20);
        payFrameButton.addActionListener(this);
        productPanel.add(payFrameButton);

        productFrame.add(productPanel);

        productFrame.setSize(650, 500);
        productFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // mainFrame.setLayout(null);
        productFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            int confirmed = JOptionPane.showConfirmDialog(productFrame, "Are you want to exit?");
            if (confirmed == JOptionPane.YES_OPTION) {
                productFrame.dispose();
            }
        }
    
        if(e.getSource() == productComboBox) {
            Product selectProduct = (Product) productComboBox.getSelectedItem();
            int quantity = selectProduct.getQuantity();
            if (quantity < 10) {
                quantityLabel.setForeground(Color.red);
            } else {
                quantityLabel.setForeground(Color.black);
            }
            String leftQuantity = "Left Quantity: " + quantity;
            quantityLabel.setText(leftQuantity);
        }
    
        if(e.getSource() == addProductButton) {
            try {
                Product selectedProduct = (Product) productComboBox.getSelectedItem();

                String productId = selectedProduct.getId();
                String name = selectedProduct.getName();
                double price = selectedProduct.getPrice();
                int leftQuantity = selectedProduct.getQuantity();

                int quantity = (Integer) productSpinner.getValue();
                if (leftQuantity < quantity) {
                    JOptionPane.showMessageDialog(productFrame, "Not Enough Product", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                newRecord.addProduct(selectedProduct, quantity);
                newRecord.calculateTotalBeforeTax(selectedProduct, quantity);
                productComboBox.setSelectedIndex(0);

                Object[] row = { productId, name, "RM" + price, quantity, "RM" + quantity * price };
                DefaultTableModel model = (DefaultTableModel) productTable.getModel();
                model.addRow(row);

                totalBeforeTaxLabel.setText("Total Before Tax: RM" + String.valueOf(newRecord.getTotalBeforeTax()));
                totalAfterTaxLabel.setText(
                        "Total After Tax (6%): RM"
                                + String.valueOf(Math.round(newRecord.getTotalAfterTax() * 100.0) / 100.0));
                productSpinner.setValue(1);
            } catch (Exception error) {
                error.printStackTrace();
                System.out.println("Error Data Type");
            }
        }
    
        if(e.getSource() == payFrameButton) {
            productFrame.dispose();
            PayGui payGui = new PayGui(newRecord,productListObject);
            payGui.display();
        }
    }
}