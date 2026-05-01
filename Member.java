
public class Member extends Person {
    private int memberShipLength; //in months
    private int memberPrice;
    private boolean HasWorkedOut;
    
    
    // Constructor
    public Member(String name, String id,  int mt){
        super(name,id);
        this.memberShipLength = mt;
        this.memberPrice = calculate_memberShipPrice();
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



    

    public void Workout(){
    	HasWorkedOut=true;
    	 System.out.println(name+" Has worked out");
    	
    }   

    
    //Setters and Getters
    public int getMembershipLength() {
        return this.memberShipLength;
    }
    
}