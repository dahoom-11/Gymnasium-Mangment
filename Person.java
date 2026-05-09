
public abstract class Person implements Displayable {
	
    protected String name;
    protected String id;
    
     // Constructor
    public Person(String name, String id) throws InvalidIdException{
        this.name = name;
        if(id.length() != 4){
            throw new InvalidIdException("ID must be 4 digits");
        }
        this.id = id;
    }  



    
    
}