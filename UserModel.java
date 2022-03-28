import java.sql.*;

public class UserModel {

    private static final String tableName = "users";
    private static final String idColumn = "id";
    private static final String firstNameColumn = "firstname";
    private static final String lastNameColumn = "lastname";
    private static final String userNameColumn = "username";
    private static final String emailColumn = "email";
    private static final String passwordColumn = "password";

    public static void createTable(){

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                           "(id INTEGER PRIMARY KEY     NOT NULL," +
                           " firstname      CHAR(25)    NOT NULL, " + 
                           " lastname       CHAR(25)    NOT NULL, " + 
                           " username       CHAR(25)    NOT NULL, " + 
                           " password       CHAR(50)    NOT NULL, " +
                           " email          CHAR(50)    NOT NULL )"; 
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

         }

    }

    public static User getUser(String userName, String password){

        User user = null;

        String condition = " (" + userNameColumn + "='" + userName +
            "' OR " + emailColumn + "='" + userName + "') " +
            " AND " + passwordColumn + "='" + password + "'";

        try(Connection connection = Database.connect()) {
   
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE " + condition; 
            ResultSet rs = stmt.executeQuery(sql);

            user = new Customer(rs.getString(firstNameColumn), rs.getString(lastNameColumn),
                rs.getString(userNameColumn), rs.getString(passwordColumn),
                 rs.getString(emailColumn),
                 rs.getInt(idColumn));

            rs.close();
            stmt.close();
            connection.close();

         } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
         }
        return user;

    }

    public static int createUser(String firstName, String lastName, 
            String userName, String email, String password) {

        //update database
        int id = (int) CRUDHelper.create(
            tableName,
            new String[]{firstNameColumn, lastNameColumn, 
                userNameColumn, emailColumn, passwordColumn},
            new Object[]{firstName, lastName, 
                userName, email, password},
            new int[]{Types.VARCHAR, Types.VARCHAR, 
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR}
        );

        return id;

        //update cache
        
    }
}
