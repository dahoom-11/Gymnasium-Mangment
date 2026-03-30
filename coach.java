public class Coach extends Staff {
    private int numLessons;

    public Coach(String name, String id, int income, int numLesson){
        super(name, id, income);
        this.numLesson = numLesson;
    }

    public int calculate_income(){
        //every lesson is 100 bucks
        return numlessons * 100; 
    }


    public void setnumLesson(int numLesso){
        this.numLessons = numLesso;
    }

    public int getnumLesson(){
        return numLessons;
    }

}
