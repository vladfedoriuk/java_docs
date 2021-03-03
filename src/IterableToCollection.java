import java.util.*;

public class IterableToCollection {

    public static<T> Collection<T> getCollectionFromIterable(Iterable<T> it){
        Collection<T> col = new ArrayList<T>();

        it.forEach(col::add);

        for(T t: it){
            col.add(t);
        }

        Iterator<T> i = it.iterator();
        while(i.hasNext()){
            col.add(i.next());
        }


        return col;
    }

    public static void main(String[] args){
         Iterable it = Arrays.asList(1, 2, 3, 4, 5);
         Collection col = getCollectionFromIterable(it);
         System.out.println("Collection: "+col);


    }
}
