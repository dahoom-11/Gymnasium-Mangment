
public abstract class Staff extends Person{
    protected int income;

    
// Constructor
    public Staff(String name, int id){
        super(name, id);
        
    }

    public abstract int calculate_income();


    //Setters and Getters

   