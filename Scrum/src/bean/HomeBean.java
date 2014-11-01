package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


 
@ManagedBean(name = "homeBean")
@SessionScoped
public class HomeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 531991458949989585L;

	public String toViewHome() {
    	return "home";
    }
 
    public String toViewUsusuarios() {
    	return "usuarios";
    }
}