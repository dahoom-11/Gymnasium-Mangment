public abstract class Staff extends Person{
    protected int income;

    public Staff(String name, String id, int income){
        super(name, id);
        this.income = income;
    }  

    public abstract int calculate_income();

    //Setters and Getters
}
