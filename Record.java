import java.util.ArrayList;

public class Record {
    PaymentMethod paymentMethod;
    TaxCalculator taxCalculator = new TaxCalculator();

    private Customer customer;
    private ArrayList<Product> products = new ArrayList<Product>();

    // private String branchName;
    // private String branchLocation;
    private String recordId;
    private String date;
    private double totalBeforeTax;
    private double totalAfterTax;
    private double tax;

    private CreditCard creditCard;
    private DebitCard debitCard;
    private double cash;

    // default constructor
    Record() {
        // this.branchName="";
        // this.branchLocation="";
        this.recordId = "";
        this.date = "";
        this.totalBeforeTax = 0.0;
        this.totalAfterTax = 0.0;
        this.tax = 0.0;

    }

    // parameterized constructor
    Record(String recordId, String date, double totalBeforeTax, double totalAfterTax, double tax) {
        this.recordId = recordId;
        this.date = date;
        this.totalBeforeTax = totalBeforeTax;
        this.totalAfterTax = totalAfterTax;
        this.tax = tax;
    }

    /**
     * Accessors
     **/
    public Customer getCustomer() {
        return customer;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getDate() {
        return date;
    }

    public double getTotalBeforeTax() {
        return totalBeforeTax;
    }

    public double getTotalAfterTax() {
        return totalAfterTax;
    }

    public double getTax() {
        return tax;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public double getCash() {
        return cash;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Mutators
     **/
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalBeforeTax(double totalBeforeTax) {
        this.totalBeforeTax = totalBeforeTax;
    }

    public void setTotalAfterTax(double totalAfterTax) {
        this.totalAfterTax = totalAfterTax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setCreditCard(String cardNumber, String expireDate, String ccv) {
        this.creditCard = new CreditCard(cardNumber, expireDate, ccv);
    }

    public void setDebitCard(String cardNumber, String expireDate, String ccv, double cash) {
        this.debitCard = new DebitCard(cardNumber, expireDate, ccv, cash);
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * Decide the method of payment.
     * Fallback value is set to cash.
     * 
     * @param paymentMethod String paymentMethod.
     */
    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == "creditCard") {
            this.paymentMethod = new PayByCreditCard(creditCard);
        } else if (paymentMethod == "debitCard") {
            this.paymentMethod = new PayByDebitCard(debitCard);
        } else {
            this.paymentMethod = new PayByCash(cash);
        }
    }

    /**
     * Add product(object) to products arraylist.
     * Calls calculateTotalBeforeTax() and THEN calculateTotalAfterTax().
     * 
     * @param product Object.
     */
    public void addProduct(Product product) {
        products.add(product);
        calculateTotalBeforeTax();
        calculateTotalAfterTax();
    }

    /**
     * Calculate the total price BEFORE being taxed.
     * Returns output to totalBeforeTax.
     */
    public void calculateTotalBeforeTax() {
        double total = 0;
        // iterate for each object in arraylist
        for (Product product : products) {
            total += product.getPrice();
        }
        totalBeforeTax = total;
    }

    /**
     * Calculate total price AFTER taxed.
     * Works by using calculateTotalAfterTax function in taxCalculator class.
     * Returns output to totalAfterTax.
     */
    public void calculateTotalAfterTax() {
        totalAfterTax = taxCalculator.calculateTotalAfterTax(totalBeforeTax);
    }

    /**
     * Calls makePayment function from paymentMethod class.
     */
    public void processOrder() {
        paymentMethod.makePayment(totalAfterTax);
    }

    /**
     * Calculate total price by multipliying the product price with the item
     * quantity.
     * Calls calculateTotalAfterTax function of this class.
     * 
     * @param product  Object.
     * @param quantity Amount of items.
     */
    public void calculateTotal(Product product, int quantity) {
        totalBeforeTax += product.getPrice() * quantity;
        calculateTotalAfterTax();
    }
}
