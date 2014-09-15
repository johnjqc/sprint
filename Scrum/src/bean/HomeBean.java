package bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utils.Util;


 
@ManagedBean(name = "homeBean")
@SessionScoped
public class HomeBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String message, uname;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getUname() {
    	HttpSession session = Util.getSession();
    	
        return (String) session.getAttribute("username");
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    
 
    public String logout() {
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login";
   }
}