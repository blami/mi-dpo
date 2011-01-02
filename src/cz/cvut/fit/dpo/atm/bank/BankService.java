package cz.cvut.fit.dpo.atm.bank;


// MI-DPO: Decorator (Component Interface)

/**
* Bank service interface.
* @author Ondřej Baláž <balazond@fit.cvut.cz>
*/
public interface BankService {
	
	/**
	 * Get bank name.
	 * @return			bank name
	 */
	public String getName();
	
	/**
	 * Authorize bank customer by PIN entered to ATM.
	 * @param pin		entered PIN
	 * @return			true if success, otherwise false
	 */
	public boolean authorize(int pin);
	
	/**
	 * Get customer's account balance.
	 * @param pin		customer's PIN
	 * @return			amount available to customer through ATM
	 */
	public int getBalance(int pin);
	
	/**
	 * Get customer's detailed balance message.
	 * @return			detailed message amount available to customer through ATM
	 */
	public String getDetailedBalance(int pin);
	
	// MI-DPO (note about kinda ugly thing)
	// This is made this way only because I need applicate Strategy pattern 
	// somewhere (so at least two methods - algorithms to fullfil user's request
	// must have the same interface. In normal situation there would be 
	// withdraw() returning boolean or throwing Exception.
	
	/**
	 * Withdraw money form customer's account.
	 * @param pin		customer's PIN
	 * @param amount	amount to withdraw
	 * @return			new balance if success, otherwise -1
	 */
	public int withdraw(int pin, int amount); 
	
	/**
	 * Deposit money to customer's account.
	 * @param pin		customer's PIN
	 * @param amount	amount to deposit
	 * @return			balance after deposit
	 */
	public int deposit(int pin, int amount);
}
