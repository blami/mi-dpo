package cz.cvut.fit.dpo.atm.process.strategy;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.OperationStrategy;


// MI-DPO: Strategy (Concrete Strategy)

/**
 * Operation strategy for deposit operation.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class DepositStrategy 
	implements OperationStrategy {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#execute(cz.cvut.fit.dpo.atm.bank.BankService, int, int)
	 */
	@Override
	public int execute(BankService bank, int pin, int amount) {
		return bank.deposit(pin, amount);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#getMessage()
	 */
	@Override
	public String getOperationMessage() {
		return "Deposit money";
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.OperationStrategy#getResultMessage(int)
	 */
	@Override
	public String getResultMessage(int result) {
		if(result > -1)
			return "Deposit successfull";
		
		return "ERROR: Deposit unsucessfull!";
	}
}