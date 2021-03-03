interface Interfejs {
    void metoda();
}

class Klasa implements Interfejs{
    Klasa(){
        System.out.println("Konstruktor");
    }
    static void statyczna(){
        System.out.println("Statyczna");
    }

    void niestatyczna(){
        System.out.println("nietatyczna");
    }

    public void metoda(){
        System.out.println("metoda");
    }
}


public class example {
    static void funkcja(Interfejs obj){
        obj.metoda();
    }

    public static void main(String[] args){
        Klasa obj = new Klasa();
        funkcja(Klasa::new);
        funkcja(Klasa::statyczna);
        funkcja(obj::niestatyczna);
        funkcja(obj);
        funkcja(obj::metoda);
        //funkcja(Klasa::metoda);

    }
}
