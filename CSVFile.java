import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    
    /**
     * Calculate the total product count by iterating the number of lines
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
     * Read from CSV and then create the object
     * @param productFilename
     * @return arraylist of products
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
     * Create individual product in product arraylist
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
    
    
    /*
     * Write(Overwrite) the productCSV data to a new one
     */
    public static void writeProduct(String productFilenameString, ArrayList<Product> products) {

        try {
            FileWriter fileWrt = new FileWriter(productFilenameString);
            BufferedWriter bufferWrt = new BufferedWriter(fileWrt);

            for (Product product : products) {
                System.out.println(product.getId().toString());
                bufferWrt.write(product.getId().toString() + ",");
                bufferWrt.write(product.getName().toString() + ",");
                bufferWrt.write(Double.toString(product.getPrice()) + ",");
                bufferWrt.write(Integer.toString(product.getQuantity()) + "\n");

                bufferWrt.flush();
                bufferWrt.close();
                System.out.println("File updated successfully!");
            }

        } catch (IOException writeError) {
            writeError.printStackTrace();
        }
    }    
    
    
    /*
     * Create product CSV if file is missing
     */
    public static void createProductCSVfile(String productFilename) {

        File producFile = new File(productFilename + ".csv");
        System.out.println("File created: " + producFile.getName());

        FileWriter writer;
        try {
            writer = new FileWriter(productFilename);

            // this is the original template for product, change here
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
}
