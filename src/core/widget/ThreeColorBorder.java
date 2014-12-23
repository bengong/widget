package core.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class ThreeColorBorder extends AbstractBorder {

	private Color normal = Colors.border;
	private Color focus = Colors.borderFocus;
	private Color disable = Colors.borderDisable;
	private Insets insets = new Insets(1, 1, 1, 1);
	
	public ThreeColorBorder() {
		super();
	}
	
	public ThreeColorBorder(Color normal, Color focus, Color disable) {
		super();
		this.normal = normal;
		this.focus = focus;
		this.disable = disable;
	}

	public ThreeColorBorder(Color normal, Color focus, Color disable, Insets insets) {
		super();
		this.normal = normal;
		this.focus = focus;
		this.disable = disable;
		this.insets = insets;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		return insets;
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Color color = normal;
		if (!c.isEnabled()) {
			color = disable;
		} else if (c.isFocusOwner()) {
			color = focus;
		}
		Color oldColor = g.getColor();
		g.setColor(color);
		
		// 僅畫下劃線。
		// g.drawRect(x, y, width - 1, height - 1);
		g.drawLine(x, y+height-1, x+width, y+height-1);
		g.setColor(oldColor);
	}

}
