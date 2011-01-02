package cz.cvut.fit.dpo.atm.bank;

import java.util.Hashtable;


// MI-DPO: Decorator (Concrete Decorator)

/**
* Bank overdraft decorator. Adds overdraft limit to every account.
* @author Ondřej Baláž <balazond@fit.cvut.cz>
*/
public class BankOverdraftDecorator extends BankAbstractDecorator {

	/**
	 * Overdrafts. Entry for each pin is created during first use.
	 */
	private Hashtable<Integer, Integer> overdrafts
		= new Hashtable<Integer, Integer>();
		
	/**
	 * Overdraft limit for accounts.
	 */
	private int limit = 5000;
	
	// Decorator pattern
	
	/**
	 * Constructs decorator with default limit 5000
	 * @param bank			decorated bank
	 */
	public BankOverdraftDecorator(BankService bank) {
		super(bank);
	}
	
	/**
	 * Constructs decorator with direct limit specification
	 * @param bank			decorated bank
	 * @param limit			overdraft limit added to account
	 */
	public BankOverdraftDecorator(BankService bank, int limit) {
		this(bank);
		
		// set limit
		this.limit = limit;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankAbstractDecorator#getName()
	 */
	@Override
	public String getName() {
		return super.getName() + " WITH OVERDRAFT";
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankAbstractDecorator#getBalance(int)
	 */
	@Override
	public int getBalance(int pin) {
		int balance = super.getBalance(pin);
		int overdraft = getOverdraft(pin);
		
		int total = balance + overdraft;
		
		return total;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankAbstractDecorator#getDetailedBalance(int)
	 */
	@Override
	public String getDetailedBalance(int pin) {
		int balance = super.getBalance(pin);
		int overdraft = getOverdraft(pin);
		int total = balance + overdraft;
		
		// build detailed report
		String detail = "Balance: " + balance + "\n"
			+ "Overdraft available: " + overdraft + "\n"
			+ "Total: " + total + "\n";
		
		if(overdraft < limit) {
		// if loan exists, show
			detail = detail + "Loan: " + (0 - (limit - overdraft)) + "\n";
		}
		
		return detail;
	}	
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankAbstractDecorator#withdraw(int, int)
	 */
	@Override
	public int withdraw(int pin, int amount) {
		// first try withdraw from customer's account
		int balance;
		if((balance = super.withdraw(pin, amount)) != -1) {
			return balance;
		} else {
		// withdraw from customer's account failed, try to use overdraft
			balance = super.getBalance(pin);
			int overdraft = getOverdraft(pin);
			
			if((amount - balance) <= overdraft) {
				// first withdraw all customer has
				super.withdraw(pin, balance);
				// then start withdrawing overdraft
				setOverdraft(pin, overdraft - (amount - balance));
				
				return balance + overdraft - amount;
			}
		}
		
		// not enough money
		return -1;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankAbstractDecorator#deposit(int, int)
	 */
	@Override
	public int deposit(int pin, int amount) {
		int balance = super.getBalance(pin);
		int overdraft = getOverdraft(pin);
		
		int residue = amount;
		
		// first check overdraft and redeem existing loan		
		if(overdraft < limit) {
			// amount will be not sufficient or exactly sufficient to redeem
			if((overdraft + amount) <= limit) {
				setOverdraft(pin, overdraft + amount);
				residue = 0;
			} else {
				// there will be residue				
				residue = (overdraft + amount) - limit;
				setOverdraft(pin, limit);
			}
		}
		
		// deposit residue to customer's account
		super.deposit(pin, residue);

		// return new total
		return balance + overdraft + amount;
	}
	
	/**
	 * Gets customer's available overdraft.
	 * @param pin		customer's pin
	 * @return			available overdraft
	 */
	private int getOverdraft(int pin) {
		// create overdraft
		if(!overdrafts.containsKey(pin)) {
			overdrafts.put(pin, limit);
			return limit;
		}
		
		return overdrafts.get(pin);
	}
	
	/**
	 * Sets customer's available overdraft.
	 * @param pin		customer's pin
	 * @return			new overdraft value
	 */
	private int setOverdraft(int pin, int amount) {
		// sets user overdraft
		overdrafts.put(pin, amount);
		return amount;
	}
}
