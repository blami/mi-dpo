package cz.cvut.fit.dpo.atm.ui.console;

import cz.cvut.fit.dpo.atm.ui.UIScreen;


// MI-DPO: Abstract Factory (Concrete Product)

/**
 * Console (stdout) ATM screen.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class ConsoleUIScreen
	implements UIScreen {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIScreen#initialize()
	 */
	@Override
	public void initialize() {
		// does not need to be initialized
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIScreen#write(java.lang.String)
	 */
	@Override
	public void write(String msg) {
		System.out.println(msg);
	}
}
