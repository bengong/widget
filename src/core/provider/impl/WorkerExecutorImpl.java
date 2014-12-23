package core.provider.impl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.*;

import javax.swing.SwingWorker;

import sun.awt.AppContext;
import core.provider.WorkerExecutor;
import core.provider.impl.internal.DefaultThreadFactoryImpl;

public class WorkerExecutorImpl implements WorkerExecutor {

	   /**
     * number of worker threads.
     */
    private static final int MAX_WORKER_THREADS = 10;
    
	private static synchronized ExecutorService service() {
		final AppContext appContext = AppContext.getAppContext();
		ExecutorService executorService = (ExecutorService) appContext.get(SwingWorker.class);
		if (executorService == null) {
			// this creates daemon threads.
			ThreadFactory threadFactory = new DefaultThreadFactoryImpl();

			executorService = new ThreadPoolExecutor(MAX_WORKER_THREADS, MAX_WORKER_THREADS, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(), threadFactory);

			appContext.put(SwingWorker.class, executorService);

			// Don't use ShutdownHook here as it's not enough. We should track
			// AppContext disposal instead of JVM shutdown, see 6799345 for details
			final ExecutorService es = executorService;

			appContext.addPropertyChangeListener(AppContext.DISPOSED_PROPERTY_NAME, new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent pce) {
					boolean disposed = (Boolean) pce.getNewValue();
					if (disposed) {
						final WeakReference<ExecutorService> executorServiceRef = new WeakReference<ExecutorService>(es);
						final ExecutorService executorService = executorServiceRef.get();
						if (executorService != null) {
							AccessController.doPrivileged(new PrivilegedAction<Void>() {
								public Void run() {
									executorService.shutdown();
									return null;
								}
							});
						}
					}
				}
			});
		}
		return executorService;
	}
	
	public void execute(SwingWorker command) {
		service().execute(command);
	}
}
