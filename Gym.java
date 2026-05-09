public class Gym implements Displayable{
	
    private String name;
    private int balance;

    
    private LinkedList memberList;
    private int memberCount;
    
    private LinkedList staffList;
    private int coachCount;
    private int staffcount;
    
    private LinkedList machineList;
    private int machineCount;

    public Gym(String name, int memberLength, int MachineLength, int staffLength ){ // constructor
        this.name = name;
      
       memberList = new LinkedList("Members");
       machineList = new LinkedList("Machines");
       staffList = new LinkedList("Staff");
    }


 //Add machine to the gym
    public void AddMachines(String name){
        Machine m = new Machine(name); // composition
        machineList.insertAtBack(m);
        machineCount++;
    }
    	
    
 //Add new member to the gym and updates the balance
    public void addMember(Member m){
        memberList.insertAtBack(m);
        memberCount++;
        balance+=m.calculate_memberShipPrice();
    }

    
 //Searche for member in the linked list by id and name
    public boolean searchMember(Member m){
        Node current = memberList.getHead();

        while(current != null){
            Member member = (Member) current.getData();
            if(member.id.equals(m.id) && member.name.equals(m.name)){
                return true;
            }
            current = current.getNext();
        }
        return false;}
   

    
 //Remove member from the linked list
    public boolean removeMember(Member m){
        Node current = memberList.getHead();
        Node prev = null;
        
        while(current != null){
            Member member = (Member) current.getData();
            if(member.id.equals(m.id) && member.name.equals(m.name)){
                if(prev == null){        // Remove first element
                    memberList.setHead(current.getNext());
                } else {
                    prev.setNext(current.getNext());
                }
                memberCount--;
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;}
   
                
 //Add staff to the gym
    public void addStaff(Staff s){
        staffList.insertAtBack(s);
        staffcount++;

        if(s instanceof Coach){
            coachCount++;
        }
    }

    public String displayInfo(){
        String Info = """ 
        Name:  + %s
        Balance:  + %d
        Amount of Members:  + %d
        Total Amount of MemberShip length (in months):  + %d
        Amount of Coachs:  + %d
        Total Number of Lessons Done: " + %d
        Amount of Machines:  + %d
        Total Number of Uses:  + %d
        """.formatted(
            name, 
            balance, 
            memberCount, 
            Calculate_TotalMemberShipLength(), 
            coachCount, 
            Calculate_TotalLessonsDone(), 
            machineCount, 
            Calculate_TotalNumberOfUses()
        );
        return Info;
    }
    
 //Calculates total membership length of all members
    public int Calculate_TotalMemberShipLength(){
        int total = 0;
        Node current = memberList.getHead();

        while(current != null){
            Member m = (Member) current.getData();
            total += m.getMembershipLength();
            current = current.getNext();
        }
        return total;
    }
    
 //Calculates total coaching lessons done by all coaches
    public int Calculate_TotalLessonsDone(){
        int total = 0;
        Node current = staffList.getHead();

        while(current != null){
            Staff s = (Staff) current.getData();
            if(s instanceof Coach){
                Coach c = (Coach) s;
                total += c.getnumLesson();
            }
            current = current.getNext();
        }
        return total;
    }

 //Calculates total number of machine uses
    public int Calculate_TotalNumberOfUses(){
        int total = 0;
        Node current = machineList.getHead();
        
        while(current != null){
            Machine m = (Machine) current.getData();
            total += m.getnumOfUses();
            current = current.getNext();
        }
        return total;
    }
    
    	

    //Setters and Getters

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
    
    public Staff getCoach(int index){
        Node current = staffList.getHead();
        int i = 0;

        while(current != null){
            if(i == index){
                return (Staff) current.getData();
            }
            current = current.getNext();
            i++;
        }
        return null;
    }

    public Member getMember(int index){
        Node current = memberList.getHead();
        int i = 0;

        while(current != null){
            if(i == index){
                return (Member) current.getData();
            }
            current = current.getNext();
            i++;
        }
        return null;
    }

    public LinkedList getMemberList(){
        return memberList;
    }
    public LinkedList getStaffList(){
        return staffList;
    }
    public LinkedList getmachineList(){
        return machineList;
    }

}