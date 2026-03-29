public class Member extends Person {
    private int memberShipLength; //in months
    private int memberPrice;
    private boolean HasWorkedOut;
    
    public Member(String N, int I,  int mt){
        super(N,I);
        setMembershipLength(mt);
        setMemberPrice(mt);
    }

    public void DisplayInfo(){  // Display member info

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
