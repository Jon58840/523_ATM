package sm;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import simulatedInput.FakeCards;

public class Gui implements KeyListener {

	public static final int FONT_SIZE = 24;

	private JFrame frame;
	private JLabel msgLabel;

	private final CardScannerInterface cardScanner;
	private final KeypadInterface keypad;

	public Gui(CardScannerInterface csi, KeypadInterface keypadInterface) {
		this.cardScanner = csi;
		this.keypad = keypadInterface;
		createAndShowGUI(); // can also be made public and called from
							// outside...
	}

	private void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("ATM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(800, 600));

		frame.addKeyListener(this);

		Container pane = frame.getContentPane();

		msgLabel = new JLabel("booting");
		msgLabel.setFont(new Font(msgLabel.getFont().getName(), Font.PLAIN, FONT_SIZE));
		msgLabel.setHorizontalAlignment(JLabel.CENTER);
		msgLabel.setVerticalAlignment(JLabel.CENTER);
		pane.add(msgLabel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	public void setMonitorMessage(String ... msgs) {
		StringBuilder sb = new StringBuilder("<html>");//"<html>Welcome!<br>Second line</html>"
		for (String line : msgs) {
			sb.append("<p align=\"center\">");
			sb.append(line);
			sb.append("</p>");
		}
		sb.append("</html>");
		this.msgLabel.setText(sb.toString());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_0:
			keypad.dataButtonPressed(0);
			break;
		case KeyEvent.VK_1:
			keypad.dataButtonPressed(1);
			break;
		case KeyEvent.VK_2:
			keypad.dataButtonPressed(2);
			break;
		case KeyEvent.VK_3:
			keypad.dataButtonPressed(3);
			break;
		case KeyEvent.VK_4:
			keypad.dataButtonPressed(4);
			break;
		case KeyEvent.VK_5:
			keypad.dataButtonPressed(5);
			break;
		case KeyEvent.VK_6:
			keypad.dataButtonPressed(6);
			break;
		case KeyEvent.VK_7:
			keypad.dataButtonPressed(7);
			break;
		case KeyEvent.VK_8:
			keypad.dataButtonPressed(8);
			break;
		case KeyEvent.VK_9:
			keypad.dataButtonPressed(9);
			break;
		case KeyEvent.VK_INSERT:
			cardScanner.inputCard(FakeCards.getNextFakeCardNumber());
			//System.out.println("CARD INSERTED");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
	}

}
