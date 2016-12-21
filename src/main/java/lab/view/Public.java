package lab.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="pub")
@RequestScoped
public class Public {

	String longurl;
	String shorturl;

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public String getLongurl() {
		return longurl;
	}

	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	
	public String doLong(){
		
        
		longurl=Url.findlongurl(shorturl);
		if(longurl=="error"){
			FacesContext.getCurrentInstance().addMessage(
            		"publicForm",new FacesMessage("This shortened url doesn't exist in the database"));
			longurl=null;
		}
		shorturl=null;
		return "Public";
	}
}
