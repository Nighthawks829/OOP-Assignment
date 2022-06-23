import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVFile {
    
    /**
     * Calculate the total product count by iterating the number of lines.
     * @param filename 
     * @return total product count
     */
    public static long countLine(String filename) {
        long lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null)
                lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;  // number of lines or product
    }


    /**
     * Read from CSV and then create the object. And then insert it to an arraylist.
     * @param productFilename
     * @return arraylist of products
     * @throws IOException
     */
    public static ArrayList<Product> readCSVProductFile(String productFilename) {

        ArrayList<Product> products = new ArrayList<>();
        Path pathToFile = Paths.get(productFilename);

        if (Files.notExists(pathToFile)) {  // if the productCSV file is missing, create a new one
            createProductCSVfile(productFilename);
        }

        try (BufferedReader bufferedreaderObject = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = bufferedreaderObject.readLine();

            while (line != null) {

                String[] variable = line.split(",");
                Product product = createProduct(variable);
                products.add(product);
                line = bufferedreaderObject.readLine();
            }
        } catch (Exception errorReadCSV) {
            errorReadCSV.printStackTrace();
        }
        return products;
    }


    /**
     * Create individual product in product arraylist.
     * @param variable Used for constructor
     * @return Product
     */
    private static Product createProduct(String[] variable) {

        String id = variable[0];
        String name = variable[1];
        double price = Double.parseDouble(variable[2]);
        int quantity = Integer.parseInt(variable[3]);

        return new Product(id, name, price, quantity);
    }


    /**
     * Write(Overwrite) the productCSV data to a new one.
     * @param productFilenameString
     * @param products
     */
    public static void writeProduct(String productFilenameString, ArrayList<Product> products) {

        Path pathToFile = Paths.get(productFilenameString);

        if (Files.notExists(pathToFile)) {  // if the productCSV file is missing, create a new one
            createProductCSVfile(productFilenameString);
        }

        FileWriter fileWrt;
        try {
            fileWrt = new FileWriter(productFilenameString);
            String data = "";

            for (Product product : products) {
                data += product.getId().toString() + ",";
                data += product.getName().toString() + ",";
                data += Double.toString(product.getPrice()) + ",";
                data += Integer.toString(product.getQuantity()) + "\n";
            }

            System.out.println(data);
            fileWrt.append(data);
            fileWrt.close();
            System.out.println("File updated successfully!");

        } catch (IOException writeError) {
            writeError.printStackTrace();
        }

    }


    /**
     * Create product CSV if file is missing
     * @param productFilename
     */
    public static void createProductCSVfile(String productFilename) {

        File productFile = new File(productFilename);
        System.out.println("File created: " + productFile.getName());

        FileWriter writer;
        try {
            writer = new FileWriter(productFilename);

            // this is the original template for product
            writer.append("A001,Product 1 Name,10.00,100\n");
            writer.append("A002,Product 2 Name,20.00,10\n");
            writer.append("A003,Product 3 Name,30.00,200\n");
            writer.append("A004,Product 4 Name,40.00,5\n");
            writer.append("A005,Product 5 Name,50.00,50");

            writer.flush();
            writer.close();
        } catch (IOException writeError) {
            writeError.printStackTrace();
        }
    }


    /**
     * Read from CSV and then create the object. And then insert it to an arraylist.
     * @param userFilename filename for csv file.
     * @return arraylist of users.
     */
    public static ArrayList<User> readCSVUserFile(String userFilename) {

        ArrayList<User> users = new ArrayList<>();
        Path pathToUserFile = Paths.get(userFilename);

        if (Files.notExists(pathToUserFile)) {  // if the UserCSV file is missing, create a new one
            createUserCSVfile(userFilename);
        }

        try (BufferedReader bufferedreaderObject = Files.newBufferedReader(pathToUserFile, StandardCharsets.US_ASCII)) {
            String line = bufferedreaderObject.readLine();

            while (line != null) {
                String[] variable = line.split(","); 
                User user = createUser(variable);
                users.add(user);
                line = bufferedreaderObject.readLine();
            }
        } catch (Exception errorReadCSV) {
            errorReadCSV.printStackTrace();
        }
        return users;
    }


    /**
     * Create individual user in user arraylist.
     * @param variable
     * @return User
     */
    private static User createUser(String[] variable) {

        String username = variable[0];
        String password = variable[1];

        return new User(username, password);
    }


    /**
     * Create product CSV if file is missing.
     * @param userFilename
     */
    public static void createUserCSVfile(String userFilename) {

        File userFile = new File(userFilename);
        System.out.println("File created: " + userFile.getName());

        FileWriter writer;
        try {
            writer = new FileWriter(userFile);

            // original template, change here
            writer.append("admin,admin\n");
            writer.append("user1,password\n");
            writer.append("user2,opensesame");

            writer.flush();
            writer.close();
        } catch (IOException writeError) {
            writeError.printStackTrace();
        }
    }

}
