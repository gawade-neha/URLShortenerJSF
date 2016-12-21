package lab.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean(name="login")
@SessionScoped
public class Login {

	
	private String username;
	private String password;
	public Login(){
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String doLogin()
    {
        System.out.println("Reached doLogin");
        boolean valid =LoginDao.validate(username, password);  
        if (valid) {
            HttpSession session = HttpMethods.getSession();
            session.setAttribute("username", username);
            System.out.print("username is set as session attribute");
            return "Private";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(
            		"loginForm",new FacesMessage("Incorrect Username and Password. Please enter correct username and Password"));
            return "index";
        }
    }

    public String logout() {
        HttpSession session = HttpMethods.getSession();
        session.invalidate();
        return "index";

    }
	
	
}
