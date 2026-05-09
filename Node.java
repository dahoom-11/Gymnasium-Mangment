
public class Node {
    Object data;
    Node next;

    public Node(Object obj){
        data = obj;
        next = null;
    }

    public Object getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}
