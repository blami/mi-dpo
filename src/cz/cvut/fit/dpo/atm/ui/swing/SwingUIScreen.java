package cz.cvut.fit.dpo.atm.ui.swing;

import cz.cvut.fit.dpo.atm.ui.UIScreen;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;


// MI-DPO: Abstract Factory (Concrete Product)
// MI-DPO: Observer (Concrete Observer)

/**
 * Console (stdout) ATM screen.
 * @author Ondřej Baláž <balazond@fit.cvut.cz>
 */
@SuppressWarnings("serial")
public class SwingUIScreen extends JFrame
	implements UIScreen, SwingUIKeyboardObserver {

	private JLabel label;
	private String msg = "";
	private String promptInput = "";
	
	// Abstract Factory pattern
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIScreen#initialize()
	 */
	@Override
	public void initialize() {
		// initialize Swing dialog
		setTitle("Swing ATM Screen");
		setSize(320, 240);
		setResizable(false);		
		
		// exit on close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// setup controls
		label = new JLabel();
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setBackground(Color.white);
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		// add label to content pane
		getContentPane().add(label);
		
		// show dialog
		setVisible(true);
	}

	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.UIScreen#write(java.lang.String)
	 */
	@Override
	public void write(String msg) {
		this.msg = msg;
		
		// with new message clear promptInput
		promptInput = "";

		// refresh screen
		refresh();
	}

	// Observer pattern
	
	/* (non-Javadoc)
	 * @see cz.cvut.fit.dpo.atm.ui.swing.SwingUIInputObserver#poke(int)
	 */
	@Override
	public void poke(int n) {
		if(n == -1) {
			promptInput = "";
		} else {
			promptInput = Integer.toString(n);
		}
		
		//refresh screen
		refresh();
	}
	
	/**
	 * Refresh ATM screen text.
	 */
	private void refresh() {
		// FIXME dirty, maybe adapter pattern?
		msg = msg.replaceAll("\n", "<br>");
		label.setText("<html><center>" + msg + "<br>" + promptInput + "</center></html>");		
	}
}
