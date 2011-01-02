package cz.cvut.fit.dpo.atm.ui.swing;

import cz.cvut.fit.dpo.atm.ui.UIKeyboard;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


// MI-DPO: Abstract Factory (Concrete Product)
// MI-DPO: Observer (Subject)

/**
 * Java/Swing ATM keyboard.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
@SuppressWarnings("serial")
public class SwingUIKeyboard extends JFrame
	implements UIKeyboard {
	
	private int n;
	public final Semaphore semaphore = new Semaphore(1, true);
	
	/**
	 * List of subscribed observers of prompt input.
	 */
	private ArrayList<SwingUIKeyboardObserver> observers
		= new ArrayList<SwingUIKeyboardObserver>();
	
	// Abstract Factory pattern
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIKeyboard#initialize()
	 */
	@Override
	public void initialize() {
		// initialize Swing dialog
		setTitle("Swing ATM Keyboard");
		setSize(320, 240);
		setResizable(false);		
		
		// do nothing on close
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// setup controls
		setLayout(new GridLayout(4, 4, 4, 4));
		
		for(int i=1; i<=12; i++) {
			String label = "";
			int value = -1;
			
			JButton button = new JButton();
			
			switch(i) {
			// clear button
			case 10:
				label = "CLR";
				
				// add action callback
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clearButtonHandler();
					}
				});				
				break;
			
			// submit button
			case 12:
				label = "OK";
				
				// add action callback
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						submitButtonHandler();
					}
				});					
				break;
			
			// 0-9 buttons			
			case 11:
			default:
				if(i == 11) {
					label = "0";
					value = 0;
				} else {
					label = Integer.toString(i);
					value = i;
				}
				
				// we must dupe value as final due to closures semantics
				final int finalValue = value;
				
				// add action callback
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						numericButtonHandler(finalValue);
					}
				});
			}
			
			button.setText(label);			
			getContentPane().add(button);
		}		
		
		// do not show dialog
		setVisible(false);
	}

	/**
	 * Keyboard numeric button action handler.
	 * @param value		value of pressed button
	 */
	public void numericButtonHandler(int value) {
		if(n == -1) {
			n = value;
		} else {
			n = n * 10 + value;
		}
		
		// poke observers
		pokeObservers();
	}
	
	/**
	 * Keyboard clear button handler.
	 */
	public void clearButtonHandler() {
		n = -1;
		
		// poke observers
		pokeObservers();
	}
	
	/**
	 * Keyboard submit button handler.
	 */
	public void submitButtonHandler() {
		// notify itself to stop waiting in read()
		synchronized(this) {
			notify();
		}
	}	
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIKeyboard#read()
	 */
	@Override
	public int read() {
		n = -1;
		setVisible(true);
		
		// wait here and let user
		try {
			synchronized(this) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	// Observer pattern
	
	/**
	 * Subscribe observer to get pokes about prompt input state changes.
	 * @param observer		observer to subscribe
	 */
	public void subscribe(SwingUIKeyboardObserver observer) {
		// check to avoid multiple subscriptions of single object
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	/**
	 * Unsubscribe observer from getting pokes about prompt input state changes.
	 * @param observer		observer to unsubscribe
	 */
	public void unsubscribe(SwingUIKeyboardObserver observer) {
		// NOTE: just to adhere with pattern. Really not needed
		observers.remove(observer);
	}
	
	/**
	 * Poke all subscribed observers with prompt input state.
	 */
	private void pokeObservers() {
		for(SwingUIKeyboardObserver observer : observers) {
			observer.poke(n);
		}
	}
}
