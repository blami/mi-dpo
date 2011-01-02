package cz.cvut.fit.dpo.atm.ui.swing;


// MI-DPO: Observer (Observer) 

/**
 * SwingUI input observer is an interface that must be implemented by any object
 * interested in input changes from SwingUIKeyboard object (which acts as 
 * Subject).
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public interface SwingUIKeyboardObserver {
	/**
	 * Poke object with prompt input state
	 * @param n			input state
	 */
	public void poke(int n);
}
