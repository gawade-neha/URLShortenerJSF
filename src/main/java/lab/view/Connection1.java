package lab.view;

import java.sql.Connection;
import java.sql.DriverManager;
public class Connection1 {
//establishing connection with hsqldb

    public static Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:hsqldb:hsql://localhost/cpsc476;", "SA", "Passw0rd");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

}