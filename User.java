
/**
 * Used for login purposes
 */
public class User {
    
    // attributes
    private String username;
    private String password;
    
    // default constructor
    public User() {
        this.username = "";
        this.password = "";
    }

    // parameterised constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /**
     * Accessors
     * @return pass attributes value
     */
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }


    /**
     * Setter
     * @param set attributes value
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
