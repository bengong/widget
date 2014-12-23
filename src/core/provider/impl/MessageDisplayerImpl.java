package core.provider.impl;

import core.provider.MessageDisplayer;
import core.provider.impl.internal.MessageDisplayerPanel;

public class MessageDisplayerImpl implements MessageDisplayer {

	private MessageDisplayerPanel panel ;
	
	public MessageDisplayerImpl(MessageDisplayerPanel panel) {
		super();
		
		this.panel = panel;
	}

	@Override
	public void reset() {
		panel.reset();
	}

	@Override
	public void exception(Exception exception) {
		panel.exception(exception);
	}

	@Override
	public void throwable(Throwable throwable) {
		panel.throwable(throwable);
	}

	@Override
	public void error(String message) {
		panel.error(message);
	}

	@Override
	public void warn(String message) {
		panel.warn(message);
	}

	@Override
	public void success(String message) {
		panel.success(message);
	}

	@Override
	public void message(String message) {
		panel.message(message);
	}
}
