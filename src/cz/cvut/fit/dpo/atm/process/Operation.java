package cz.cvut.fit.dpo.atm.process;

import cz.cvut.fit.dpo.atm.bank.BankService;


//MI-DPO: Strategy (Context)

/**
 * Strategy context that manages ATM operations.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class Operation {

	/**
	 * Strategy.
	 */
	private OperationStrategy strategy = null;
	
	// Strategy pattern
	
	/**
	 * Constructor.
	 */
	public Operation() {
	}
	
	/**
	 * Construct operation with concrete strategy to execute
	 * @param strategy	strategy to execute
	 */
	public Operation(OperationStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Sets executed strategy.
	 * @param strategy	strategy to execute
	 */
	public void setStrategy(OperationStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * Execute strategy.
	 * @param bank		bank service to operate on
	 * @param pin		PIN to operate on
	 * @param amount	amount to operate with
	 * @return			operation result
	 */
	public int execute(BankService bank, int pin, int amount) {
		return strategy.execute(bank, pin, amount);
	}
	
	/**
	 * Get operation message shown to customer in ATM
	 * @return				enter message
	 */		
	public String getOperationMessage() {
		return strategy.getOperationMessage();
	}
	
	/**
	 * Get result message shown to customer in ATM
	 * @param result		result to get message for
	 * @return 				result message for given result
	 */	
	public String getResultMessage(int result) {
		return strategy.getResultMessage(result);
	}	
}