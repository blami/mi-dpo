package cz.cvut.fit.dpo.atm.ui;


// MI-DPO: Abstract Factory (Abstract Product)

/**
 * Interface implemented by objects acting as ATM screen.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public interface UIScreen {
	
	/**
	 * Initialize ATM's screen object.
	 */
	public void initialize();
	
	/**
	 * Write message to ATM's screen.
	 * @param msg		message to be written on ATM's screen
	 */
	public void write(String msg);
}