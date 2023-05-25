package dbSteps;

import java.sql.*;

import static Connection.ConnectToSQL.serverConnection;


public class DataBase{
    public static int getLastId(){
        int lastId = 0;

        try (Connection connectServ = serverConnection()) {
            Statement lastRowStatement = connectServ.createStatement();
            ResultSet lastRow = lastRowStatement.executeQuery("USE students; SELECT MAX(id) as maxId FROM dbo.students");

            while (lastRow.next()) {
                lastId = lastRow.getInt("maxId");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastId;
    }

    public static int addNewRow(int id, String name, boolean commit){

        int lastId = 0;

        try (Connection connectServ = serverConnection()) {
            connectServ.setAutoCommit(false);
            String insertQuery = "USE students; INSERT INTO dbo.students (id, firstName, lastName, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connectServ.prepareStatement(insertQuery);
            insertStatement.setInt(1, id);
            insertStatement.setString(2, name);
            insertStatement.setString(3, "Khumarashvili");
            insertStatement.setString(4, "1234567890");
            insertStatement.executeUpdate();

            if(commit){
                connectServ.commit();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastId;
    }

    public static int rowExist(int id){
        int rowExists = -10;

        try (Connection connectServ = serverConnection()) {
            String selectQuery = "USE students; SELECT COUNT(*) AS rowExists FROM dbo.students WHERE id = ?";
            PreparedStatement selectStatement = connectServ.prepareStatement(selectQuery);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            rowExists = resultSet.getInt("rowExists");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowExists;
    }

    public static void updateFirstName(int lastId, String newName){
        try (Connection connectServ = serverConnection()) {
            String updateQuery = "USE students;UPDATE dbo.students SET firstName = ? WHERE id = ?";
            PreparedStatement updateStatement = connectServ.prepareStatement(updateQuery);
            updateStatement.setString(1, newName);
            updateStatement.setInt(2, lastId);
            updateStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getFirstName(int id){
        String name = null;

        try (Connection connectServ = serverConnection()) {
            String selectQuery = "USE students; SELECT firstName FROM dbo.students WHERE id = ?";
            PreparedStatement selectStatement = connectServ.prepareStatement(selectQuery);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            name = resultSet.getString("firstName");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return name;
    }
}
