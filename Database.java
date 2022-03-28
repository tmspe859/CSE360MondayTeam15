import java.sql.*;

public class Database {


    protected static void checkTables(){
        UserModel.createTable();
    }

    protected static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println(exception.toString());
            System.out.println("Could not connect to Database");
            return null;
        }
        return connection;
    }

}
