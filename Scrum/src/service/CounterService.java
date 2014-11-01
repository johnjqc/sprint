package service;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName= "CounterService")
public class CounterService {

	@EJB
	private CounterBeanLocal ejbRef;
	
	@WebMethod(operationName= "getHits")
	public int getHits() {
		return ejbRef.getHits();
	}
	
}
