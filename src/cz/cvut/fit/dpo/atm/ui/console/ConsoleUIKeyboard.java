package cz.cvut.fit.dpo.atm.ui.console;

import cz.cvut.fit.dpo.atm.ui.UIKeyboard;
import java.io.BufferedReader;
import java.io.InputStreamReader;


// MI-DPO: Abstract Factory (Concrete Product)

/**
 * Console (stdin) ATM keyboard.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class ConsoleUIKeyboard
	implements UIKeyboard {

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIKeyboard#initialize()
	 */
	@Override
	public void initialize() {
		// does not need to be initialized
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIKeyboard#read()
	 */
	@Override
	public int read() {
		int n = -1;

		// BFU-proof stdin read loop
		while(n < 0) {
			try {
				BufferedReader in = new BufferedReader(
					new InputStreamReader(System.in));
				String strInput = in.readLine();
				n = Integer.parseInt(strInput);
			} catch(Exception e) {
				System.err.println("ERROR: Invalid input.");				
			}
		}
		
		return n;
	}
}
