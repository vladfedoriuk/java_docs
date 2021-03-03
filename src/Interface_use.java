import java.io.*;

interface Function<T>{
    T fun(T x);
}

interface DFh{
    default Double d_h(Double x, Double h, Function<Double> f){
        return (f.fun(x + h) -f.fun(x))/h;
    }
    static Double absolute_error(Double x, Double h, DFh fobj, Function<Double> f, Function<Double> df){
        return Math.abs(fobj.d_h(x, h, f) - df.fun(x));
    }
}


public class Interface_use {

    public static void main(String[] args) {

        //double x = 1.0;
        Function<Double> f = Math::cos;
        Function<Double> df = (x) -> -Math.sin(x);

        DFh a = new DFh() {

        };

      DFh b = new DFh() {
            @Override
            public Double d_h(Double x, Double h, Function<Double> f) {
                return (f.fun(x + h) - f.fun(x - h)) / (2 * h);
            }
        };
       // DFh b = (Double x, Double h, Function<Double> f) ->(f.fun(x + h) - f.fun(x - h)) / (2 * h); - there are o function to override
        DFh c = new DFh() {
            @Override
            public Double d_h(Double x, Double h, Function<Double> f) {
                return (-f.fun(x + 2 * h) + 8 * f.fun(x + h) - 8 * f.fun(x - h) + f.fun(x - 2 * h)) / (12 * h);
            }
        };

        FileWriter writer = null;
        FileWriter writer1 = null;
        FileWriter writer2 = null;
        try {
           writer1 = new FileWriter("in1.txt");
           writer2 = new FileWriter("in2.txt");
           writer = new FileWriter("in.txt");
        }catch(IOException e){
            System.out.println(e.toString());
        }


        BufferedWriter bufWriter = new BufferedWriter(writer);
        BufferedWriter bufWriter1 = new BufferedWriter(writer1);
        BufferedWriter bufWriter2 = new BufferedWriter(writer2);
        double k = 1e-16;
        double h;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 100; j++) {
                h = Math.random() * 50 * k;
                //System.out.println("h = "+h);
                //System.out.println(DFh.absolute_error(1.0, h, a, f, df));
                try {
                    bufWriter.write(Double.toString(h)+" "+Double.toString(DFh.absolute_error(1.0, h, a, f, df))+'\n');
                    bufWriter1.write(Double.toString(h)+" "+Double.toString(DFh.absolute_error(1.0, h, b, f, df)) +'\n');
                    bufWriter2.write(Double.toString(h)+" "+Double.toString(DFh.absolute_error(1.0, h, c, f, df)) +'\n');
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
          k = k*10;
        }
        try {
            bufWriter.close();
            bufWriter1.close();
            bufWriter2.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("OK");
    }
};
