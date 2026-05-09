
public class LinkedList {
    private Node head;
    private Node tail;
    private String name;

    public LinkedList(String name){
        this.name = name;
        head = tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void insertAtBack(Object obj){
        Node newNode = new Node(obj);

        if(isEmpty()){
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public Node getHead(){
        return head;
    }

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHead(Node head) {
		this.head = head;
	}
    
    
}
