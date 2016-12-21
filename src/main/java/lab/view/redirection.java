package lab.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;
//redirection code if user enters shortened URL in browsers address bar

@WebFilter(
        urlPatterns = { "/short/*" },
        filterName = "UrlFilter",
        description = "Filter all short URLs"  
)
public class redirection implements Filter {
    // implements Filter's methods here...

	private ServletContext context;
	static Connection con=null;
    static PreparedStatement ps=null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
		this.context = filterConfig.getServletContext();

	}
	
    @Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String shortUrl = request.getRequestURL().toString();
       	System.out.println(shortUrl);
       	//shortUrl=shortUrl.replace("URLShortenerJSF/", "");
    	String longUrl="";
    		        	
    	con=Connection1.getConnection();
        try {
            
            ps=con.prepareStatement("Select distinct LONGURL ,SHORTURL from GradProj.Url where shorturl = ? ");
            ps.setString(1,shortUrl); 
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
            	longUrl=rs.getString("longurl");
            	String shorturl1=rs.getString("shorturl");
            	System.out.println(longUrl+" "+shorturl1+" ");
            }
            
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("error displaying the long url: "+e.getMessage());
        }
    	
    	if(longUrl.contains("http://")|| longUrl.contains("https://") ){
			response.sendRedirect( longUrl);
			return;
		}
		else if(longUrl!=""){
			response.sendRedirect("http://" + longUrl);
			return;
		}
		
		chain.doFilter(req, res);
	}

}

