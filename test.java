import java.util.*;
public class test{
    
    public static void main(String args[]){
        Scanner i = new Scanner(System.in);
        int count = 0;

        while(true){
            System.out.println("----------------");

            System.out.println("1-Create Gym");
            System.out.println("2-Manage Gym");
            int choice = i.nextInt();
            //The Array that holds All the Gyms
            Gym GymList[] = new Gym[3];

            switch(choice){
                case 1:
                    // Gym Attrabutes
                    System.out.print("Enter: Gym name, Amount of Members, Amount of Staff, Amount of Machines");
                    String name = i.nextLine();
                    int NumMem = i.nextInt();
                    int NumSta = i.nextInt();   
                    int NumMac = i.nextInt();

                    // add to Gym list
                    Gym G1 = new Gym(name, NumMem, NumSta, NumMac);
                    GymList[count] = G1;
                    count++;
                    // User back to main menu not done yet
                case 2:
                    System.out.println("Choose your Gym: ");

                    //Loop threw Array of Gyms declared in Case 1

                     choice = i.nextInt();

                    for(int s = 1;s<count;s++){
                            System.out.println(s + "-" + GymList[s].getName());                            
                    }

                    switch (choice) {
                        case 1:
                            //GymList[0] is going to be used
                            System.out.println("1-Sign up Member");
                            System.out.println("2-Workout");
                            System.out.println("3-Add Machines");
                            System.out.println("4-Add Coach");
                            System.out.println("5-Get a Coaching Leasson");
                            System.out.println("6-Display Gym info");

                            choice = i.nextInt();

                            switch (choice) {
                                case 1:
                                    System.out.println("Enter the following: Name, id, MemberShipLength");
                                    name = i.nextLine();
                                    String id = i.nextLine();
                                    int MemType = i.nextInt();

                                    GymList[0].AddMember(name, id , MemType);
                            }

                            
                            case 2:
                    }
                            
                            
                            
                            
                    /*
                    Gym Managment Goals
                    System.out.println("1-Sign up Member");
                    System.out.println("2-Workout") //
                    System.out.println("5-Get a Coaching Lesson");
                    System.out.println("3-Add Machines");
                    System.out.println("4-Add Coach");
                    System.out.println("6-Display Gym info");
                    Just general it will be change
                    */
            }
        }
    }
}
