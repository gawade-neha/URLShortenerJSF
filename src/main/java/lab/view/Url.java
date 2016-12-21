package lab.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name="url")
@RequestScoped
public class Url {
    static Connection con=null;
    static PreparedStatement ps=null;
    HttpSession session= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);


	public Url()
     {
         

     }
     //insert URLs in database
    public static String insertintodatabse(String username,String longurl,String shorturl) throws SQLException {

    	System.out.println("insertintodatabse method");

        try {

        	int count=0;
            con=Connection1.getConnection();
            ps=con.prepareStatement("SELECT COUNT (*)  FROM GradProj.URL where username=? and longurl=?");
            ps.setString(1,username);
            ps.setString(2,longurl);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println(count);
            if(count==0){
            int clicks=0;
            ps=con.prepareStatement("INSERT INTO GradProj.URL(USERNAME,LONGURL,SHORTURL,CLICKS) VALUES (?,?,?,?)");
            ps.setString(1,username);
            ps.setString(2,longurl);
            ps.setString(3,shorturl);
            ps.setInt(4,clicks);
            ps.executeUpdate();
            }
            return "Private";

        }
        catch (SQLException ex)
        {
            System.out.println("Error entering url to database" +ex.getErrorCode()+ex.getMessage());
            return "Private";
        }
        finally {
            con.close();
        }
    }

    //find longurl from shorturl
    public static String findlongurl(String shorturl){
    	System.out.println("findlongUrl method");
    	String longurl="";
        con=Connection1.getConnection();
        try {
            
            ps=con.prepareStatement("Select distinct LONGURL ,SHORTURL from GradProj.Url where shorturl = ? ");
            ps.setString(1,shorturl); 
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            	longurl=rs.getString("longurl");
            	String shorturl1=rs.getString("shorturl");
            	System.out.println(longurl+" "+shorturl1+" ");
            }
            if(!longurl.equals("")){
            return longurl;
            }else{
            	return "error";
            }
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("error displaying the long url: "+e.getMessage());
            return longurl;
        }
    	
    	
    }
    
   // increment clicks
    public static void Click(String longurl)
    {
        String username=HttpMethods.getUserName();
    	System.out.println("clicks method");

 
        con=Connection1.getConnection();
        try {
            
            ps=con.prepareStatement("UPDATE GradProj.Url SET clicks = clicks+1  where longurl = ? and username = ?");
            ps.setString(1,longurl);
            ps.setString(2,username);
            ps.executeUpdate();
            
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("error incrementing the clicks: "+e.getMessage());
        }
    }
    
  //find all urls for a particular user  
    public static List<UrlInfo> findAllUrl() throws SQLException {
    	
    	String username=HttpMethods.getUserName();
        
    	List<UrlInfo> m=new ArrayList<UrlInfo>();
    	System.out.println("findAllUrl method");
        con=Connection1.getConnection();
        try {
            
            ps=con.prepareStatement("Select distinct m.LONGURL ,m.SHORTURL , (select sum(CLICKS)  from GradProj.Url where LONGURL = m.LONGURL)as totalclicks from GradProj.Url as m where username = ? ");
            ps.setString(1,username); 
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            	String longurl=rs.getString("longurl");
            	String shorturl=rs.getString("shorturl");
            	int clicks=rs.getInt("totalclicks");
            	System.out.println(longurl+" "+shorturl+" "+clicks);
            	UrlInfo urls=new UrlInfo(longurl,shorturl,clicks);
            	m.add(urls);
            }
            
            return m;
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("error displaying the list of urls: "+e.getMessage());
            return m;
        }
    }





}