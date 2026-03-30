public abstract class Staff extends Person{
    private int income;

    public Staff(String name, String id, int income){
        super(name, id);
        setincome(income);
    }  

    public abstract int calculate_income();

    //Setters and Getters

    public void setincome(int income){
        this.income = income;
    }

    public int getincome(){
        return income;
    }
}
