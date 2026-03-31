
public class Member extends Person {
    private int memberShipLength; //in months
    private int memberPrice;
    private boolean HasWorkedOut;
    
    
    // Constructor
    public Member(String N, int I,  int mt){
        super(N,I);
        setMembershipLength(mt);
        setMemberPrice();
    }

    @Override
    public void displayInfo(){  // Display member info
        System.out.println("Member name: "+ name);
        System.out.println("Member ID: "+ id);
        System.out.println("Membership lenght: "+ memberShipLength+ " months");
        System.out.println("Membership price: "+ memberPrice+ " RS");
    }



    public int calculate_memberShipPrice(){
        int price = memberShipLength * 100; //The price of one month = 100
        
        return price;
    }

    
    // member behavior (Workout)
    @Override
    public void performRole() {
    	Workout();
    }
    
    
    // Simulate workout
    public void Workout(){
    	HasWorkedOut=true;
    	 System.out.println(name+" Has worked out");
    	
    }   

    
    //Setters and Getters
    public void setMembershipLength(int memberShipLength) {
        this.memberShipLength = memberShipLength;
    }
    public void setMemberPrice() {
       this.memberPrice = calculate_memberShipPrice();
    }
    
}