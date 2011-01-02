package cz.cvut.fit.dpo.atm.process.state;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.process.ProcessState;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (Concrete State)

/**
 * State for situation when customer wants login (enter PIN).
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class LoginState
	implements ProcessState {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.ProcessState#script(cz.cvut.fit.dpo.atm.process.ProcessContext)
	 */
	@Override
	public void script(Process context) {
		UI ui = context.getUI();
		BankService bank = context.getBankService();
		
		int pin;
		
		// display PIN request
		ui.write("Wellcome in " + bank.getName() + "\n\n"
			+ "ENTER PIN:");
		pin = ui.read();
		
		// verify PIN
		if(bank.authorize(pin)) {
			context.setState(new MenuState(pin));
		} else { 
			context.setState(new LogoutState("ERROR: Invalid PIN!"));
		}
	}
}