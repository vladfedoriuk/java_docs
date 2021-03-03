import java.util.Arrays;

public class Synchronization {
    public static void main(String[] args) throws InterruptedException {
        String[] arr = {"1","2","3","4"};
        HashMapProcessor hmp = new HashMapProcessor(arr);
        Thread t1 = new Thread(hmp, "t1");
        Thread t2 = new Thread(hmp, "t2");
        Thread t3 = new Thread(hmp, "t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Arrays.asList(hmp.getMap()));

    }
}

class HashMapProcessor implements Runnable{
    private String[] strArr = null;
    public HashMapProcessor(String[] m){
        this.strArr = m;
    }
    public String[] getMap(){
        return strArr;
    }
    @Override
    public void run(){
        processArr(Thread.currentThread().getName());
    }

    private void processArr(String name){
        for (int i = 0; i < strArr.length; i++) {
            processSomething(i);
            addThreadName(i, name);
        }
    }
    Object lock = new Object();
    private void addThreadName(int i, String name){
        synchronized(lock) {
            strArr[i] = strArr[i] + ":" + name;
        }
    }
    private void processSomething(int index){
        try{
            Thread.sleep(index*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
