public class Coach extends Staff {
    private int numLessons;

    public Coach(String name, String id, int income, int numLesson){
        super(name, id, income);
        this.numLessons = numLesson;
    }

    public int calculate_income(){
        //every lesson is 100 bucks
        return numLessons * 100; 
    }


    public void setnumLesson(int numLesson){
        this.numLessons = numLesson;
    }

    public int getnumLesson(){
        return numLessons;
    }

}
