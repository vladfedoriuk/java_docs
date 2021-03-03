//Thread Safety in Java is a very important topic.
// Java provides multi-threaded environment support using Java Threads,
// we know that multiple threads created from same Object share object variables
// and this can lead to data inconsistency when the threads are used to read and update the shared data.

//The reason for data inconsistency is because updating any field value is not an atomic process, it requires three steps;
// first to read the current value,
// second to do the necessary operations to get the updated value and third to assign the updated value to the field reference.

//Synchronization is the easiest and most widely used tool for thread safety in java.
//Use of Atomic Wrapper classes from java.util.concurrent.atomic package. For example AtomicInteger
//Use of locks from java.util.concurrent.locks package.
//Using thread safe collection classes, check this post for usage of ConcurrentHashMap for thread safety.
//Using volatile keyword with variables to make every thread read the data from memory, not read from thread cache.

//Instance methods are synchronized over the instance of the class owning the method.
// Which means only one thread per instance of the class can execute this method.

//Static methods are synchronized on the Class object associated with the class and since only one Class object exists per JVM per class,
// only one thread can execute inside a static synchronized method per class, irrespective of the number of instances it has.

//When we use a synchronized block, internally Java uses a monitor also known as monitor lock or intrinsic lock, to provide synchronization
//These monitors are bound to an object,
// thus all synchronized blocks of the same object can have only one thread executing them at the same time.
// (because we lock/unlock the monitor in this object)

//Notice, that we passed a parameter this to the synchronized block.
// This is the monitor object, the code inside the block get synchronized on the monitor object.
// Simply put, only one thread per monitor object can execute inside that block of code.

//In case the method is static, we would pass class name in place of the object reference.
// And the class would be a monitor for synchronization of the block:

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Methods{
    private int sum;
    private Object monitor = new Object();

    {
        sum = 0;
    }

    public static void performStaticSyncTask(){
        synchronized (Methods.class) {
          System.out.println("Static synchro block");
        }
    }

    public void calculate(){
        setSum(getSum()+1);
    }

    public synchronized void synchroCalculate(){
        setSum(getSum()+1);
    }
    public static synchronized void staticSynchroCalculate(){
        System.out.println("Static synchro");
    }
    //Sometimes we do not want to synchronize the entire method
    // but only some instructions within it. This can be achieved by applying synchronized to a block:
    public void performSynchronisedTask() {
        synchronized (monitor) {
            setSum(getSum()+1);
        }
    }
    void setSum(int i){
        sum = i;
    }
    int getSum(){
        return sum;
    }
}

public class ThreadSafety {
    public static void main(String[] args) throws InterruptedException {
        //ExecutorService service = Executors.newFixedThreadPool(3);
        Methods summation = new Methods();


        //IntStream.range(0,1000).forEach( count -> service.submit(summation::calculate)); //Consumer<T>::accept(T t)
        //IntStream.range(0,1000).forEach( count -> service.submit(summation::synchroCalculate));
        //IntStream.range(0,1000).forEach( count -> service.submit(Methods::staticSynchroCalculate));

        //IntStream.range(0,1000).forEach(count ->service.submit(summation::performSynchronisedTask));
        //assert summation.getSum() == 1000: "Not Equal sum";

        Thread t1 = new Thread(()->{
            //summation.performSynchronisedTask();
            IntStream.range(0, 5).forEach(count ->summation.performSynchronisedTask());

        });

        Thread t2 = new Thread(()->{
            //IntStream.range(0, 1000).forEach(count ->summation.synchroCalculate());
            for(int i = 0; i < 5; i++){
                summation.synchroCalculate();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(summation.getSum());
        assert summation.getSum() == 10: "Not equal";
        //service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        //service.shutdownNow();
    }
}

