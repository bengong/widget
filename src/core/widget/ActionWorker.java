package core.widget;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.http.NameValuePair;

import core.Result;

public class ActionWorker<T extends Action, V extends Worker>  extends SwingWorker<Result, Void> {

	protected T action;
	protected V worker;
	
	public ActionWorker() {
		super();
	}
	
	public ActionWorker(T action, V worker) {
		super();
		
		this.action = action;
		this.worker = worker;
	}
	
	protected Action action() {
		return action;
	}

	protected Worker worker() {
		return worker;
	}
	
	/**
	 * 非EDT线程运行。
	 * 
	 * @return
	 */
	public final Result doInBackground() {
		try {
			return worker().doInBackground(params());
		} catch (final Throwable e) {
			e.printStackTrace();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					action().throwable(e);
				}
			});

			return Result.exception(e);
		}
	}
		
	protected final List<NameValuePair> params() {
		return action().params();
	}
	
	@Override
	protected void done() {
		try {
			// 无论成功与否，均重置回icon, 并再次激活。
			action().setEnabled(true);
			
			Result result = get();
			// 只有得到正确的结果，方可执行doneInEDT事件。否则将自行处理异常。
			if(result != null) {
				boolean isContinue = action().result(result);
				if(isContinue) {
					action().doneInEDT(result);
					worker().doneInEDT(result);
				}
			}
		} catch(Exception e) {
			action().exception(e);
		}  finally {
			action().doneFinally();
			worker().doneFinally();
		}
	}
}
