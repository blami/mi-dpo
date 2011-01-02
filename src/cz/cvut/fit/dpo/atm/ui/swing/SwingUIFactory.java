package cz.cvut.fit.dpo.atm.ui.swing;

import cz.cvut.fit.dpo.atm.ui.UIFactory;
import cz.cvut.fit.dpo.atm.ui.UIKeyboard;
import cz.cvut.fit.dpo.atm.ui.UIScreen;


// MI-DPO: Abstract Factory (Concrete Product)

/**
 * Java/Swing ATM UI factory.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class SwingUIFactory implements UIFactory {

	private SwingUIKeyboard keyboard;
	private SwingUIScreen screen;
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIFactory#createScreen()
	 */
	@Override
	public UIScreen createScreen() {
		screen = new SwingUIScreen();
		
		// do proper SwingUIKeyboardObserver wiring		
		refreshSubscriptions();
		
		return screen;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIFactory#createKeyboard()
	 */
	@Override
	public UIKeyboard createKeyboard() {
		keyboard = new SwingUIKeyboard();
		
		// do proper SwingUIKeyboardObserver wiring		
		refreshSubscriptions();
		
		return keyboard;
	}

	private void refreshSubscriptions() {
		// subscribe all screens to all keyboards		
		if(keyboard != null) {
			keyboard.subscribe(screen);
		}
	}
}
