package cz.cvut.fit.dpo.atm.process.state;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.Operation;
import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.process.ProcessState;
import cz.cvut.fit.dpo.atm.process.strategy.DepositStrategy;
import cz.cvut.fit.dpo.atm.process.strategy.WithdrawStrategy;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (Concrete State)

/**
* State for situation when user is navigating menu.
* @author Ondřej Baláž <balazond@fit.cvut.cz>
*/
public class MenuState 
	implements ProcessState {
	
	/**
	 * Authorized customer PIN.
	 */
	private int pin = 0;
	
	/**
	 * Construct MenuState and set pin to authorized user's PIN.
	 * @param pin			authorized user PIN	
	 */
	public MenuState(int pin) {
		this.pin = pin;
	}
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.ProcessState#script(cz.cvut.fit.dpo.atm.process.ProcessContext)
	 */
	@Override
	public void script(Process context) {
		UI ui = context.getUI();
		BankService bank = context.getBankService();
		
		Operation operation = new Operation();
		
		int choice = 0;
		
		while(choice < 1 || choice > 4) {
			// display menu
			ui.write("Menu of " + bank.getName() + "\n\n"
				+ "1. Balance\n"
				+ "2. Withdraw\n"
				+ "3. Deposit\n"
				+ "4. Quit\n\n"
				+ "YOUR CHOICE:");
		
			// read menu choice
			choice = ui.read();
	
			switch(choice) {
			case 1:
				context.setState(new BalanceState(pin));
				break;
			case 2:
			case 3:
				// dirty way, this should be encapsulated
				if (choice == 2)
					operation.setStrategy(new WithdrawStrategy());
				else 
					operation.setStrategy(new DepositStrategy());
				
				context.setState(new OperationState(pin, operation));
				break;
			case 4:
				context.setState(new LogoutState());
				break;
			}
		}
	}
}
