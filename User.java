public abstract class User {

    static private Integer GLOBAL_ID = 0;

    protected String firstName = "";
    protected String lastName = "";
    protected String userName = "";
    protected String password = "";
    protected String email = "";
    protected Integer accountID;

    public User() {
        this.accountID = User.GLOBAL_ID;
        User.GLOBAL_ID++;
    }
    
    public User(String firstName, String lastName, String userName, String password, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    
    public void updateInfo() {}

    public String getFirstName() { return this.firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUserName() { return this.userName; }
    public void setUserName(String userName) { this.userName = userName; }

    // Should be done with a database lookup
    public String getPassword() { return this.password; }

    public String getEmail() { return this.email; }
    public Integer getAccountID() { return this.accountID; }
}
