package utility;

public class PoolThread extends Thread{

	private static IDAssigner threadID = new IDAssigner(1);
	
	private ThreadPool pool;
	
	public PoolThread(ThreadPool pool){
		super(pool, "PoolThread-" + threadID.next());
		this.pool = pool;
	}

	@Override
	public void run() {
		while(!isInterrupted()){
			Runnable task = null;
			try {
				task = pool.getTask();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(task == null) return;
			
			try {
				task.run();
			} catch(Throwable t){
				pool.uncaughtException(this, t);
			}
		}
	}
	
	
}
