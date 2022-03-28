public class Application {
    private static final Application INSTANCE = new Application();
    
    public static Application getInstance() {
        return Application.INSTANCE;
    }

    private User currUser;

    private Application() {
        this.currUser = null;
    }

    public void setUser(User newCurrUser) {
        this.currUser = newCurrUser;
    }

    public User getCurrUser() {
        return this.currUser;
    }

}
