import java.util.*;
import java.util.function.ToIntFunction;
import java.util.function.Consumer;

public class LambdaWithCollections {
    public static void main(String[] args){
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(34);
        a.add(12);
        a.add(45);
        a.add(66);

        System.out.println("Elements of a before sorting: "+a);
        //using lambda in place of comparator
        Collections.sort(a, (o1, o2)-> o2-o1);
        System.out.println("Elements of a after sorting: "+a);

        Collections.sort(a, Comparator.comparingInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value;
            }
        }));

        Collections.sort(a, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        System.out.println("Elements of a after sorting: "+a);

        a.forEach(new Consumer<Integer>(){
            @Override
            public void accept(Integer obj){
                System.out.println("obj is "+obj);
            }
        });

        a.forEach((b) ->{
            b = b-35;
            System.out.println("b is "+b);
        });


        //multiplying all the elements of a list by 2 and then displaying them
        Consumer<List<Integer>> f = list->{
            //list.forEach(c-> c = c*2);
            for(int i = 0; i < list.size(); i++){
                list.set(i, 2*list.get(i));
            }
        };

        Consumer<List<Integer>> disp = list->{
           // list.forEach(c->System.out.println(c));
            list.forEach(System.out::println);
        };

        List<Integer> l = new ArrayList<Integer>();
        l.add(23);
        l.add(11);
        l.add(34);
        System.out.println("l=");
        f = f.andThen(disp);
        f.accept(l);

    }
}
