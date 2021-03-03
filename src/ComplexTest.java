import org.junit.Assert;

public class ComplexTest {
    public static void main(String[] args) {
        Complex a = new Complex("-1.2+3.4i");
        Complex b = Complex.valueOf("5.6-7.8i");
        Complex c = Complex.valueOf("-7.8i");
        Complex d = Complex.valueOf("-7.8");
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());
        System.out.println(d.toString());

        a.sub(b);
        b.add(c);
        c.mul(d);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());

        Complex x = Complex.mul(new Complex(1, 2), new Complex(3, 4));
        System.out.println(x.toString());
        Complex zero = new Complex(0,0);
        assert c.div(d).toString().equals("-7.9i"): "Not equal -7.8i";
        try {
            x.div(zero);
        } catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        try {
            zero.phase();
        } catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }

       // Assert.assertEquals("-7.8i", c.div(d).toString());

    }
}