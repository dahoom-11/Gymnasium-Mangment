public class Gym {
    private String name;
    private int Balance;

    private Member peList[];
    private int MemberCount;
    
    private Staff stList[];
    private int StaffCount;

    private Machine maList[];
    private int MachineCount;

    public Gym(String name, int MemberLength, int StaffLength,  int MachineLength){ // constructor
        setName(name);
        setBalance(Balance);
        
        peList = new Member[MemberLength];
        stList = new Staff[StaffLength];
        maList = new Machine[MachineLength];
    }


    public void AddMachines(String name, int index){ // using recursion
        // create the Machine object in this function to make a compostion relation
        if(index > maList.length){
            System.out.println("No More Space");
            return; // Stop recursion
        }
        else if(maList[index] == null){
            maList[index] = new Machine(name); // Compostion Relation
            return; // stop recursion
        }
        else{ // The recursive Step
            index++;
            AddMachines(name, index);
        }
    }

    public void AddMember(String name, String id, int ml){
        //Add MemberShip price to Gym Balance
        //every three members need atleast 1 machines and 1 coach
        //Create a Member object (compostion Relation)
        
        
    }

    public void AddStaff(String name, String id, int ml){ //using recursion

        
    }

    

    // Setters and Getters

    public String getName(){
        return name;
    }

    public int getBalance() {
        return Balance;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

}
