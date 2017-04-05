package sm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Gui implements KeyListener {

	public static final int FONT_SIZE = 24;

	private JFrame frame;
	private JLabel msgLabel;

	private JTextField cardNumTxt;

	private final CardScannerInterface cardScanner;
	private final Keypad keypad;
	private final CashDisburser disburser;
	private Monitor monitor;

	public Gui(CardScannerInterface csi, Keypad keypadSimulationInterface, CashDisburser disburser) {
		this.cardScanner = csi;
		this.keypad = keypadSimulationInterface;
		this.disburser = disburser;
		createAndShowGUI();
		createDeviceSimulatorGUI();
		frame.toFront();
		frame.requestFocus();
	}

	/**
	 * Creates GUI of the ATM.
	 */
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

	/**
	 * Creates the GUI used to simulate the devices
	 */
	private void createDeviceSimulatorGUI() {

		JFrame deviceControl = new JFrame("Devices");

		deviceControl.addKeyListener(this);

		Container pane = deviceControl.getContentPane();

		pane.setLayout(new GridLayout(5, 3));

		pane.add(new JLabel("Keypad:"));
		ButtonGroup group = new ButtonGroup();
		JRadioButton keypadOkBtn = new JRadioButton("working");
		keypadOkBtn.setSelected(true);
		keypadOkBtn.addActionListener(e -> keypad.setKeypadStatus(true));
		JRadioButton keypadFaultyBtn = new JRadioButton("faulty");
		keypadFaultyBtn.addActionListener(e -> keypad.setKeypadStatus(false));
		group.add(keypadOkBtn);
		pane.add(keypadOkBtn);
		group.add(keypadFaultyBtn);
		pane.add(keypadFaultyBtn);

		pane.add(new JLabel("CardScanner:"));
		group = new ButtonGroup();
		JRadioButton scannerOkBtn = new JRadioButton("working");
		scannerOkBtn.setSelected(true);
		scannerOkBtn.addActionListener(e -> cardScanner.setCardReaderStatus(true));
		JRadioButton scannerFaultyBtn = new JRadioButton("faulty");
		scannerFaultyBtn.addActionListener(e -> cardScanner.setCardReaderStatus(false));
		group.add(scannerOkBtn);
		pane.add(scannerOkBtn);
		group.add(scannerFaultyBtn);
		pane.add(scannerFaultyBtn);

		pane.add(new JLabel("Monitor:"));
		group = new ButtonGroup();
		JRadioButton monitorOkBtn = new JRadioButton("working");
		monitorOkBtn.setSelected(true);
		monitorOkBtn.addActionListener(e -> monitor.setMonitorStatus(true));
		JRadioButton monitorFaultyBtn = new JRadioButton("faulty");
		monitorFaultyBtn.addActionListener(e -> monitor.setMonitorStatus(false));
		group.add(monitorOkBtn);
		pane.add(monitorOkBtn);
		group.add(monitorFaultyBtn);
		pane.add(monitorFaultyBtn);

		pane.add(new JLabel("Disburser:"));
		group = new ButtonGroup();
		JRadioButton disbuserOkBtn = new JRadioButton("working");
		disbuserOkBtn.setSelected(true);
		disbuserOkBtn.addActionListener(e -> disburser.setCashDisburserStatus(true));
		JRadioButton disburserFaultyBtn = new JRadioButton("faulty");
		disburserFaultyBtn.addActionListener(e -> disburser.setCashDisburserStatus(false));
		group.add(disbuserOkBtn);
		pane.add(disbuserOkBtn);
		group.add(disburserFaultyBtn);
		pane.add(disburserFaultyBtn);

		pane.add(new JLabel("Card number:"));
		cardNumTxt = new JTextField("1234");
		cardNumTxt.setInputVerifier(new CardNumberInputVerifier());
		pane.add(cardNumTxt);
		JButton insertCardBtn = new JButton("Insert");
		insertCardBtn.addActionListener(e -> insertCard());
		pane.add(insertCardBtn);

		deviceControl.pack();
		deviceControl.setLocation(frame.getX() + frame.getWidth(),
				frame.getY() + frame.getHeight() / 2 - deviceControl.getHeight() / 2);
		deviceControl.setVisible(true);
	}

	public void setMonitorMessage(String... msgs) {
		StringBuilder sb = new StringBuilder("<html>");
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
		// System.out.println(e.getKeyCode());
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
			insertCard();
			break;
		case KeyEvent.VK_ENTER:
			keypad.setEnterButtonPressed(true);
			break;
		case KeyEvent.VK_ESCAPE:
			keypad.setCancelButtonPressed(true);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
	}

	private void insertCard() {
		int accNum = AccountDatabase.INVALID_ACCOUNT_NUMBER;
		try {
			accNum = Integer.parseInt(cardNumTxt.getText());
		} catch (NumberFormatException e) {
			// do nothing
		}
		if (accNum != AccountDatabase.INVALID_ACCOUNT_NUMBER && Account.isValidAccountNumber(accNum)) {
			if (!cardScanner.isCardInsertEngine()) {
				cardScanner.inputCard(accNum);
			} else {
				JOptionPane.showMessageDialog(frame,
						"Card could not be inserted as there is already a card in the scanner.", "Card error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frame, cardNumTxt.getText() + " is not a legal account number. Change!",
					"Input error", JOptionPane.ERROR_MESSAGE);
		}

	}

	void setMonitor(Monitor m) {
		this.monitor = m;
	}

	public class CardNumberInputVerifier extends InputVerifier {
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText();
			try {
				int value = Integer.parseInt(text);
				return Account.isValidAccountNumber(value);
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}

}
