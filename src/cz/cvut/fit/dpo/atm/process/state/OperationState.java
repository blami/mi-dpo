package cz.cvut.fit.dpo.atm.process.state;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.Operation;
import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.process.ProcessState;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (Concrete State)

/**
 * State for situation when customer requests account balance.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class OperationState
	implements ProcessState {
	
	private int pin;
	private Operation operation;
	
	/**
	 * Construct balance state.
	 * @param pin		authorized customer PIN
	 * @param operation	operation object with properly set operation strategy
	 */
	public OperationState(int pin, Operation operation) {
		this.pin = pin;
		this.operation = operation;
	}
		
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.ProcessState#script(cz.cvut.fit.dpo.atm.process.ProcessContext)
	 */
	@Override
	public void script(Process context) {
		UI ui = context.getUI();
		BankService bank = context.getBankService();
		
		int amount;
		int result;
		
		ui.write(operation.getOperationMessage() + "\n\n" + "ENTER AMOUNT:");
		amount = ui.read();
		
		result = operation.execute(bank, pin, amount);
		
		// validate operation result
		String msg = operation.getResultMessage(result);
		context.setState(new LogoutState(msg));
	}
}