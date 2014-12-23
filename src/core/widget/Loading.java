package core.widget;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * 正在加载...
 */
public class Loading extends JPanel {

	public Gif gif;
	public JLabel message;
	
	public Loading() {
		setLayout(new MigLayout("", "[grow][32!][grow]", "[grow][32!][][grow]"));
		gif = new Gif(new File("images/loading.gif"), 12);
		add(gif, "cell 1 1,grow");
		
		message = new JLabel("Loading...", SwingConstants.CENTER);
		add(message, "cell 0 2 3 1,grow");
	}
}
