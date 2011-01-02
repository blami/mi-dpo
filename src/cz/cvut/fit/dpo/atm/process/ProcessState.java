package cz.cvut.fit.dpo.atm.process;


// MI-DPO: State (State)

/**
* Interface implemented by all process states.
* @author Ondřej Baláž <balazond@fit.cvut.cz>
*/
public interface ProcessState {
	
	/**
	 * Script to be done when ProcessContext enters this state.
	 */
	public void script(Process context);
}
