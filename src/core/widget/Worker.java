package core.widget;

import java.util.List;

import org.apache.http.NameValuePair;

import core.Result;

public class Worker {

	protected boolean verifiedInEDT() {
		return true;
	}
	
	/**
	 * 非EDT线程运行。
	 * 
	 * @return
	 */
	public Result doInBackground(List<NameValuePair> params) {
		
		return Result.sucess("");
	}
	
	/**
	 * EDT线程运行。
	 * 
	 * @param result
	 */
	protected void doneInEDT(Result result) {
		
	}
	
	protected void doneFinally() {
		
	}
}
