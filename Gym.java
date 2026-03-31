
public class Gym {
	
    private String name;
    private int Balance;

    
    private Member memberList[];
    private int memberCount;
    
    private Staff staffList[];
    private int staffCount;
    
    private Machine maList[];
    private int MachineCount;

    public Gym(String name, int memberLength, int MachineLength, int staffLength ){ // constructor
        setName(name);
      
       memberList = new Member[memberLength];
       maList = new Machine[MachineLength];
       staffList = new Staff[staffLength];
    }


    
    public void AddMachines(String name, int index){ // using recursion
        // create the Machine object in this function to make a compostion relation
        if(index > maList.length){
            System.out.println("No More Space");
            return; // Stop recursion
        }
        else if(maList[index] == null){
            maList[index] = new Machine(name); //Compostion Relation
            return; // stop recursion
        }
        else{ //The recursive Step
            index++;
            AddMachines(name, index);
        }
    }

    	 
    	
    	
        //If member then add it to the array
        //Add MemberShip price to Gym Balance

        //If other add it normally
    	
    

    public void addMember(Member m){
            if(memberCount < memberList.length) {
               memberList[memberCount] = m;
               memberCount++;

               Balance += m.calculate_memberShipPrice();
                                   
                }
            else {
                System.out.println("Full");
            }
            return;
}
    
    
    
    
        // Search person by id-------------------------------------------
    	public Member searchMember(int id){
    	    return searchMemberRec(id, 0);
    	}
    	
    	private Member searchPersonRec(int id, int index){
    	    if(index >= PersonCount){
    	        return null; // not found
    	    }

    	    if(peList[index] != null && peList[index].id == id){
    	        return peList[index];
    	    }

    	   
    
    	
    
    
    	   
    	
    	public void addStaff(Staff s){
        if(staffCount < staffList.length) {
           staffList[staffCount] = s;
           staffCount++;
                               
            }
        else {
            System.out.println("Full");
        }
        return;
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