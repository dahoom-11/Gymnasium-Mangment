public abstract class Person implements Displayable{
    protected String name;
    protected String id;

    public Person(String name, String id){
        this.name = name;
        if(id.length() == 4){
            this.id = id; 
        }
        else{
            System.out.println("Id Must be four Digits");
            this.id = "1111";
        }
    }    
}
