package core.provider.impl.internal;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import core.widget.Images;

public class MessageDisplayerPanel extends JPanel {

	private JLabel logoLabel;
	private JLabel messageLabel;
	private JLabel moreLabel;
	
	public MessageDisplayerPanel() {
		setLayout(new MigLayout("", "[][200!][]", "[]"));

		logoLabel = new JLabel("");
		add(logoLabel, "cell 0 0");

		messageLabel = new JLabel("");
		messageLabel.setBorder(null);
		add(messageLabel, "cell 1 0,grow");

		moreLabel = new JLabel("");
		add(moreLabel, "cell 2 0");
	}

	public void reset() {
		logoLabel.setIcon(null);
		logoLabel.setText("");
		
		messageLabel.setText("");

		moreLabel.setText("");
		moreLabel.setVisible(false);
		
		setVisible(false);
	}

	public void exception(Exception exception) {
		logoLabel.setIcon(Images.imageIcon("images/error.gif"));
		messageLabel.setText(exception.getMessage());
		setVisible(true);
	}

	public void throwable(Throwable throwable) {
		logoLabel.setIcon(Images.imageIcon("images/error.gif"));
		messageLabel.setText(throwable.getMessage());
		setVisible(true);
	}

	public void error(String message) {
		logoLabel.setIcon(Images.imageIcon("images/error.gif"));
		messageLabel.setText(message);
		setVisible(true);
	}

	public void warn(String message) {
		logoLabel.setIcon(Images.imageIcon("images/warn.gif"));
		messageLabel.setText(message);
		setVisible(true);
	}

	public void success(String message) {
		logoLabel.setIcon(Images.imageIcon("images/success.gif"));
		messageLabel.setText(message);
		setVisible(true);
	}

	public void message(String message) {
		logoLabel.setIcon(Images.imageIcon("images/info.gif"));
		messageLabel.setText(message);
		setVisible(true);
	}
}
