package cz.cvut.fit.dpo.atm.ui;

// MI-DPO: Abstract Factory (Abstract Factory)

/**
 * Factory interface for UI factories. Provides methods for creating UI parts 
 * like ATM screen and keyboard.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public interface UIFactory {
	public UIScreen createScreen();
	public UIKeyboard createKeyboard();
}
