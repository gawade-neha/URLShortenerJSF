package lab.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginDao {

        public static boolean validate(String username, String password) {
            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = Connection1.getConnection();

                ps = con.prepareStatement("Select USERNAME, PASSWORD from GradProj.userdetails where USERNAME = ? and PASSWORD = ?");
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    //result found, means valid inputs
                    System.out.println("true");

                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Login error -->" + ex.getMessage());

                return false;
            } finally {
            	Connection1.close(con);
            }
            return false;
        }
    }