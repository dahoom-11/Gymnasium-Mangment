
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class test{
    
    public static void main(String args[]){
        Scanner i = new Scanner(System.in);
        int count = 0;  
        boolean userMenu1 = true;
        Gym GymList[] = new Gym[3];
        System.out.print("\nWelcome To The Gymnasium-Managment");
        while(userMenu1){ // Main Menu Loop
            System.out.println("\n-------------------\n");

            System.out.println("1-Create Gym");
            System.out.println("2-Manage Gym");
            System.out.println("3-Delete Gym");
            System.out.println("4-Exit Program");
            System.out.println();
            System.out.print("Enter Option Here: ");
            int choice = i.nextInt();
            System.out.println();
            //The Array that holds All the Gyms
        
            switch(choice){ // Main Menu Switch
                case 1: // Create Gym Option
                    if(count == 3){
                        System.out.println("no more space");
                        break;
                    }
                    System.out.print("Enter: Gym name: ");
                    String name = i.next();
                    System.out.print("Capacity of Members: ");
                    int NumMem = i.nextInt();
                    System.out.print("Capacity of Staff: ");
                    int NumSta = i.nextInt();   
                    System.out.print("Capacity of Machines: ");
                    int NumMac = i.nextInt();
                    // Gym Attributes

                    // add to Gym list
                    GymList[count] = new Gym(name, NumMem, NumSta, NumMac);
                    
                    //making the file and writing on it
                    File Gymfile = new File(name + "_Gym.txt");
                    try(FileWriter F1 = new FileWriter(Gymfile)){
                        F1.write(GymList[count].displayInfo());
                    } 
                    catch(IOException e){   
                        System.out.println("Couldn't write on the file ");
                    }

                    count++;
                    break;
                    // User back to main menu not done yet
                case 2: // Manage Gym Menu
                    boolean chooseMenu = true;
                    while(chooseMenu){      //Choose Gym Menu
                        boolean ManageMenu = false;
                        System.out.println("\n-----------Gymnasium-Managment------------\n");
                        System.out.println("Choose your Gym: ");

                        //Loop threw Array of Gyms declared in Case 1
                        for(int s = 0;s<count;s++){
                            System.out.println(s+1 + "-" + GymList[s].getName());                            
                        }
                        System.out.println(count+1 + "-Go back");

                        System.out.println();
                        System.out.print("Enter Option Here: ");
                        choice = i.nextInt();

                        if(choice == count+1){
                            break;
                        }
                        else if(choice <= count && choice > 0){
                            ManageMenu = true;
                        }
                        int gymchoice = choice-1; // The Gym's Index That Will be Used
                        
                        
                        while(ManageMenu){ // Manage Menu Start
                            System.out.println("\n-----------Gymnasium-Managment------------\n");
                            System.out.println("1-Sign up Member");
                            System.out.println("2-Remove Member");
                            System.out.println("3-Workout");
                            System.out.println("4-Add Machines");
                            System.out.println("5-Add Coach");
                            System.out.println("6-Get a Coaching Leasson");
                            System.out.println("7-Display Gym info");
                            System.out.println("8-Go Back");

                            System.out.println();
                            System.out.print("Enter Option Here: ");  
                            choice = i.nextInt();
                            System.out.println();  

                            switch (choice) { // Manage Menu Switch
                                case 1: // Sign Up Member
                                    System.out.println("\n-----------Gymnasium-Managment------------\n");
                                    System.out.print("Enter Name: ");
                                    name = i.next();
                                    System.out.print("Enter ID: ");
                                    String id = i.next();
                                    System.out.print("Enter MemberShip Length (in Months): ");
                                    int MemType = i.nextInt();
                                    
                                    Member M1 = new Member(name, id, MemType);
                                    GymList[gymchoice].addMember(M1); 
                                    break;

                                case 2: // Remove Member
                                    if(GymList[gymchoice].getmemberCount() == 0){
                                        System.out.println("No Member");
                                        break;
                                    }
                                    System.out.println("\n-----------Gymnasium-Managment------------\n");
                                    for(int i3 = 0;i3<GymList[gymchoice].getmemberCount();i3++){
                                        System.out.println(i3+1 + "-" + GymList[gymchoice].getMember(i3).name);
                                    }
                                    System.out.println();
                                    System.out.print("Enter Option Here: ");
                                    choice = i.nextInt();
                                    System.out.println();
                                    Member rm = GymList[gymchoice].getMember(choice - 1);
                                    GymList[gymchoice].removeMember(rm);
                                    break;

                                case 3:// Workout
                                    if(GymList[gymchoice].getmemberCount() != 0){
                                        System.out.println("\n-----------Gymnasium-Managment------------\n");
                                        for(int i2 = 0;i2<GymList[gymchoice].getmemberCount();i2++){
                                            System.out.println(i2+1 + "-" + GymList[gymchoice].getMember(i2).name);
                                        }
    
                                        System.out.println();
                                        System.out.print("Enter Option Here: ");
                                        choice = i.nextInt();
                                        System.out.println();
                                        GymList[gymchoice].getMember(choice-1).Workout();
                                        break;
                                    }
                                    else{
                                        System.out.println("No Member to workout");
                                        break;
                                    }
                                case 4: //Add Machine
                                    System.out.println("\n-----------Gymnasium-Managment------------\n");
                                    System.out.print("Enter Machine Name: ");
                                    String Mname = i.next();
                                    GymList[gymchoice].AddMachines(Mname, 0);
                                    break;

                                case 5: // Add Coach
                                    System.out.println("\n-----------Gymnasium-Managment------------\n");
                                    System.out.print("Enter Name: ");
                                    String Cname = i.next();
                                    System.out.print("Enter Id: ");
                                    String Cid = i.next();
                                    Staff C1 = new Coach(Cname, Cid,0);
                                    GymList[gymchoice].addStaff(C1);
                                    break;

                                case 6:// Get coaching lesson
                                    if(GymList[gymchoice].getcoachCount() == 0){
                                        System.out.println("\nNo Coaches Added");
                                        break;
                                    }
                                    else{
                                        System.out.println("\n-----------Gymnasium-Managment------------\n");
                                        for(int i3 = 0;i3 < GymList[gymchoice].getcoachCount();i3++){
                                            if(GymList[gymchoice].getCoach(i3) instanceof Coach){
                                                System.out.println(i3+1 + "-" + GymList[gymchoice].getCoach(i3).name);
                                            }
                                        }
                                        System.out.println();
                                        System.out.print("Enter Option Here: ");
                                        choice = i.nextInt();

                                        Coach C2 = (Coach)GymList[gymchoice].getCoach(choice-1);
                                        C2.doCoachingLesson();
                                        break;
                                    }
                                
                                case 7://Display Info
                                    File s = new File(GymList[gymchoice].getName()+"_Gym.txt");
                                    try(Scanner Sca = new Scanner(s)){
                                        while(Sca.hasNextLine()){
                                            System.out.println(Sca.nextLine());
                                        }
                                    }
                                    catch(FileNotFoundException e){
                                        e.setStackTrace(null);
                                    }

                                    break;
                                
                                case 8:
                                    ManageMenu = false;
                                    break;
                                default:
                                    System.out.println();
                                    System.out.println("Choose An Available Option");
                                    break;

                                }
                                    //Write The Info of the gym
                                    try(FileWriter F1 = new FileWriter(GymList[gymchoice].getName()+"Gym.txt")){
                                        F1.write(GymList[gymchoice].displayInfo());
                                    } 
                                    catch(IOException e){   
                                        System.out.println("Couldn't write on the file ");
                                    }
                                
                            }
                            
                        }     
                        break;
                case 3: // Delete Gym                  
                    for(int i4 = 0;i4<count;i4++){
                        System.out.println(i4+1 + "-" +  GymList[i4].getName());
                    }
                    System.out.println(count+1 + "-Go Back");

                    System.out.println();
                    System.out.print("Enter Option Here: ");
                    choice = i.nextInt();
                    int delchoice = choice - 1;

                    if(choice == count+1 || delchoice > count || delchoice < 0){
                        break;
                    }
                    else{
                        File deleted = new File(GymList[delchoice].getName()+"_Gym.txt");
                        GymList[delchoice] = GymList[count - 1];
                        GymList[count - 1] = null;
                        if(deleted.delete()){
                            System.out.println("\nGym Deleted");
                        }
                        else{
                            System.out.println("Gym not deleted");
                        }
                        count--;
                        break;
                    }
                
                case 4:
                    userMenu1 = false;
                    break;

                default:
                    System.out.println();
                    System.out.println("Choose An Available Option");
                    break;
            }
        }
    }
}