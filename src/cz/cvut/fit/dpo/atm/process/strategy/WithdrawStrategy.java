package cz.cvut.fit.dpo.atm.process.strategy;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.OperationStrategy;


// MI-DPO: Strategy (Concrete Strategy)

/**
 * Operation strategy for withdraw operation.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class WithdrawStrategy 
	implements OperationStrategy {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#execute(cz.cvut.fit.dpo.atm.bank.BankService, int, int)
	 */
	@Override
	public int execute(BankService bank, int pin, int amount) {
		return bank.withdraw(pin, amount);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#getEnterMessage()
	 */
	@Override
	public String getOperationMessage() {
		return "Withdraw money";
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#getResultMessage(int)
	 */
	@Override
	public String getResultMessage(int result) {
		if(result > -1)
			return "Withdraw successfull";
		
		return "ERROR: Not enough money to withdraw!";
	}	
}