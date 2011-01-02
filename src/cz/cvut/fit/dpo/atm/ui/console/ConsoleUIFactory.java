package cz.cvut.fit.dpo.atm.ui.console;

import cz.cvut.fit.dpo.atm.ui.UIFactory;
import cz.cvut.fit.dpo.atm.ui.UIKeyboard;
import cz.cvut.fit.dpo.atm.ui.UIScreen;


// MI-DPO: Abstract Factory (Concrete Product)

/**
 * Console (stdin) ATM UI factory.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class ConsoleUIFactory implements UIFactory {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIFactory#createScreen()
	 */
	@Override
	public UIScreen createScreen() {
		return new ConsoleUIScreen();
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIFactory#createKeyboard()
	 */
	@Override
	public UIKeyboard createKeyboard() {
		return new ConsoleUIKeyboard();
	}

}
