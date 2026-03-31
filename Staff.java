
public abstract class Staff extends Person{
    private int income;

    
// Constructor
    public Staff(String name, int id, int income){
        super(name, id);
        setIncome(income);
    }

    
public abstract double calculateBonus(); //Calculates bonus based on staff member

// get total income (salary + bonus)
public double getTotalIncome() {
	return getIncome() +calculateBonus();
}

    //Setters and Getters

    public void setIncome(int income){
        this.income = income;
    }

    public int getIncome(){
        return income;
    }
}