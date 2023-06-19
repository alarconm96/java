import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

class JDBCTest {
    public static void main(String[] args) {
        try {
            //driver and user args
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/school";
            String user = "alarconm96";
            String password = "password";

            //create connection obj with user args
            Connection connection = DriverManager.getConnection(url, user, password);

            //create statement obj to carry statements to db
            Statement statement = connection.createStatement();

            //create resultSet obj and execute given query string
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

            //while resultSet has results, print them to sysout
            while (resultSet.next()) {
                System.out.println(
                    resultSet.getInt(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " " +
                    resultSet.getString(4) + " " +
                    resultSet.getString(5) + " " +
                    resultSet.getString(6)
                );
            }

            //close connection to db
            connection.close();
        } catch (Exception e) {
            //print exceptions to sysout
            System.out.println(e);
        }
    }
}