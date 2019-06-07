package Database;

import java.sql.*;

public class DatabaseHandler extends ConfigsDB{

    public static Connection getDbConnection() throws SQLException{
        String connectionString ="jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName+ "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }
    public static String getUserByLogin(String login,String password) throws SQLException {
        String SELECT = "SELECT * FROM users WHERE login = ? AND password = ?";
        ResultSet resultSet = null;
        int countUsers=0;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(SELECT);
            prSt.setString(1, login);
            prSt.setString(2, password);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (resultSet.next())
            countUsers++;

        return countUsers == 1 ? "found" : "not found";
    }

}
