//ExecutorService Example
//n the above program, we are creating a fixed-size thread pool of 5 worker threads.
// Then we are submitting 10 jobs to this pool, since the pool size is 5, it will start working
// on 5 jobs and other jobs will be in wait state,
// as soon as one of the job is finished, another job from the wait queue will be picked up by worker thread and get’s executed.
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    String command;

    WorkerThread(String command){
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

public class ThreadPool {
    public static void main(String[] args){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 10; i++){
            Runnable worker = new WorkerThread(""+i);
            executor.execute(worker);
        }
        executor.shutdown();
        while(!executor.isTerminated());
        System.out.println("Finished all threads");
    }
}
