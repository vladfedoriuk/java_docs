
class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread running");
        try{
            System.out.println("Current Thread's id = "+Thread.currentThread().getId());
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }

};

class MyRunnable implements Runnable{
    @Override
    public void run(){
        System.out.println("MyThread running");
        try{
            System.out.println("Current Thread's id = "+Thread.currentThread().getId());
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }
};

/*. If we extend the Thread class, our class cannot extend any other class
because Java doesn’t support multiple inheritance.
But, if we implement the Runnable interface,
our class can still extend other base classes.
 */

/*2. We can achieve basic functionality of a thread by extending
Thread class because it provides some inbuilt methods like yield(),
interrupt() etc. that are not available in Runnable interface.
 */
public class Multithreading {

    /*Two ways of creating a thread:
      1.Extending the Thread class
      2.Implementing the Runnable Interface
     */

    public static void main(String[] args){
        MyThread t = new MyThread();
        t.start();

        MyRunnable r = new MyRunnable();
        r.run();

        Thread anon = new Thread(new Runnable(){

            @Override
            public void run(){
                System.out.println(Thread.currentThread().getId());
            }
        });
       anon.start();

       new Thread(()->{
           long start = System.currentTimeMillis();
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e){
               System.err.println("ooops"+'\n'+e.getMessage());
           }
           System.out.println("Sleeping time = "+(System.currentTimeMillis() - start));
       }).start();
    }
}
