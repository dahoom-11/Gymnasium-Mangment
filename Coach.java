
public class Coach extends Staff {
    private int numLessons;

    public Coach(String name, String id, int income ){
        super(name, id);
        calculate_income();
    }

    public void doCoachingLesson() {
    	numLessons++;
    	calculate_income();
    	System.out.println(name + " has Coached Someone");
    }
    
    
    public int calculate_income(){
    	
        //every lesson is 100 bucks
    	income=numLessons * 100;
        return income; 
    }


    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("Id: " + id);
        System.out.println("Income: " + income);
        System.out.println("Number of Lessons: " + numLessons);
    }


    public void setnumLesson(int numLesson){
        this.numLessons = numLesson;
    }

    public int getnumLesson(){
        return numLessons;
    }
}
