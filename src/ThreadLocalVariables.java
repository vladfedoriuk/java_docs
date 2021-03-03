import java.util.Random;
public class ThreadLocalVariables implements Runnable {

    private static final ThreadLocal<Integer> id = new ThreadLocal<>();
    @Override
    public void run(){
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" id= "+id.get());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id.set(new Random().nextInt());
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" id= "+id.get());
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadLocalVariables obj = new ThreadLocalVariables();
        for(int i=0 ; i<10; i++){
            Thread t = new Thread(obj, ""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

}
