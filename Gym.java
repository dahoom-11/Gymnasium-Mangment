
public class Gym implements Displayable{
	
    private String name;
    private int balance;

    
    private Member memberList[];
    private int memberCount;
    
    private Staff staffList[];
    private int coachCount;
    private int staffcount;
    
    private Machine machineList[];
    private int machineCount;

    public Gym(String name, int memberLength, int MachineLength, int staffLength ){ // constructor
        this.name = name;
      
       memberList = new Member[memberLength];
       machineList = new Machine[MachineLength];
       staffList = new Staff[staffLength];
    }


    
    public void AddMachines(String name, int index){ // using recursion
        // create the Machine object in this function to make a compostion relation
        if(index >= machineList.length){
            System.out.println("No More Space");
            return; // Stop recursion
        }
        else if(machineList[index] == null){
            machineList[index] = new Machine(name); //Compostion Relation
            machineCount++;
            return; // stop recursion
        }
        else{ //The recursive Step
            index++;
            AddMachines(name, index);
        }
    }

    	 
    	
    	
        //If member then add it to the array
        //Add MemberShip price to Gym balance

        //If other add it normally
    	
    

    public void addMember(Member m){
        if(memberCount < memberList.length) {
            memberList[memberCount] = m;
            memberCount++;

            balance += m.calculate_memberShipPrice();
                                
            }
        else {
            System.out.println("Full");
        }
        return;
    }

    public boolean searchMember(Member m){
        for(int i=0; memberCount>i;i++)
            if(memberList[i].id.equals(m.id) && memberList[i].name.equals(m.name))
                return true;
        return false;
    } 

    public boolean removeMember(Member m){
    	for(int i=0; i<memberCount; i++)
            if(memberList[i].id.equals(m.id) && memberList[i].name.equals(m.name)){
                memberList[i]=memberList[memberCount-1];
                memberList[memberCount-1]=null;
                memberCount--;
                return true;
            }
    	return false;
    				
    }
                
    	
    public void addStaff(Staff s){
        if(staffcount < staffList.length) {
            staffList[staffcount] = s;
            staffcount++;
            if( s instanceof Coach){
            coachCount++;
            }
        }
        else {
            System.out.println("Full");
        }
        return;
    }

    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Balance: " + balance);    
        System.out.println("Amount of Members: " + memberCount);
        System.out.println("Total Amount of MemberShip length (in months): " + Calculate_TotalMemberShipLength()); // print here
        System.out.println("Amount of Coachs: " + coachCount);
        System.out.println("Total Number of Lessons Done: " + Calculate_TotalLessonsDone());
        System.out.println("Amount of Machines: " + machineCount);
        System.out.println("Total Number of Uses: " + Calculate_TotalNumberOfUses());


    }

    public int Calculate_TotalMemberShipLength(){
        int total = 0;
        for(int i = 0;i<memberCount;i++){
            total += memberList[i].getMembershipLength();
        }
        return total;
    }

    public int Calculate_TotalLessonsDone(){
        int total = 0;
        for(int i = 0; i<coachCount;i++){
            if(staffList[i] instanceof Coach){
                Coach c = (Coach) staffList[i];
                total += c.getnumLesson();
            }
        }
        return total;
    }

    public int Calculate_TotalNumberOfUses(){
        int total = 0;
        for(int i = 0;i<machineCount;i++){
            total += machineList[i].getnumOfUses();
        }
        return total;
    }
    
    	

    // Setters and Getters

    public String getName(){
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getmemberCount(){
        return memberCount;
    }

    public int getcoachCount(){
        return coachCount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public Staff getCoach(int index){ // Needs Work
        return staffList[index];
    }

    public Member getMember(int index){
        return memberList[index];
    }

}
