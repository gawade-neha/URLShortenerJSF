package lab.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "register")
@SessionScoped
public class Register {
    private String username;
    private String password;
    

   public Register(){
	   
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


	public String doregister()
    {

        String destination=RegisterDao.registerUser(username,password);
        if(destination.equals("register")){
        	FacesContext.getCurrentInstance().addMessage(
            		"regForm",new FacesMessage("Username and Password already exists. Please enter different username and Password"));
        }
        return destination;

    }



}