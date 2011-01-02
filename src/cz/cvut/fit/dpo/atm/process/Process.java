package cz.cvut.fit.dpo.atm.process;

import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.state.LoginState;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (State Context)

/**
 * Process context that manages ATM states.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class Process {
	
	/**
	 * Bank service to fullfil state script requests.
	 */
	private BankService bank;
	private UI ui;
	
	/**
	 * Current state.
	 */
	private ProcessState state;
	
	// State pattern
	
	/**
	 * Constructs context that manages ATM states and run their scripts on 
	 * bank service.
	 */
	public Process(UI ui, BankService bank) {
		// do necessary wiring
		this.bank = bank;
		this.ui = ui;
		
		// initial state
		state = new LoginState();
	}
	
	/**
	 * Set context state. Normally should be called only by objects implementing
	 * ProcessState interface.
	 * @param state 	do transition to this state 
	 */
	public void setState(ProcessState state) {
		this.state = state;
	}
	
	/**
	 * Do state script.
	 */
	public void script() {
		state.script(this);
	}
	
	/**
	 * Get reference to context's BankService object
	 * @return 			reference to BankService object
	 */
	public BankService getBankService() {
		return bank;
	}
	 
	/**
	 * Get reference to context's UI facade
	 * @return 			reference to UI facade
	 */
	public UI getUI() {
		return ui;
	}
}