package cz.cvut.fit.dpo.atm.ui;


// MI-DPO: Facade

/**
 * UI facade representing ATM UI built from UI parts objects.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class UI {
	private UIScreen screen;	
	private UIKeyboard keyboard;
	
	/**
	 * Construct UI using given UIFactory.
	 * @param factory	concrete UIFactory 
	 */
	public UI(UIFactory factory) {
		screen = factory.createScreen();
		keyboard = factory.createKeyboard();
	}
	
	/**
	 * Initialize UI parts.
	 */
	public void initialize() {
		screen.initialize();
		keyboard.initialize();
	}
	
	/**
	 * Read number from ATM's keyboard.
	 * @returns			number read from ATM's keyboard
	 */
	public int read() {
		int n = keyboard.read();
		return n;
	}
	
	/**
	 * Write message to ATM's screen.
	 * @param msg		message to be written on ATM's screen
	 */
	public void write(String msg) {
		screen.write(msg);
	}
}
