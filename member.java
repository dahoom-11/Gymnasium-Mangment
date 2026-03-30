public class Member extends Person implements Displayable{
    private int memberShipLength; //in months
    private int memberPrice;
    private boolean hasWorkedOut;
    
    public Member(String name, String id,  int mt){
        super(N,I);
        setMembershipLength(mt);
        setMemberPrice(mt);
    }

    public void DisplayInfo(){  // Display member info
        System.out.println("Name: " + name);
        System.out.println("Id: " + id);
        System.out.println("MemberShip Length: " + memberShipLength);
        System.out.println("MemberShip Price: " + memberPrice);
        System.out.println("Has worked out today: " + hasWorkedOut);

    }



    public int calculate_memberShipPrice(){
        int price = memberShipLength * 100; //The price of one month = 100
        
        return price;
    }

    public void Workout(){

    }   

    
    //Setters and Getters
    public void setMembershipLength(int memberShipLength) {
        this.memberShipLength = memberShipLength;
    }
    public void setMemberPrice(int memberPrice) {
        memberPrice = calculate_memberShipPrice();
    }
    
}
