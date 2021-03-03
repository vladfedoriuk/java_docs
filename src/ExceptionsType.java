import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

class MyExcept extends Throwable{
    MyExcept(String s){
        super(s);
    }
}

public class ExceptionsType {
    public static void main(String[] args){
        //ArithmeticException
        try{
            int a = 30;
            int b =0;
            int c = a/b;
            System.out.println("c = ");
        } catch (ArithmeticException e){
            e.printStackTrace();
        }
       //NullPointerException
        try{
            String a = null;
            System.out.println(a.charAt(0));
        } catch(NullPointerException e){
            System.out.println(e.toString());
        }
        //StringIndexOutOfBound Exception
        try{
            String s = "abcd"; //length is 4
            char c = s.charAt(5);
            System.out.println(c);
        } catch(StringIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        //FileNotFound Exception
        try{
            File file = new File("f.txt");
            FileReader fr = new FileReader(file);
        }catch(FileNotFoundException e){
            System.out.println(e.toString());
        }
        // NumberFormat Exception
        try{
            int num = Integer.parseInt ("akki") ;
        } catch(NumberFormatException e){
            System.out.println(e.toString());
        }
        //ArrayIndexOutOfBounds Exception
        try{
            int[] a = new int[5];
            a[6] = 9;
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.toString());
        }
        //MyExcept
        try{
            System.out.println("Throwing MyExcept");
            throw new MyExcept("something");
        } catch(MyExcept e){
            System.out.println(e.toString());
        }


    }
}
