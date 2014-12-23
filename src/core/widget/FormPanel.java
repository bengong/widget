package core.widget;

import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class FormPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public FormPanel() {
		setLayout(new MigLayout("", "[][][grow]", "[][][grow]"));
		
		textField = new JTextField();
		add(textField, "cell 0 0");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		add(textField_1, "cell 1 0,growx");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		add(textField_2, "cell 0 1 2 1,growx");
		textField_2.setColumns(10);
	}
}
