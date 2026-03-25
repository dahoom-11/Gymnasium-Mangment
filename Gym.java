public class Gym {
    private int address;
    private int name;

    private member meList[];
    private int NoMe;
    
    private staff coList[];
    private int NoCo;

    private machine maList[];
    private int NoMa;

    public Gym(int melen,int colen, int malen){ // constructor
        meList = new member[melen];
        coList = new staff[colen]; 
        maList = new machine[malen];

    }


    public void AddMachines(machine M){ // using recursion

    }

    public void AddCoaches(staff C){ // using recursion

    }

    public void AddMember(member M){

    }


}
