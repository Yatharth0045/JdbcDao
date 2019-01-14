package magAd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnection {

    static final String JDBC_DRIVER="org.postgresql.Driver";
    static final String DB_URL="jdbc:postgresql://localhost:5432/test_db";
    static final String USERNAME="postgres";
    static final String PASSWORD="postgres";
    Connection connection;

    static JConnection jConnection=new JConnection();

    private JConnection(){
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static JConnection getInstance(){
        return jConnection;
    }

    public Connection getConection(){
        try {
            connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
