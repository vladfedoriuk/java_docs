public class Dogs {
    String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Dogs(String name){
        this.name = name;
    }
    public static void foo(Dogs d){
        d.getName().equals("Max");
        d.setName("Reks");
        d = new Dogs("Fafik");
        d.setName("Rudy");
        d.getName().equals("Fafik");

    }
    public static void main(String[] args){
        Dogs aDog = new Dogs("Max");
        foo(aDog);
        System.out.println(aDog.getName());
    }
}
