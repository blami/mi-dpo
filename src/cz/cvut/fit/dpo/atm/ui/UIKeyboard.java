package cz.cvut.fit.dpo.atm.ui;


// MI-DPO: Abstract Factory (Abstract Product)

/**
 * Interface implemented by objects acting as ATM keyboard.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public interface UIKeyboard {
	
	/**
	 * Initialize ATM's keyboard object.
	 */
	public void initialize();
	
	/**
	 * Read number from ATM's keyboard.
	 * @return			number read from ATM's keyboard
	 */
	public int read();
}
