package cz.cvut.fit.dpo.atm.process;

import cz.cvut.fit.dpo.atm.bank.BankService;


// MI-DPO: Strategy (Strategy)

/**
 * Operation strategy for withdraw and deposit operations.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public interface OperationStrategy {
	
	/**
	 * Generalized interface to all operations available.
	 * @param bank			bank service to operate on
	 * @param pin			PIN to operate on
	 * @param amount		amount to operate with
	 * @return				operation result
	 */
	public int execute(BankService bank, int pin, int amount);
	
	/**
	 * Get operation message shown to customer in ATM
	 * @return				enter message
	 */
	public String getOperationMessage();

	/**
	 * Get result message shown to customer in ATM
	 * @param result		result to get message for
	 * @return 				result message for given result
	 */
	public String getResultMessage(int result);		
}