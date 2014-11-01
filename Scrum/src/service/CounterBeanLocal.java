package service;

import javax.ejb.Local;

@Local
public interface CounterBeanLocal {
	public int getHits();
}
