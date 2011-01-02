package cz.cvut.fit.dpo.atm.process.state;

import cz.cvut.fit.dpo.atm.process.Process;
import cz.cvut.fit.dpo.atm.process.ProcessState;
import cz.cvut.fit.dpo.atm.ui.UI;


// MI-DPO: State (Concrete State)

/**
 * State for situation when user is logged out (just press OK).
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class LogoutState
	implements ProcessState {

	private String msg = "Thank you and see again";
	
	/**
	 * Construct logout state with standard message.
	 */
	public LogoutState() {
	}
	
	/**
	 * Construct logout state with special message.
	 * @param msg		special message to be shown instead of standard one.
	 */
	public LogoutState(String msg) {
		this.msg = msg;
	}
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.process.ProcessState#script(cz.cvut.fit.dpo.atm.process.ProcessContext)
	 */
	@Override
	public void script(Process context) {
		UI ui = context.getUI();
		
		ui.write(msg + "\n\n" + "Please press \"OK\" and remove card");
		ui.read();
		
		context.setState(new LoginState());
	}
}