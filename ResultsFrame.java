class ResultsFrame extends JFrame {  
    public ResultsFrame(GymLinkedList list) {  
        setTitle("Gym Records Display");  
        setSize(400, 400);  
        JTextArea area = new JTextArea();  
        area.setEditable(false);  
          
        StringBuilder sb = new StringBuilder("Registered Gyms Details:\n\n");  
        Node temp = list.getHead();  
        while (temp != null) {  
            sb.append("Gym Name: ").append(temp.data.getName())  
              .append("\nMembers: ").append(temp.data.getmemberCount())  
              .append("\nBalance: ").append(temp.data.getBalance()).append(" RS\n")  
              .append("----------------------------\n");  
            temp = temp.next;  
        }  
        area.setText(sb.toString());  
        add(new JScrollPane(area));  
    }  
}