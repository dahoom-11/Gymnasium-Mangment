public class Machine implements Displayable {
    public String name;
    private static int numOfUses;

    public void Machine(String name, int numOfUses){
        this.name = name;
    }

    public void Use(){
        numOfUses++;
        System.out.println("Sucess");
    }

    public void DisplayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Number of Uses: " + numOfUses);
    }
}
