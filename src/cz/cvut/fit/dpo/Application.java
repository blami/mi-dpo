package cz.cvut.fit.dpo;

import cz.cvut.fit.dpo.atm.ATM;
import java.util.Hashtable;


// MI-DPO: Singleton

/**
 * Application class represents application.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
public class Application {
	
	/**
	 * Application instance.
	 */
	private static Application instance = null;
	
	/**
	 * Application general purpose registry.
	 */
	private Hashtable<String,Object> registry = new Hashtable<String,Object>(); 

	/**
	 * ATM facade.
	 */
	private ATM atm = null;
	
	// Singleton pattern
	
	/* (non-Javadoc)
	 * Constructs new application instance. Constructor is private to adhere
	 * with singleton pattern.
	 */
	private Application() {
	}
	
	/**
	 * Gets instance.
	 */
	public static Application getInstance() {
		if(instance == null) {
			instance = new Application();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * Disable object cloning to adhere with Singleton pattern.
	 */
	public Object clone()
		throws CloneNotSupportedException {
		// throw standard exception
		throw new CloneNotSupportedException();
	}	
	
	/**
	 * Set value in application-wide registry.
	 * @param key		key
	 * @param value		value
	 */
	public void setValue(String key, Object value) {
		registry.put(key, value);
	}
	
	/**
	 * Get value from application-wide registry.
	 * @param key		key
	 * @returns			value Object or null if such key does not exists
	 */
	public Object getValue(String key) {
		return registry.get(key);
	}
	
	public void run() {
		atm = new ATM();
		atm.run();
	}
	
	// Main
	
	/**
	 * Application entry-point.
	 * @param args		given command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("MI-DPO semestral project\n"
				+ "Author: Ondřej Baláž <balazond@fit.cvut.cz>");
		
		Application app = Application.getInstance();
		
		// process command line arguments
		for(String arg : args) {
			// UI parameters
			if(arg.equalsIgnoreCase("console")) {
				app.setValue("ui", "console");
			}
			else if(arg.equalsIgnoreCase("swing")) {
				app.setValue("ui", "swing");
			}
			
			// Bank parameters
			if(arg.equalsIgnoreCase("overdraft")) {
				app.setValue("bank", "overdraft");
			}
		}
		
		// run ATM application
		app.run();
	}
}