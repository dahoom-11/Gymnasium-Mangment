
public abstract class Person {
	
    protected String name;
    protected int id;
    
     // Constructor
    public Person(String name, int id){
        this.name = name;
        this.id = id;
    }

    // Display person info
    public abstract void displayInfo();
    
    // Define person role (behavior)
    public abstract void performRole();
    
   
}
