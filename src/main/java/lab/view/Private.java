package lab.view;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name="pri")
@RequestScoped
public class Private {

	private String longurl;
	private String shorturl;
	List<UrlInfo> list;
	public Private(){
		
	}
	
	@PostConstruct
    public void init(){
		list=listUrl();
	}
	
	public List<UrlInfo> getList() {
		return list;
	}

	
	
    public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public void setList(List<UrlInfo> list) {
		this.list = list;
	}

	public List<UrlInfo> listUrl() {
		

    	List<UrlInfo> list1=new ArrayList<>();
		try {
			list1 = Url.findAllUrl();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println(list1.size()+"listUrl()");

		return list1;
	
	}
	
	public void clickinterface() throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
    	Map<String,String> params = context.getExternalContext().getRequestParameterMap();
    	String longurl = params.get("action");
		Url.Click(longurl);
		FacesContext.getCurrentInstance()
		   .getExternalContext().redirect("http://"+longurl);

	}
	
	public String getLongurl() {
		return longurl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	
	//long URL to short URL
	public String doShorten() throws UnsupportedEncodingException, SQLException{
		
        String username=HttpMethods.getUserName();
        System.out.println(username+longurl+"doShorten()");
        
		String shorturlstr = Base64.getUrlEncoder().encodeToString(longurl.getBytes("utf-8")).substring(0, 8);
		
		shorturl = "http://localhost:8080/URLShortenerJSF/short/" + shorturlstr;
		
		Url.insertintodatabse(username, longurl, shorturl);
		
		System.out.println("Successfully added in database: "+longurl);
		return "Private";
	}
	
	
	}
	

