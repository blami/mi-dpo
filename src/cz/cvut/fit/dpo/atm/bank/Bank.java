package cz.cvut.fit.dpo.atm.bank;

import java.util.Hashtable;


// MI-DPO: Decorator (Concrete Decorator)

/**
 * Bank service implementation.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class Bank
	implements BankService {

	/**
	 * Accounts table. Contains pin-amount pairs.
	 */
	private Hashtable<Integer, Integer> accounts 
		= new Hashtable<Integer, Integer>();
	
	/**
	 * Constructs accounts.
	 */
	public Bank() {
		// add some example accounts
		accounts.put(1234, 5000);
		accounts.put(4321, 100);
	}
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#getName()
	 */
	@Override
	public String getName() {
		return "BANK";
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#authorize(int)
	 */
	@Override
	public boolean authorize(int pin) {
		if(accounts.containsKey(pin)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#balance(int)
	 */
	@Override
	public int getBalance(int pin) {
		return accounts.get(pin);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#getDetailedBalance(int)
	 */
	@Override
	public String getDetailedBalance(int pin) {
		return "Balance: " + accounts.get(pin).toString();
	}	
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#withdraw(int, int)
	 */
	@Override
	public int withdraw(int pin, int amount) {
		int a = accounts.get(pin);
		
		if(a < amount) {			
			return -1;
		} else {
			// update balance
			a = a - amount;
			accounts.put(pin, a);
			
			return a;
		}
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#deposit(int, int)
	 */
	@Override
	public int deposit(int pin, int amount) {
		int a = accounts.get(pin);
			
		// update balance
		a = a + amount;
		accounts.put(pin, a);
		
		return a;
	}
}
