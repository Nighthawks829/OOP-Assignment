
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGui {

    private ArrayList<Product> productList;
    private ArrayList<User> userList;

    JFrame loginFrame;
    JTextField employeeIdTextField;
    JTextField passwordTextField;

    private String nextGui = "";
    
    // user data initiation
    String userFilename = "UserCSV.csv";  // User CSV filename, modify this value to change the filename

    public LoginGui(ArrayList<Product> productList, String nextGui) {
        this.productList = productList;
        this.userList = CSVFile.readCSVUserFile(userFilename);
        this.nextGui = nextGui;
    }

    public void display() {
        loginFrame = new JFrame("Admin Login");

        JLabel employeeIdLabel = new JLabel("Employee ID");
        employeeIdLabel.setBounds(120, 120, 300, 30);
        loginFrame.add(employeeIdLabel);

        employeeIdTextField = new JTextField();
        employeeIdTextField.setBounds(220, 125, 150, 20);
        loginFrame.add(employeeIdTextField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(120, 170, 300, 30);
        loginFrame.add(passwordLabel);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(220, 175, 150, 20);
        loginFrame.add(passwordTextField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(175, 270, 150, 20);
        loginButton.addActionListener(new loginListener());
        loginFrame.add(loginButton);

        loginFrame.setSize(500, 500);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
    }

    class loginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = employeeIdTextField.getText();
            String password = passwordTextField.getText();
            boolean validUser = false;

            for (int i = 0; i < userList.size(); i++) {
                if (id.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())) {
                    validUser = true;
                    break;
                }
            }
            
            if (validUser) {
                JOptionPane.showMessageDialog(null, "Login Successful");
                if (nextGui == "editProduct") {
                    EditProductGui editProduct = new EditProductGui(productList);
                    loginFrame.dispose();
                    editProduct.display();
                } else {
                    AddProductGui addProduct = new AddProductGui(productList);
                    loginFrame.dispose();
                    addProduct.display();
                }
            } else {  
                JOptionPane.showMessageDialog(loginFrame, "Invalid Employee ID or Password", "Alert",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
