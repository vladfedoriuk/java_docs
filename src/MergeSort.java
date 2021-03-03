import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort<T extends Comparable<? super T>> {
    MergeSort(T[] arr){
        monitor = new MergeMonitor<>(arr);
    }

    private ExecutorService executor = Executors.newFixedThreadPool(100);
    private MergeMonitor<T> monitor;
    public void mergeSort(Integer left, Integer right){
        if(right > left){
            Integer mid = (left+right)/2;

        }
    }
}

class MyCallable<T extends Comparable<? super T>> implements Callable<T>{

    @Override
    public T call() throws Exception {
        return null;
    }
}

class MergeMonitor<T extends Comparable<? super T>>{
    private T[] arr;
    private Integer left;
    private Integer right;
    MergeMonitor(T[] arr){
        this.arr = arr;
    }
    void set_left(Integer left){
        this.left = left;
    }
    void set_right(Integer right){
        this.right = right;
    }
    Integer get_left(){
        return  left;
    }
    Integer get_right(){
        return  right;
    }

    T[] get_arr(){
        return arr;
    }

}
