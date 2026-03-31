public abstract class Staff extends Person{
    protected int income;

    public Staff(String name, String id){
        super(name, id);
    }  

    public abstract int calculate_income();

    //Setters and Getters
}
