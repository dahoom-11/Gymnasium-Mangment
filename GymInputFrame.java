public class GymInputFrame extends JFrame {  
    private JTextField nameField, memCapField;  
    private JButton addBtn, viewBtn, saveBtn;  
    private GymLinkedList gymList = new GymLinkedList();  
  
    public GymInputFrame() {  
        setTitle("Gym Management System - Input");  
        setSize(400, 300);  
        setLayout(new GridLayout(5, 2, 10, 10));  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        add(new JLabel("Gym Name:"));  
        nameField = new JTextField();  
        add(nameField);  
  
        add(new JLabel("Member Capacity:"));  
        memCapField = new JTextField();  
        add(memCapField);  
  
        addBtn = new JButton("Add Gym");  
        saveBtn = new JButton("Save to File");  
        viewBtn = new JButton("View Results");  
  
        add(addBtn);  
        add(saveBtn);  
        add(viewBtn);  
  
        addBtn.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                try {  
                    String name = nameField.getText();  
                    int cap = Integer.parseInt(memCapField.getText());  
                    if (cap <= 0) throw new CapacityException("Capacity must be positive");  
                    gymList.addGym(new Gym(name, cap, 10, 10));  
                    JOptionPane.showMessageDialog(null, "Gym Added!");  
                } catch (NumberFormatException ex) {  
                    JOptionPane.showMessageDialog(null, "Error: Enter a valid number");  
                } catch (CapacityException ex) {  
                    JOptionPane.showMessageDialog(null, ex.getMessage());  
                }  
            }  
        });  
  
        saveBtn.addActionListener(e -> saveToFile());  
  
        viewBtn.addActionListener(e -> {  
            ResultsFrame results = new ResultsFrame(gymList);  
            results.setVisible(true);  
        });  
    }  
  
    private void saveToFile() {  
        try (PrintWriter out = new PrintWriter(new FileWriter("gyms.txt"))) {  
            Node temp = gymList.getHead();  
            while (temp != null) {  
                out.println("Gym: " + temp.data.getName() + " | Balance: " + temp.data.getBalance());  
                temp = temp.next;  
            }  
            JOptionPane.showMessageDialog(this, "Data Saved to File Successfully");  
        } catch (IOException e) {  
            JOptionPane.showMessageDialog(this, "File Error: " + e.getMessage());  
        }  
    }  