import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

class Worker implements Runnable {
    String command;

    public String toString(){
        return command;
    }
    Worker(String command){
        this.command = command;
    }

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+" started, command = "+command);
        processCommand(command);
        System.out.println(Thread.currentThread().getName()+" ended");
    }

    void processCommand(String command){
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class RejectedExecutionHandlerImp implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor){
        System.out.println(r.toString()+" rejected");
    }

}

class MyMonitor extends TimerTask{
    private ThreadPoolExecutor executor;

    MyMonitor(ThreadPoolExecutor executor){
        this.executor = executor;
    }
    @Override
    public void run(){
        System.out.println(
                String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),
                        this.executor.getCorePoolSize(),
                        this.executor.getActiveCount(),
                        this.executor.getCompletedTaskCount(),
                        this.executor.getTaskCount(),
                        this.executor.isShutdown(),
                        this.executor.isTerminated()));
    }
}
public class ExecutionServices {
    public static void main(String[] args) throws InterruptedException {
        RejectedExecutionHandlerImp reject = new RejectedExecutionHandlerImp();
        ThreadFactory factory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), factory, reject);
        TimerTask t = new MyMonitor(executorPool);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(t, 0, 10000);
        for (int i = 0; i < 10; i++) {
            executorPool.execute(new Worker("cmd" + i));
        }
        Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        Thread.sleep(30000);
        timer.cancel();
        System.out.println("TimerTask cancelled");
    }
}
