public class Machine implements Displayable {
    private String name;
    private static int numOfUses;

    public Machine(String name){
        this.name = name;
    }

    public static void Use(){
        numOfUses++;
        System.out.println("Sucess");
    }

    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Number of Uses: " + numOfUses);
    }

    public int getnumOfUses(){
        return numOfUses;
    }
}
