
public class Coach extends Staff {
    private boolean coachingLesson;

    
 // Constructor
	public Coach(String name, int id, int income) {
		super(name, id, income);
		
	}

    
	@Override
    public void displayInfo(){  // Display Coach info
        System.out.println("Coach name: "+ name);
        System.out.println("Coach ID: "+ id);
        System.out.println("Coach income: "+ getIncome()+ " RS");
    }
    
	
	 @Override //Coach behavior
	 public void performRole() {
		giveLesson();
	 }
	
	//Simulate giving a lesson
	public void giveLesson() {
		coachingLesson=true;
		 System.out.println(name+ " is giving a coavhing lesson");
	}
	
	
	@Override //Calculate coach bonus
	public double calculateBonus() {
		return getIncome()*0.1;
	}
	
	
}