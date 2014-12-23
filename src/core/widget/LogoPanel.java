package core.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LogoPanel extends JPanel {

	private JLabel icon;

	private Image image;
	
	public LogoPanel() {
		setLayout(new MigLayout("", "0[][grow]0", "0[][40!]0"));
		
		// 設置背景色為白色。
		setBackground(Color.white);
		icon = new JLabel(Images.imageIcon("images/icbc_macau_200.jpg"));
		add(icon, "cell 0 0");
		
		image = Images.image("images/macau_background.jpg");
	}
	
	public void init() {

	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int x = getWidth()-image.getWidth(this);
        int y = getHeight()-image.getHeight(this);
        
        g.drawImage(image, x, y, this);
    }

}
