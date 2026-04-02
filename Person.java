
public abstract class Person implements Displayable {
	
    protected String name;
    protected String id;
    
     // Constructor
    public Person(String name, String id){
        this.name = name;
        if(id.length() == 4){
            this.id = id;
        }
        else{
            this.id = "1111";
        }
    }  



    
    
}
