import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ── Shared state (used by both console and frames) ────────────
public class Test {
    static Gym[] gyms     = new Gym[3];
    static int   gymCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWelcome To The Gymnasium-Managment");

        boolean running = true;
        while (running) {
            System.out.println("\n-------------------\n");
            System.out.println("1-Create Gym");
            System.out.println("2-Manage Gym");
            System.out.println("3-Delete Gym");
            System.out.println("4-Exit Program");
            System.out.println();
            System.out.print("Enter Option Here: ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {

                case 1: // ── Open Frame 1 (Create Gym) ─────────
                    if (Test.gymCount == 3) {
                        System.out.println("No more space (max 3 gyms).");
                        break;
                    }
                    final CreateGymFrame cgf = new CreateGymFrame();
                    // show Frame 1 on the GUI thread, then wait for it to close
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            public void run() { cgf.setVisible(true); }
                        });
                    } catch (Exception ex) {}
                    while (cgf.isShowing()) {
                        try { Thread.sleep(100); } catch (InterruptedException ex) {}
                    }
                    break;

                case 2: // Manage Gym
                    if (Test.gymCount == 0) {
                        System.out.println("No gyms created yet.");
                        break;
                    }
                    boolean chooseMenu = true;
                    while (chooseMenu) {
                        System.out.println("\n-----------Gymnasium-Managment------------\n");
                        System.out.println("Choose your Gym: ");
                        for (int s = 0; s < Test.gymCount; s++) {
                            System.out.println((s + 1) + "-" + Test.gyms[s].getName());
                        }
                        System.out.println((Test.gymCount + 1) + "-Go back");
                        System.out.println();
                        System.out.print("Enter Option Here: ");
                        choice = scanner.nextInt();

                        if (choice == Test.gymCount + 1) break;
                        if (choice < 1 || choice > Test.gymCount) continue;

                        final int gymchoice = choice - 1;
                        boolean ManageMenu = true;

                        while (ManageMenu) {
                            System.out.println("\n-----------Gymnasium-Managment------------\n");
                            System.out.println("1-Sign up Member");
                            System.out.println("2-Remove Member");
                            System.out.println("3-Workout");
                            System.out.println("4-Add Machines");
                            System.out.println("5-Add Coach");
                            System.out.println("6-Get a Coaching Leasson");
                            System.out.println("7-Display Gym info");
                            System.out.println("8-Go Back");
                            System.out.println();
                            System.out.print("Enter Option Here: ");
                            choice = scanner.nextInt();
                            System.out.println();

                            switch (choice) {
                                case 1: // Sign Up Member
                                    System.out.print("Enter Name: ");
                                    String name = scanner.next();
                                    System.out.print("Enter ID: ");
                                    String id = scanner.next();
                                    System.out.print("Enter MemberShip Length (in Months): ");
                                    int MemType = scanner.nextInt();
                                    try {
                                        Member M1 = new Member(name, id, MemType);
                                        Test.gyms[gymchoice].addMember(M1);
                                    } catch (InvalidIdException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 2: // Remove Member
                                    if (Test.gyms[gymchoice].getmemberCount() == 0) {
                                        System.out.println("No Member");
                                        break;
                                    }
                                    for (int idx = 0; idx < Test.gyms[gymchoice].getmemberCount(); idx++) {
                                        System.out.println((idx + 1) + "-" + Test.gyms[gymchoice].getMember(idx).name);
                                    }
                                    System.out.print("Enter Option Here: ");
                                    choice = scanner.nextInt();
                                    Member rm = Test.gyms[gymchoice].getMember(choice - 1);
                                    Test.gyms[gymchoice].removeMember(rm);
                                    break;

                                case 3: // Workout
                                    if (Test.gyms[gymchoice].getmemberCount() == 0) {
                                        System.out.println("No Member to workout");
                                        break;
                                    }
                                    for (int i2 = 0; i2 < Test.gyms[gymchoice].getmemberCount(); i2++) {
                                        System.out.println((i2 + 1) + "-" + Test.gyms[gymchoice].getMember(i2).name);
                                    }
                                    System.out.print("Enter Option Here: ");
                                    choice = scanner.nextInt();
                                    Test.gyms[gymchoice].getMember(choice - 1).Workout();
                                    break;

                                case 4: // Add Machine
                                    System.out.print("Enter Machine Name: ");
                                    String Mname = scanner.next();
                                    Test.gyms[gymchoice].AddMachines(Mname);
                                    break;

                                case 5: // Add Coach
                                    System.out.print("Enter Name: ");
                                    String Cname = scanner.next();
                                    System.out.print("Enter Id: ");
                                    String Cid = scanner.next();
                                    try {
                                        Staff C1 = new Coach(Cname, Cid, 0);
                                        Test.gyms[gymchoice].addStaff(C1);
                                    } catch (InvalidIdException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 6: // Coaching Lesson
                                    if (Test.gyms[gymchoice].getcoachCount() == 0) {
                                        System.out.println("No Coaches Added");
                                        break;
                                    }
                                    for (int i3 = 0; i3 < Test.gyms[gymchoice].getcoachCount(); i3++) {
                                        System.out.println((i3 + 1) + "-" + Test.gyms[gymchoice].getCoach(i3).name);
                                    }
                                    System.out.print("Enter Option Here: ");
                                    choice = scanner.nextInt();
                                    Coach C2 = (Coach) Test.gyms[gymchoice].getCoach(choice - 1);
                                    C2.doCoachingLesson();
                                    break;

                                case 7: // ── Open Frame 2 (Gym Info) ──────────
                                    SwingUtilities.invokeLater(new Runnable() {
                                        public void run() {
                                            new GymInfoFrame(gymchoice).setVisible(true);
                                        }
                                    });
                                    break;

                                case 8:
                                    ManageMenu = false;
                                    break;

                                default:
                                    System.out.println("Choose An Available Option");
                                    break;
                            }

                            if (ManageMenu) {
                                try (FileWriter fw = new FileWriter(Test.gyms[gymchoice].getName() + "_Gym.txt")) {
                                    fw.write(Test.gyms[gymchoice].displayInfo());
                                } catch (IOException e) {
                                    System.out.println("Couldn't write on the file.");
                                }
                            }
                        }
                    }
                    break;

                case 3: // Delete Gym
                    if (Test.gymCount == 0) {
                        System.out.println("No gyms to delete.");
                        break;
                    }
                    for (int i4 = 0; i4 < Test.gymCount; i4++) {
                        System.out.println((i4 + 1) + "-" + Test.gyms[i4].getName());
                    }
                    System.out.println((Test.gymCount + 1) + "-Go Back");
                    System.out.println();
                    System.out.print("Enter Option Here: ");
                    choice = scanner.nextInt();
                    int delchoice = choice - 1;

                    if (choice == Test.gymCount + 1 || delchoice >= Test.gymCount || delchoice < 0) {
                        break;
                    }
                    File deleted = new File(Test.gyms[delchoice].getName() + "_Gym.txt");
                    Test.gyms[delchoice] = Test.gyms[Test.gymCount - 1];
                    Test.gyms[Test.gymCount - 1] = null;
                    Test.gymCount--;
                    if (deleted.delete()) {
                        System.out.println("\nGym Deleted");
                    } else {
                        System.out.println("Gym not deleted");
                    }
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Choose An Available Option");
                    break;
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────
//  FRAME 1 — Create Gym
// ─────────────────────────────────────────────────────────────
class CreateGymFrame extends JFrame {

    CreateGymFrame() {
        super("Create a Gym");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // North: title
        JLabel title = new JLabel("Create a New Gym", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(14, 0, 6, 0));
        add(title, BorderLayout.NORTH);

        // Center: form
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(4, 30, 16, 30));

        final JTextField fName = new JTextField();
        final JTextField fMem  = new JTextField();
        final JTextField fSta  = new JTextField();
        final JTextField fMac  = new JTextField();

        form.add(new JLabel("Gym Name:"));            form.add(fName);
        form.add(new JLabel("Max Members (<=10):"));  form.add(fMem);
        form.add(new JLabel("Max Staff:"));            form.add(fSta);
        form.add(new JLabel("Max Machines:"));         form.add(fMac);
        form.add(new JLabel());
        JButton btnCreate = new JButton("Create Gym");
        form.add(btnCreate);

        add(form, BorderLayout.CENTER);

        final CreateGymFrame self = this;

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = fName.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(self, "Please enter a gym name.");
                    return;
                }
                try {
                    int numMem = Integer.parseInt(fMem.getText().trim());
                    int numSta = Integer.parseInt(fSta.getText().trim());
                    int numMac = Integer.parseInt(fMac.getText().trim());
                    if (numMem > 10) {
                        JOptionPane.showMessageDialog(self, "Max members cannot exceed 10.");
                        return;
                    }
                    if (numSta > 10) {
                        JOptionPane.showMessageDialog(self, "Max staff cannot exceed 10.");
                        return;
                    }
                    if (numMac > 10) {
                        JOptionPane.showMessageDialog(self, "Max machines cannot exceed 10.");
                        return;
                    }
                    Test.gyms[Test.gymCount] = new Gym(name, numMem, numSta, numMac);
                    Test.gymCount++;
                    JOptionPane.showMessageDialog(self, "Gym \"" + name + "\" created!");
                    self.dispose(); // closing signals the console to continue
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Please enter valid numbers.");
                }
            }
        });
    }
}

// ─────────────────────────────────────────────────────────────
//  FRAME 2 — Gym Info
// ─────────────────────────────────────────────────────────────
class GymInfoFrame extends JFrame {

    GymInfoFrame(final int gymIndex) {
        super("Gym Info: " + Test.gyms[gymIndex].getName());
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // North: gym name
        JLabel gymName = new JLabel("Gym: " + Test.gyms[gymIndex].getName(), JLabel.CENTER);
        gymName.setFont(new Font("SansSerif", Font.BOLD, 15));
        gymName.setBorder(BorderFactory.createEmptyBorder(14, 0, 6, 0));
        add(gymName, BorderLayout.NORTH);

        // Center: info in a scrollable text area
        JTextArea area = new JTextArea(Test.gyms[gymIndex].displayInfo());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setMargin(new Insets(10, 10, 10, 10));
        add(new JScrollPane(area), BorderLayout.CENTER);

        // South: Close button
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnClose = new JButton("Close");
        south.add(btnClose);
        add(south, BorderLayout.SOUTH);

        final GymInfoFrame self = this;

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                self.dispose();
            }
        });
    }
}
