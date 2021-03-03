public class BinaryTree<E extends Comparable<? super E>> { //? super E - nadklasa E


    public void add(E x){

    }

    public static <T extends Comparable<T>> T max(T a, T b){ //T - zawsze klasa
        if(a.compareTo(b) <=0){
            return a;
        } else
            return b;
    }
}
