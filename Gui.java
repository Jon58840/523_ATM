import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui implements KeyListener {
	
	public static final int FONT_SIZE = 24;
	
	private JFrame frame;
	private JLabel msgLabel;
	
	private final CardScannerInterface cardScanner;
	
	public Gui(CardScannerInterface csi) {
		this.cardScanner = csi;
		createAndShowGUI(); // can also be made public and called from outside...
	}

	private void createAndShowGUI() {
		
		// Create and set up the window.
		frame = new JFrame("ATM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));
		
		frame.addKeyListener(this);
		
		Container pane = frame.getContentPane();
		
		msgLabel = new JLabel("Welcome");
		msgLabel.setFont(new Font(msgLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));
		msgLabel.setHorizontalAlignment(JLabel.CENTER);
		msgLabel.setVerticalAlignment(JLabel.CENTER);
		pane.add(msgLabel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setMonitorMessage(String msg) {
		this.msgLabel.setText(msg);
	}
	
	
	public static void main(String[] args) {
		Gui g = new Gui(new CardScanner()/*DUMMY*/);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode()) {
		case KeyEvent.VK_0:
			
			break;
		case KeyEvent.VK_INSERT:
			cardScanner.CardInsert(); // TODO is that the right method to call??
			System.out.println("CARD INSERTED");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
	}

}
