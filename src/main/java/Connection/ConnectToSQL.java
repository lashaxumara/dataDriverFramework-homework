package Connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectToSQL {
    public static Connection serverConnection() throws SQLException {
        Connection connectServ = null;

        try (FileInputStream filePath = new FileInputStream("src/main/resources/database.properties")) {
            Properties pros = new Properties();
            pros.load(filePath);

            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            connectServ = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return connectServ;
    }
}
