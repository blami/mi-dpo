package cz.cvut.fit.dpo.atm.bank;


// MI-DPO: Decorator (Abstract Decorator)

/**
* Bank service decorator.
* @author Ondřej Baláž <balazond@fit.cvut.cz>
*/
public abstract class BankAbstractDecorator
	implements BankService {

	/**
	 * Decorated bank object.
	 */
	protected BankService decoratedBank = null;
	
	// Decorator pattern	
	
	/**
	 * Constructs decorator around existing object implementing BankService
	 * interface.
	 * @param bank			decorated bank
	 */
	public BankAbstractDecorator(BankService bank) {
		decoratedBank = bank;
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#getName()
	 */
	@Override
	public String getName() {
		return decoratedBank.getName();
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#authorize(int)
	 */
	@Override
	public boolean authorize(int pin) {
		return decoratedBank.authorize(pin);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#getBalance(int)
	 */
	@Override
	public int getBalance(int pin) {
		return decoratedBank.getBalance(pin);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#getDetailedBalance(int)
	 */
	@Override
	public String getDetailedBalance(int pin) {
		return decoratedBank.getDetailedBalance(pin);
	}	
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#withdraw(int, int)
	 */
	@Override
	public int withdraw(int pin, int amount) {
		return decoratedBank.withdraw(pin, amount);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.bank.BankService#deposit(int, int)
	 */
	@Override
	public int deposit(int pin, int amount) {
		return decoratedBank.deposit(pin, amount);
	}

}
