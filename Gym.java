public class Gym {
    private String name;
    private int Balance;

    private Person peList[];
    private int PersonCount;
    
    private machine maList[];
    private int MachineCount;

    public Gym(String name, int PersonLength, int MachineLength){ // constructor
        setName(name);
        setBalance(Balance);
        peList = new Person[PersonLength];
        maList = new machine[MachineLength];
    }


    public void AddMachines(machine M){ // using recursion

    }

    public void AddPerson(Person P){ // using recursion
        //If member then add it to the array
        //Add MemberShip price to Gym Balance

        //If other add it normally

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
