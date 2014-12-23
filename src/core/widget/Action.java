package core.widget;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

import net.miginfocom.swing.MigLayout;

import org.apache.http.NameValuePair;

import core.Result;
import core.provider.MessageDisplayer;
import core.provider.WorkerExecutor;

/**
 * 可执行动作组件。
 */
public class Action extends javax.swing.JPanel implements MouseListener, MouseWheelListener, MouseMotionListener, MouseInputListener, FocusListener, ActionListener {

	public JLabel title;
	public JLabel icon;
	
	private MessageDisplayer displayer;
	private Worker worker;
	private WorkerExecutor executor;
	
	private static Color normal = new Color(245,245,245);
	private static Color entered = new Color(230,230,230);
	private static Color pressed = new Color(220,220,220);
	
	public Action() {
		setLayout(new MigLayout("", "[grow][]", "[grow]"));

		this.setBorder(BorderFactory.createLineBorder(new Color(181,181,181)));
		
		title = new JLabel("確認");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		add(title, "cell 0 0,grow");
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		
		addFocusListener(this);
	}
	
	public Action(String text) {
		this();
		
		title.setText(text);
	}
	
	public Action(Worker worker) {
		this();
		
		setWorker(worker);
	}
	
	/**
	 * 處理前校驗。
	 * 
	 * @return
	 */
	public boolean verifiedInEDT() {
		
		return true;
	}
	
	/**
	 * 處理後。
	 */
	protected void doneInEDT(Result result) {
		
	}
	
	/**
	 * 無論成功與否，均執行處理。
	 */
	protected void doneFinally() {
		setEnabled(true);
	}
	
	protected List<NameValuePair> params() {
		return new ArrayList<NameValuePair>();
	}
	
	protected MessageDisplayer displayer() {
		if(displayer == null) {
			displayer = createDisplayer();
		}
		
		return displayer;
	}
	
	protected MessageDisplayer createDisplayer() {
		return Register.get(MessageDisplayer.class);
	}
	
	protected WorkerExecutor executor() {
		if(executor == null) {
			executor = Register.get(WorkerExecutor.class);
		}
		
		return executor;
	}

	public void setExecutor(WorkerExecutor executor) {
		this.executor = executor;
	}

	protected Worker worker() {
		if(worker == null) {
			worker = new Worker();
		}
		
		return worker;
	}

	
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public Action(Icon icon, String text) {
		this();
		
		title.setIcon(icon);
		title.setText(text);
	}
	
	public Action(String text, Icon image) {
		this();
				
		title.setText(text);
		
		icon = new JLabel(image);
		add(icon, "cell 1 0,grow");
	}
	
	public void setFont(Font font) {
		super.setFont(font);
		if(title != null) {
			title.setFont(font);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		execute();
	}
	
	public void mouseClicked(MouseEvent e) {
		execute();
	}
	
	public void execute() {
		// 如果disable，直接返回。
		if(!isEnabled()) return;
		
		// 清空
		displayer().reset();
		// 根据校验结果，判断是否继续开启后台线程执行任务。
		boolean isContinue = false;
		try {
			isContinue = verifiedInEDT() && worker().verifiedInEDT();
			
			if(isContinue) {
				// 置灰，避免多次点击。
				setEnabled(false);
				executor().execute(new ActionWorker(this, worker()));
			}
		} catch (Exception e) {
			exception(e);
			return;
		}
	}
	
	public boolean result(Result result) {		
		if(result.isSuccess()) {
			// displayer().success(result.message);
			return true;
		} else if(result.isWarn()) {
			displayer().warn(result.message);
			return false;
		} else if(result.isError()) {
			displayer().error(result.message);
			return false;
		} else if(result.isThrowable()) {
			displayer().throwable(result.throwable);
			return false;
		} else {
			displayer().message(result.message);
			return false;
		}
	}
	
	public void exception(Exception e) {
		displayer().exception(e);
	}
	
	public void throwable(Throwable e) {
		displayer().throwable(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!isEnabled()) return;
		
		setBackground(pressed);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(!isEnabled()) return;
		
		setBackground(normal);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!isEnabled()) return;
		
		setBackground(entered);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!isEnabled()) return;
		
		setBackground(normal);
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(!isEnabled()) return;
		
		setBackground(entered);
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(!isEnabled()) return;
		
		setBackground(normal);
	}
}
