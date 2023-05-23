import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import steps.PracticeFormStep;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class SQLtable {

    PracticeFormStep practiceFormStep;

    @BeforeMethod
    public void configSetup() {
        practiceFormStep = new PracticeFormStep();
        open("https://demoqa.com/automation-practice-form");
    }

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

    @Test
    public void dbTest() {
        try (Connection connectServ = SQLtable.serverConnection()) {
            Statement querySt = connectServ.createStatement();
            ResultSet result = querySt.executeQuery("use students; select firstName,lastName,phone from dbo.students;");

            while (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String phoneNumber = result.getString("phone");

                practiceFormStep
                        .fillFirstName(firstName)
                        .fillLastName(lastName)
                        .fillMobile(phoneNumber);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}