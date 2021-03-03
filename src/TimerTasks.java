//java.util.TimerTask is an abstract class that implements Runnable
// interface and we need to extend this class to create our own TimerTask
// that can be scheduled using java Timer class.

//Java Timer class is thread safe and multiple threads can share a single
// Timer object without need for external synchronization.

//Java Timer class is thread safe and multiple threads can share a single
// Timer object without need for external synchronization. Timer class uses java.util.TaskQueue
// to add tasks at given regular interval and at any time there can be only one thread running the TimerTask,
// for example if you are creating a Timer to run every 10 seconds but single thread execution takes 20 seconds,
// then Timer object will keep adding tasks to the queue and as soon as one thread is finished, it will notify
// the queue and another thread will start executing.
//
//Java Timer class uses Object wait and notify methods to schedule the tasks.
//
//The output confirms that if a task is already executing, Timer will wait for it to finish and once finished, it will start again the next task from the queue.
//
//Java Timer object can be created to run the associated tasks as a daemon thread.
// Timer cancel() method is used to terminate the timer and discard any scheduled tasks,
// however it doesn’t interfere with the currently executing task and let it finish

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class MyTimerTask extends TimerTask{
    @Override
    public void run(){
        System.out.println("Timer task started at: "+new Date());
        completeTask();
        System.out.println("Timer task finished at: "+new Date());
    }
    public void completeTask(){
        try{
            //assuming it takes 20 seconds to complete the task
            Thread.sleep(20000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class TimerTasks {
    public static void main(String[] args){
        TimerTask t = new MyTimerTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(t,0,10*1000);
        System.out.println("TimerTask started");
        //cancel after some time
        try{
            Thread.sleep(120000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(30000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
