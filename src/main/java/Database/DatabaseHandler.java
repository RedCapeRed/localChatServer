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
    public static boolean userFound(String login,String password) throws SQLException {
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

        return countUsers == 1;
    }
    public static  boolean loginAlreadyTaken(String login) throws SQLException {
        String SELECT = "SELECT * FROM users WHERE login = ?";
        ResultSet resultSet = null;
        int countUsers=0;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(SELECT);
            prSt.setString(1, login);
            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (resultSet.next())
            countUsers++;
        return countUsers > 0;
    }

    public static void registerUser(String login,String password){
        String INSERT = "INSERT INTO users ( login , password )" + " VALUES(?,?)" ;

        try (Connection conn = getDbConnection()){
            PreparedStatement prSt = conn.prepareStatement(INSERT);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
