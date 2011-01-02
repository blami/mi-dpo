package cz.cvut.fit.dpo.atm.process.state;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.process.ProcessState;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (Concrete State)

/**
 * State for situation when customer requests account balance.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class BalanceState
	implements ProcessState {
	
	private int pin;
	
	/**
	 * Construct balance state.
	 */
	public BalanceState(int pin) {
		this.pin = pin;
	}
		
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.ProcessState#script(cz.cvut.fit.dpo.atm.process.ProcessContext)
	 */
	@Override
	public void script(Process context) {
		UI ui = context.getUI();
		BankService bank = context.getBankService();
		
		ui.write(bank.getDetailedBalance(pin) + "\n\n" + "Please press \"OK\" to return");
		ui.read();
		
		context.setState(new MenuState(pin));
	}
}