import java.util.*;
import java.util.function.Predicate;

public class FunctionalInterface {
    //@FunctionalInterface annotation is used to ensure that
    // the functional interface can’t have more than one abstract method
    @java.lang.FunctionalInterface
    interface Square<T>{
        T calculate(T x);
    }
    public static void main(String[] args){

        //anonymous classes
        new Thread(new Runnable(){
            @Override
            public void run(){
                System.out.println("Thread run()");
            }
        }).start();

        //lambda
        new Thread(()->{
            System.out.println("Thread1 run()");
        }).start();


        int a = 5;
        Square<Integer> s = (x)->x*x;
        System.out.println(s.calculate(a));

        List<String> names =
                Arrays.asList("Geek","GeeksQuiz","g1","QA","Geek2");
        Predicate<String> p = (k)->k.startsWith("G");
        for(String k: names){
            if(p.test(k)){
                System.out.println(k);
            }
        }

        names.forEach(k->{
            if (p.test(k))
                System.out.println(k);
        });
        /*Important Points/Observations:

    1)A functional interface has only one abstract method but it can have multiple default methods.
    2)@FunctionalInterface annotation is used to ensure an interface can’t have more than one abstract method. The use of this annotation is optional.
    3)The java.util.function package contains many builtin functional interfaces in Java 8
*/

    }
}
