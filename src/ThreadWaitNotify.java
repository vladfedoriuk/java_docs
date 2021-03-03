//A java bean class on which threads will work and call wait and notify methods.

class Monitor{
    private String message;

    public Monitor(String m){
        message = m;
    }

    public String getMessage(){
        return message;
    }
    public void setMessage(String m){
        message = m;
    }
}


//A class that will wait for other threads to invoke notify methods
// to complete it’s processing. Notice that Waiter thread is owning monitor
// on Monitor object using synchronized block.
class Waiter implements Runnable{

    private Monitor monitor;
    public Waiter(Monitor m){
        monitor = m;
    }

    @Override
    public void run(){
        String name = Thread.currentThread().getName();
        System.out.println(name+" started");
        synchronized (monitor){
            try{
                System.out.println(name+" waiting to be notified, time: "+System.currentTimeMillis());
                monitor.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name+" thread has got notified at time: "+System.currentTimeMillis());
            System.out.println(name+" processed " + monitor.getMessage());
            monitor.setMessage(name+"-changes'");
            System.out.println(name+" changed monitor to  " + monitor.getMessage());
        }
    }
}

class Notifier implements Runnable{
    private Monitor monitor;
    public Notifier(Monitor m){
        monitor = m;
    }
    @Override
    public void run(){
        String name = Thread.currentThread().getName();
        System.out.println(name+" started");
        try{
            Thread.sleep(2000);
            synchronized (monitor){
                monitor.setMessage(name+" 'Notifier work done'");
                //monitor.notify(); - will not complete the program
                monitor.notifyAll();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class ThreadWaitNotify {
    public static void main(String[] args){
        Monitor monitor = new Monitor("process it");
        Waiter waiter = new Waiter(monitor);
        new Thread(waiter,"waiter").start();

        Waiter waiter1 = new Waiter(monitor);
        new Thread(waiter1,"waiter1").start();

        Notifier notifier = new Notifier(monitor);
        new Thread(notifier,"notifier").start();
        System.out.println("All threads are started");

    }

}
