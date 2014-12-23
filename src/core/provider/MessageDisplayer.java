package core.provider;

import core.widget.Provider;

public interface MessageDisplayer extends Provider {
	
	public void reset();
	
	public void exception(Exception exception);
	
	public void throwable(Throwable throwable);
	
	public void error(String message);
	
	public void warn(String message);
	
	public void success(String message);
	
	public void message(String message);
}
