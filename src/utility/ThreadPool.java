package utility;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
	
	private static IDAssigner poolID = new IDAssigner(1);
	
	private boolean alive;
	private LinkedList<Runnable> taskQueue;
	private int id;

	public ThreadPool(int numThreads) {
		super("ThreadPool-" + poolID.next());
		
		this.id = poolID.getCurrentID();
		setDaemon(true);
		taskQueue = new LinkedList<Runnable>();
		alive = true;
		
		for(int i = 0; i < numThreads; i++){
			new PoolThread(this).start();
		}
	}
	
	public synchronized void runTask(Runnable task){
		if(!alive) throw new IllegalStateException("TreadPool" + id + "is dead");
		if(task != null){
			taskQueue.add(task);
			notify();
		}
	}
	
	public synchronized void close(){
		if(!alive) return;
		alive = false;
		taskQueue.clear();
		interrupt();
	}
	
	public void join(){
		synchronized(this){
			alive = false;
			notifyAll();
		}
		Thread[] threads = new Thread[activeCount()];
		int count = enumerate(threads);
		
		for(int i = 0; i < count; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
	}
	
	protected synchronized Runnable getTask() throws InterruptedException{
		while(taskQueue.size() == 0){
			if(!alive) return null;
			wait();
		}
		return taskQueue.remove(0);
	}

}
