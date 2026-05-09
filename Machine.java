public class Machine implements Displayable {
    private String name;
    private int numOfUses;

    public Machine(String name){
        this.name = name;
    }

    // 
    public void Use(){
        numOfUses++;
        System.out.println("Sucess");
    }
// display the machine info
    public String displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Number of Uses: " + numOfUses);
        return "";
    }

    public int getnumOfUses(){
        return numOfUses;
    }
}
