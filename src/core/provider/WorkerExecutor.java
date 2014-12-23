package core.provider;

import javax.swing.SwingWorker;

import core.widget.Provider;

public interface WorkerExecutor extends Provider {
	public void execute(SwingWorker worker);
}
