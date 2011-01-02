package cz.cvut.fit.dpo.atm;

import cz.cvut.fit.dpo.Application;
import cz.cvut.fit.dpo.atm.bank.Bank;
import cz.cvut.fit.dpo.atm.bank.BankOverdraftDecorator;
import cz.cvut.fit.dpo.atm.bank.BankService;
import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.ui.UI;
import cz.cvut.fit.dpo.atm.ui.console.ConsoleUIFactory;
import cz.cvut.fit.dpo.atm.ui.swing.SwingUIFactory;


// MI-DPO: Facade

/**
 * ATM is facade representing an "automated teller machine" built from 
 * other subsystems.  
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class ATM {
	private UI ui = null;
	private BankService bank = null;
	private Process process = null; 
	
	/**
	 * Constructs ATM using configuration read from application-wide registry.
	 */
	public ATM() {
		Application app = Application.getInstance();
		
		// construct UI
		String uiConf = (String)app.getValue("ui");
		if(uiConf == null || uiConf.equalsIgnoreCase("console")) {
			ui = new UI(new ConsoleUIFactory());
		} 
		else if(uiConf.equalsIgnoreCase("swing")) {
			ui = new UI(new SwingUIFactory());
		}
		
		// construct BankService
		bank = new Bank();
		String bankConf = (String)app.getValue("bank");
		if(bankConf != null && bankConf.contains("overdraft")){
			bank = new BankOverdraftDecorator(bank);
		}
		
		// construct Process
		process = new Process(ui, bank);
	}
	
	/**
	 * ATM lifecycle method.
	 */
	public void run() {
		ui.initialize();
		boolean run = true;
		
		// do script and state transitions (infinite)
		while(run) {
			process.script();
		}
	}
}
