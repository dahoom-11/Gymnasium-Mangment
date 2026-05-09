public class Coach extends Staff {
    private int numLessons;

    public Coach(String name, String id, int income ) throws InvalidIdException{
        super(name, id);
        calculate_income();
    }
    
 //Increases lesson count and updates income
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

//Return coach information
    public String displayInfo(){
        String Info =
        "Name: " + name+
        "Id: " + id+
        "Income: " + income+
        "Number of Lessons: " + numLessons
        ;
        return Info;
    }


    public void setnumLesson(int numLesson){
        this.numLessons = numLesson;
    }

    public int getnumLesson(){
        return numLessons;
    }
}