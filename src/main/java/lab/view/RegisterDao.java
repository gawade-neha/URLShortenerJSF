package lab.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RegisterDao {
    public static String registerUser(String username, String password)

    {
        int count=0;
        Connection con=null;
        PreparedStatement ps = null;
        try{
            con = Connection1.getConnection();
            ps=con.prepareStatement("SELECT COUNT (*) FROM GradProj.Userdetails where username=?");
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            if(count==0){
            System.out.println("entering users"+count);
            ps=con.prepareStatement("INSERT INTO GradProj.Userdetails (USERNAME,PASSWORD) VALUES (?,?)");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.executeUpdate();

            return "Success";

            }else{
            	return "register";
            }

        }
        catch (SQLException ex)
        {
            System.out.println("register error" + ex.getMessage());
            return "register";
        }
        finally {
            Connection1.close(con);
        }

    }
}