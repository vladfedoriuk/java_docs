interface in{

    default void a(){
        System.out.println("default a()");
    }
    default void b(){
        System.out.println("default b()");
    }
    static void c(){
        System.out.println("static in.c()");
    }
}


public class InterfacePlay {
    public static void main(String[] args) {
        A obj1 = new A();
        B obj2 = new B();
        in.c();
        System.out.println("obj1.c()");
        obj1.c();
        System.out.println("obj2.c()");
        obj2.c();
    }
}


class A implements in{
    @Override
    public void a(){
        System.out.println("A a()");
    }
    public void c(){
        System.out.println("A c()");
        a();
        b();
    }
}

class B extends A implements in{
    @Override
    public void b(){
        System.out.println("B b()");
    }
}
