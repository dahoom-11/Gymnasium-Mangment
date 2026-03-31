
public class Gym {
	
    private String name;
    private int Balance;

    private Person peList[];
    private int PersonCount;
    
    private Machine maList[];
    private int MachineCount;

    public Gym(String name, int PersonLength, int MachineLength){ // constructor
        setName(name);
       this.Balance=0;
        peList = new Person[PersonLength];
        maList = new Machine[MachineLength];
        PersonCount=0;
        MachineCount=0;
    }


    public void AddMachines(Machine M){ // using recursion
    	    addMachineRec(M, 0);
    	}

    
    private void addMachineRec(Machine m, int index){
        if(index == MachineCount){
            if(MachineCount < maList.length){
                maList[index] = m.copy();
                MachineCount++;
            } else {
                System.out.println("Full");
            }
            return;
        }

        addMachineRec(m, index + 1);
    }
  
    	// Remove machine ------------------------------------------------
    	public void removeMachine(int index){
    	    if(index >= 0 && index < maList.length){
    	        if(maList[index] != null){
    	            maList[index] = maList[MachineCount-1] ;
    	            maList[MachineCount-1]=null;
    	            MachineCount--;
    	            System.out.println("Machine removed");
    	        } else {
    	            System.out.println("No machine at this position");
    	        }
    	    } else {
    	        System.out.println("Invalid index");
    	    }
    	}
    	 
    	
    	
        //If member then add it to the array
        //Add MemberShip price to Gym Balance

        //If other add it normally
    	
    public void AddPerson(Person P){ 
    	
    	    addPersonRec(P, 0);
    	}

    private void addPersonRec(Person p, int index){
        if(index == PersonCount){
            if(PersonCount < peList.length){
                peList[index] = p;
                PersonCount++;

                if(p instanceof Member){
                    Member m = (Member) p;
                    Balance += m.calculate_memberShipPrice();
                }
            } else {
                System.out.println("Full");
            }
            return;
        }

        addPersonRec(p, index + 1);
    }
   

    	
    	// Remove person by id-------------------------------------------
    	public void removePerson(int id){
    	    removePersonRec(id, 0);
    	}
    	
    	private void removePersonRec(int id, int index){
    	    if(index >= peList.length){
    	        System.out.println("Person not found");
    	        return;
    	    }

    	    if(peList[index] != null && peList[index].id == id){
    	    	
    	    	 if(peList[index] instanceof Member){
    	        Member m = (Member) peList[index];
    	        Balance -= m.calculate_memberShipPrice();
    	    }
    	        peList[index] = peList[PersonCount-1];
    	        peList[PersonCount-1]=null;
    	        PersonCount--;
    	        
    	        System.out.println("Person removed");
    	        return;
    	    }
    	   
    	    removePersonRec(id, index+1);
    	}
    	
    	
    	
    	// Search person by id-------------------------------------------
    	public Person searchPerson(int id){
    	    return searchPersonRec(id, 0);
    	}
    	
    	private Person searchPersonRec(int id, int index){
    	    if(index >= peList.length){
    	        return null; // not found
    	    }

    	    if(peList[index] != null && peList[index].id == id){
    	        return peList[index];
    	    }

    	    return searchPersonRec(id, index+1);
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