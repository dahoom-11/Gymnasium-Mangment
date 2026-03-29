public abstract class Staff extends Person{
    private int income;

    

    public Staff(String name, int id, int income){
        super(name, income);
        setincome(income);
    }



    //Setters and Getters

    public void setincome(int income){
        this.income = income;
    }

    public int getincome(){
        return income;
    }
}
