package core.widget.plaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.UIDefaults;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.metal.MetalBorders.TableHeaderBorder;

import sun.swing.SwingLazyValue;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import core.widget.Borders;
import core.widget.Colors;
import core.widget.Images;
import core.widget.ThreeColorBorder;

public class WindowsLookAndFeelImpl extends WindowsLookAndFeel {

	private static Font font = new Font("Microsoft YaHei", Font.PLAIN, 12);
	
	public WindowsLookAndFeelImpl() {
		super();
	}

	public UIDefaults getDefaults() {
		UIDefaults table = super.getDefaults();
		
		initUIs(table);
		
		// 初始化组件边框。
		initBorders(table);
		// 初始化组件颜色。
		initColors(table);
		// 初始化界面icon。
		initIcons(table);
		// 初始化其他。
		initDefaults(table);
		
		initFonts(table);
		
		return table;
	}
	
	private static void initFonts(UIDefaults table) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration keys = table.keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value;
			try {
				value = table.get(key);
				if (value instanceof FontUIResource) {
					table.put(key, fontRes);
				}
			} catch (Exception e) {
			}
		}
	}
	
	private void initUIs(UIDefaults table) {
		final String packageName = "core.plaf.";
		Object[] uiDefaults = {
				 "ActionButtonUI", packageName + "ActionButtonUI" , 
				 "PrimaryButtonUI", packageName + "PrimaryButtonUI" ,
				 "ImportantButtonUI", packageName + "ImportantButtonUI" , 
				 "MultiLineLabelUI", packageName+"MultiLineLabelUI",
				 "MultiLineToolTipUI", packageName+"MultiLineToolTipUI",
		};

		// table.putDefaults(uiDefaults);
	}
	

	private static void initColors(UIDefaults table) {
		Object[] styles = {

		 "Button.background", new Color(245,245,245) ,

		 "Button.foreground", Color.black ,

		 "Button.select", new Color(225,225,225) ,

		 "RadioButton.background", new Color(245,245,245) ,

		 "RadioButton.foreground", Color.black ,

		 "CheckBox.background", new Color(245,245,245) ,

		 "CheckBox.foreground", Color.black ,

		 "ComboBox.background", new Color(245,245,245) ,

		 "ComboBox.foreground", Color.black ,

		 "ComboBox.selectionForeground", Color.white ,

		 "ComboBox.selectionBackground", Colors.select ,

		 "ComboBox.buttonBackground", Color.white ,

		 "ComboBox.inactivebuttonBackground", Colors.unenable ,

		 "ComboBox.disabledBackground", Colors.unenable ,

		 "ComboBox.disabledForeground", Color.black ,

		 "ComboBox.buttonBorderColor", Colors.border ,

		 "InternalFrame.activeTitleBackground", new java.awt.Color(52, 102, 153) ,

		 "InternalFrame.activeTitleForeground", Color.white ,

		 "InternalFrame.inactiveTitleBackground", new java.awt.Color(32, 82, 133) ,

		 "InternalFrame.inactiveTitleForeground", Color.gray ,

		 "Desktop.background", Colors.background ,

		 "Label.foreground", Color.black ,

		 "Label.disabledForeground", new Color(0X878787) ,

		 "Label.notnullforeground", new Color(52, 102, 153) ,

		 "List.selectionForeground", Color.black ,

		 "List.selectionBackground", new Color(0Xfdf2c5) ,

		 "MenuItem.background", new Color(0XCEE0EC) ,

		 "MenuItem.foreground", Color.black ,

		 "MenuItem.selectionForeground", Color.black ,

		 "MenuItem.selectionBackground", new Color(0X7D9BB8) ,

		 "MenuItem.disabledForeground", new Color(0X848484) ,

		 "MenuItem.acceleratorForeground", Color.black ,

		 "MenuItem.acceleratorSelectionForeground", Color.black ,

		 "MessageDialog.bgcolor", new Color(0XEFF6FC) ,

		 "MessageDialog.linecolor", new Color(0X7F9DB9) ,

		 "RadioButtonMenuItem.background", new Color(0XCEE0EC) ,

		 "RadioButtonMenuItem.foreground", Color.black ,

		 "RadioButtonMenuItem.selectionForeground", Color.black ,

		 "RadioButtonMenuItem.selectionBackground", new Color(0X7D9BB8) ,

		 "RadioButtonMenuItem.disabledForeground", new Color(0X848484) ,

		 "RadioButtonMenuItem.acceleratorForeground", Color.black ,

		 "RadioButtonMenuItem.acceleratorSelectionForeground", Color.black ,

		 "CheckBoxMenuItem.background", new Color(0XCEE0EC) ,

		 "CheckBoxMenuItem.foreground", Color.black ,

		 "CheckBoxMenuItem.selectionForeground", Color.black ,

		 "CheckBoxMenuItem.selectionBackground", new Color(0X7D9BB8) ,

		 "CheckBoxMenuItem.disabledForeground", new Color(0X848484) ,

		 "CheckBoxMenuItem.acceleratorForeground", Color.black ,

		 "CheckBoxMenuItem.acceleratorSelectionForeground", Color.black ,

		 "OptionPane.background", Colors.background ,

		 "Panel.background", Colors.background ,

		 "Panel.fromColor", new Color(0xFFFFFF) ,

		 "Panel.toColor", new Color(0xF3F2F2) ,

		 "ProgressBar.foreground", new Color(0X7C8CD3) ,

		 "ProgressBar.background", Color.WHITE ,

		 "Separator.background", new Color(0XCCD3DB) ,

		 "Separator.foreground", new Color(0X6D7B8A) ,

		 "ScrollBar.background", new Color(0XDCDFE3) ,

		 "ScrollBar.track", new Color(0XDCDFE3) ,

		 "control", new Color(0XC4CCD3) ,// 解决上下箭头按钮的背景色

		 "controlShadow", new Color(0XC4CCD3) ,// 解决上下箭头按钮的背景色

		 "controlLtHighlight", new Color(0XC4CCD3) ,// 解决上下箭头按钮的背景色

		 "ScrollBar.thumb", new Color(0XC4CCD3) ,

		 "ScrollBar.thumbHighlight", Color.white ,

		 "ScrollBar.thumbDarkShadow", new Color(0X6C6C6C) ,

		 "ScrollBar.thumbLightShadow", new Color(0XC4CCD3) ,

		 "ScrollPane.background", Colors.background ,

		 "Spinner.background", Colors.background ,

		 "Viewport.background", Color.white ,

		 "TabbedPane.selected", new Color(0XF4F3F3) ,

		 "TabbedPane.background", new Color(0XB6B6B6) ,

		 "Table.selectionForeground", Color.black ,

		 "Table.selectionBackground", new Color(0Xfdf2c5) ,

		 "Table.background", Color.WHITE ,

		 "Table.gridColor", new Color(0Xd0d7e5) ,

		 "Table.borderColor", new Color(0X9eb6ce) ,// 表体边框颜色

		 "TableHeader.lineColor", new Color(0X9eb6ce) ,// 表头竖线颜色

		 "TableHeader.background", Colors.background ,// new Color(0XE2E2E2)

		 "TextField.background", Color.white ,

		 "TextField.foreground", Color.black ,

		 "TextField.selectionBackground", Colors.select ,

		 "TextField.selectionForeground", Color.white ,

		 "TextField.inactiveForeground", Color.black ,

		 "TextField.inactiveBackground", Colors.unenable ,

		 "PasswordField.background", Color.WHITE ,

		 "PasswordField.foreground", Color.black ,

		 "PasswordField.selectionBackground", Colors.select ,

		 "PasswordField.selectionForeground", Color.white ,

		 "PasswordField.inactiveForeground", new Color(0XA1A192) ,

		 "PasswordField.inactiveBackground", Colors.unenable ,

		 "TextArea.background", Color.white ,

		 "TextArea.foreground", Color.black ,

		 "TextArea.selectionBackground", Colors.select ,

		 "TextArea.selectionForeground", Color.white ,

		 "TextArea.inactiveForeground", new Color(0XA1A192) ,

		 "TextArea.inactiveBackground", Colors.unenable ,

		 "ToolBar.background", Colors.background ,

		 "ToolTip.background", new Color(0XFFFFE1) ,

		 "ToolTip.foreground", Color.black ,

		 "Tree.background", Color.white ,

		 "Tree.line", new Color(0XC7C7C7) ,

		 "Tree.hash", new Color(0XC7C7C7) ,

		 "Tree.textForeground", Color.black ,

		 "Tree.textBackground", Color.white ,

		 "Tree.selectionForeground", Color.white ,

		 "Tree.selectionBackground", new Color(0X356799) ,

		 "Tree.selectionBorderColor", new Color(0X356799) , };

		table.putDefaults(styles);
	}

	private static void initBorders(UIDefaults table) {
		Object[] styles = {

		 "Button.border", LineBorder.createGrayLineBorder() ,

		 "CheckBox.border", BorderFactory.createLineBorder(Colors.border, 1) ,

		 "ComboBox.border", new ThreeColorBorder() ,

		 "List.focusCellHighlightBorder", new SwingLazyValue("javax.swing.plaf.BorderUIResource$LineBorderUIResource", null, new Object[]  {new ColorUIResource(new Color(0xf9cd40))} ) ,

		 "PopupMenu.border", BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(0X2C5278), new Color(0XCEE0EC), Color.white, new Color(0X2C5278)) ,

		 "OptionPane.messageAreaBorder", BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black) ,

		 "OptionPane.buttonAreaBorder", BorderFactory.createMatteBorder(1, 0, 0, 0, Color.white) ,

		 "ScrollBar.border", BorderFactory.createMatteBorder(1, 1, 0, 0, Color.white) ,

		 "ScrollPane.border", null ,

		 "ScrollPane.viewportBorder", null ,

		 "Spinner.border", Borders.lineBorder ,

		 "Spinner.arrowButtonBorder", Borders.lineBorder ,

		 "Table.scrollPaneBorder", BorderFactory.createLineBorder(Colors.border, 1) ,

		 "Table.focusCellHighlightBorder", BorderFactory.createLineBorder(Color.black) ,

		 "TableHeader.cellBorder", new TableHeaderBorder() ,

		 "TextField.border", new ThreeColorBorder() ,

		 "PasswordField.border", new ThreeColorBorder() ,

		 "TextArea.border", BorderFactory.createLineBorder(Colors.border, 1) ,

		 "ToolTip.border", BorderFactory.createLineBorder(new Color(0X7A7A7A)) , 
		 };

		table.putDefaults(styles);
	}

	private static void initIcons(UIDefaults table) {
		Object[] styles = {
		 "Tree.openIcon", imageIcon("images/opened.gif") ,
		 "Tree.closedIcon", imageIcon("images/closed.gif") ,
		 "Tree.leafIcon", imageIcon("images/leaf.gif") ,
		 "Tree.expandedIcon", imageIcon("images/treeexpand.gif") ,
		 "Tree.collapsedIcon", imageIcon("images/treecollapse.gif") , 
		};

		table.putDefaults(styles);
	}

	private static void initDefaults(UIDefaults table) {
		Object[] styles = {

		 "ClassLoader", Thread.currentThread().getContextClassLoader() ,

		 "Button.textIconGap", new Integer(2) ,

		 "Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[] { "SPACE", "pressed", "released SPACE", "released", "ENTER", "pressed", "released ENTER", "released"} ) ,

		 "CheckBox.focus", Colors.focus ,

		 "InternalFrame.titleFont", font.deriveFont(Font.BOLD).deriveFont(14) ,

		 "MenuItem.acceleratorDelimiter", "+" ,

		 "RadioButtonMenuItem.acceleratorDelimiter", "+" ,

		 "CheckBoxMenuItem.acceleratorDelimiter", "+" ,

		 "Spinner.arrowButtonInsets", new Insets(1, 0, 1, 1) ,

		 "Spinner.arrowButtonSize", new Dimension(17, 8) ,

		 "SplitPane.dividerSize", new Integer(2) ,

		 "TabbedPane.selectedTabPadInsets", new InsetsUIResource(1, 2, 2, 1) ,

		 "PasswordField.echoChar", (char) 9679 ,

		 "Tree.leftChildIndent", new Integer(5) ,

		 "Tree.rightChildIndent", new Integer(10) ,

		 "Tree.drawDashedFocusIndicator", Boolean.TRUE ,

		 "Tree.drawsFocusBorderAroundIcon", Boolean.FALSE , };

		table.putDefaults(styles);
	}

	private static ImageIcon imageIcon(String name) {
		return Images.imageIcon(name);
	}
}
