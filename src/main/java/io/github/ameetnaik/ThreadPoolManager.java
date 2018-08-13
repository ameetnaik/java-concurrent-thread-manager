package io.github.ameetnaik;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

	private static final int THREAD_POOL_SIZE = 5;

	private int poolSize;
	
	private Callable<?> callback;

	private List<Callable<?>> taskList;

	ThreadPoolManager(){
		poolSize = THREAD_POOL_SIZE;
	}

	ThreadPoolManager(int size){
		poolSize = size;
	}

	protected void runManager() {

		ExecutorService pool = Executors.newFixedThreadPool(getPoolSize());

		try {

			List<FutureTask<?>> runningList = new ArrayList<FutureTask<?>>();
			int iPoolSize = getPoolSize();

			if (getTaskList().size() < iPoolSize) {
				iPoolSize = getTaskList().size();
			}

			while (getTaskList().size() > 0) {

				while (runningList.size() < iPoolSize) {
					Callable<?> task = getTaskList().get(0);
					FutureTask<?> queueTask = new FutureTask(task);
					pool.execute(queueTask);
					runningList.add(queueTask);
					getTaskList().remove(0);
				}

				if (runningList.size() == iPoolSize) {
					for (int i = 0; i < runningList.size(); i++) {
						if (runningList.get(i).isDone()) {
							runningList.remove(i);
							//override to implement messaging when thread is done or 
							//provide a Callable method
							callbackThreadDone();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}

		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void callbackThreadDone() throws Exception {
		if (null != getCallback() && getCallback() instanceof Callable) {
			getCallback().call();
		}
	}

	public static void main(String[] args) {
		ThreadPoolManager threadPoolManager = new ThreadPoolManager();
		threadPoolManager.runManager();
	}
	
	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public List<Callable<?>> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Callable<?>> taskList) {
		this.taskList = taskList;
	}

	public Callable<?> getCallback() {
		return callback;
	}

	public void setCallback(Callable<?> callback) {
		this.callback = callback;
	}
	
}
