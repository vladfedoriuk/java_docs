import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiFunctionUse {
    public static void main(String[] args) {

        //apply()
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        int sum = add.apply(2, 3);
        System.out.println("sum = "+sum);

        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        int mult = multiply.apply(2, 3);
        System.out.println("mult = "+mult);

        //andThen
        /*default <V>
            BiFunction<T, U, V>
                andThen(Function<? super R, ? extends V> after) */

        BiFunction<Integer, Integer, Integer> composite = (a, b) -> a + b*a;
        composite = composite.andThen((c)->c*5); //returns bifunction (maybe this)
        System.out.println("Composite = " + composite.apply(2, 3)); //(2+3*2)*5 = 40

        //NullPointerException demonstration
        try{
            composite = composite.andThen(null);
            System.out.println("Composite: "+composite.apply(1,1));
        }catch(NullPointerException e){
            System.out.println(e.toString());
        }
        //ArithmeticException demonstration
        try{
            composite = (a, b) -> a + b;
            composite = composite.andThen((a)->a/(a-2));
            int res = composite.apply(1, 1);
            System.out.println("res = "+res);
        }catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }

        //1)It isn’t possible to add a BiFunction to an existing BiFunction using addThen().
        //2)BiFunction interface is useful when it is needed to pass 2 parameters, unlike Function interface
        // which allows to pass only one. However, it is possible to cascade two or more Function objects to form a BiFunction but in that case it won’t be possible to use
        // both the parameters at the same time. This is the utility of BiFunction.
        //3)The Lambda expression is being used to initialize the apply() method in BiFunction interface.

        BiPredicate<String, Integer> f = (s, n)-> n == Integer.parseInt(s);
        System.out.println("BiPredicate.test() "+f.test("2", 2));

        BiPredicate<String, Integer> f1 = (n, m)->{
            if(Integer.parseInt(n) < m){
                System.out.println("n is less then m");
            } else{
                System.out.println("n is not less then m");
            }
            return Integer.parseInt(n)<m;
        };
        f = f.and(f1);
        System.out.println("BiPredicate.and()"+f.test("3", 3));

        f = f.negate();
        System.out.println("BiPredicate.negate(): "+f.test("4", 4));

        f = f.or(f1);
        System.out.println("BiPredicate.or(): "+f.test("4", 4));
    }


}
