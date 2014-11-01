package service;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class CounterBean implements CounterBeanLocal {

	private int hits = 1;
	
	@Lock(LockType.READ)
	public int getHits() {
		return hits++;
	}

}
