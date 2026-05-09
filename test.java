import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ── Shared state ──────────────────────────────────────────────
public class Test {
    static Gym[] gyms     = new Gym[3];
    static int   gymCount = 0;

    public static void main(String[] args) {
        MainMenuFrame menu = new MainMenuFrame();
        menu.setVisible(true);
    }
}

// ─────────────────────────────────────────────────────────────
//  FRAME 1 — Main Menu
// ─────────────────────────────────────────────────────────────
class MainMenuFrame extends JFrame {

    MainMenuFrame() {
        super("Gymnasium Management");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // North: title
        JLabel title = new JLabel("Gymnasium Management", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        // Center: 4 buttons
        JPanel center = new JPanel(new GridLayout(4, 1, 0, 10));
        center.setBorder(BorderFactory.createEmptyBorder(4, 30, 24, 30));

        JButton btnCreate = new JButton("Create Gym");
        JButton btnManage = new JButton("Manage Gym");
        JButton btnDelete = new JButton("Delete Gym");
        JButton btnExit   = new JButton("Exit");

        center.add(btnCreate);
        center.add(btnManage);
        center.add(btnDelete);
        center.add(btnExit);
        add(center, BorderLayout.CENTER);

        // ── Listeners ─────────────────────────────────────────
        final MainMenuFrame self = this;

        // Create Gym
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Test.gymCount == 3) {
                    JOptionPane.showMessageDialog(self, "No more space (max 3 gyms).");
                    return;
                }
                String name = JOptionPane.showInputDialog(self, "Gym Name:");
                if (name == null || name.trim().isEmpty()) return;

                String memStr = JOptionPane.showInputDialog(self, "Max Members (max 10):");
                if (memStr == null) return;
                String staStr = JOptionPane.showInputDialog(self, "Max Staff:");
                if (staStr == null) return;
                String macStr = JOptionPane.showInputDialog(self, "Max Machines:");
                if (macStr == null) return;

                try {
                    int numMem = Integer.parseInt(memStr.trim());
                    int numSta = Integer.parseInt(staStr.trim());
                    int numMac = Integer.parseInt(macStr.trim());
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
                    Test.gyms[Test.gymCount] = new Gym(name.trim(), numMem, numSta, numMac);
                    Test.gymCount++;
                    JOptionPane.showMessageDialog(self, "Gym \"" + name.trim() + "\" created!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Please enter valid numbers.");
                }
            }
        });

        // Manage Gym
        btnManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Test.gymCount == 0) {
                    JOptionPane.showMessageDialog(self, "No gyms created yet.");
                    return;
                }
                String[] names = new String[Test.gymCount];
                for (int i = 0; i < Test.gymCount; i++) {
                    names[i] = Test.gyms[i].getName();
                }
                String chosen = (String) JOptionPane.showInputDialog(self,
                    "Select a gym to manage:", "Manage Gym",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;

                for (int i = 0; i < Test.gymCount; i++) {
                    if (Test.gyms[i].getName().equals(chosen)) {
                        self.setVisible(false);
                        new GymDetailsFrame(i, self).setVisible(true);
                        return;
                    }
                }
            }
        });

        // Delete Gym
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Test.gymCount == 0) {
                    JOptionPane.showMessageDialog(self, "No gyms created yet.");
                    return;
                }
                String[] names = new String[Test.gymCount];
                for (int i = 0; i < Test.gymCount; i++) {
                    names[i] = Test.gyms[i].getName();
                }
                String chosen = (String) JOptionPane.showInputDialog(self,
                    "Select a gym to delete:", "Delete Gym",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;

                for (int i = 0; i < Test.gymCount; i++) {
                    if (Test.gyms[i].getName().equals(chosen)) {
                        int confirm = JOptionPane.showConfirmDialog(self,
                            "Delete \"" + chosen + "\"?", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                        if (confirm != JOptionPane.YES_OPTION) return;

                        File f = new File(Test.gyms[i].getName() + "_Gym.txt");
                        for (int j = i; j < Test.gymCount - 1; j++) {
                            Test.gyms[j] = Test.gyms[j + 1];
                        }
                        Test.gyms[Test.gymCount - 1] = null;
                        Test.gymCount--;
                        f.delete();
                        JOptionPane.showMessageDialog(self, "\"" + chosen + "\" deleted.");
                        return;
                    }
                }
            }
        });

        // Exit
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

//  FRAME 2 — Gym Details
class GymDetailsFrame extends JFrame {

    GymDetailsFrame(final int gymIndex, final MainMenuFrame menuFrame) {
        super("Gym Details: " + Test.gyms[gymIndex].getName());
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // North: gym name
        JLabel gymName = new JLabel(Test.gyms[gymIndex].getName(), JLabel.CENTER);
        gymName.setFont(new Font("SansSerif", Font.BOLD, 16));
        gymName.setBorder(BorderFactory.createEmptyBorder(16, 0, 8, 0));
        add(gymName, BorderLayout.NORTH);

        // Center: 4x2 button grid
        JPanel center = new JPanel(new GridLayout(4, 2, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(4, 24, 24, 24));

        JButton btnSignUp  = new JButton("Sign Up Member");
        JButton btnRemove  = new JButton("Remove Member");
        JButton btnWorkout = new JButton("Workout");
        JButton btnMachine = new JButton("Add Machine");
        JButton btnCoach   = new JButton("Add Coach");
        JButton btnLesson  = new JButton("Coaching Lesson");
        JButton btnInfo    = new JButton("Display Info");
        JButton btnBack    = new JButton("Back");

        center.add(btnSignUp);  center.add(btnRemove);
        center.add(btnWorkout); center.add(btnMachine);
        center.add(btnCoach);   center.add(btnLesson);
        center.add(btnInfo);    center.add(btnBack);
        add(center, BorderLayout.CENTER);

        // ── Listeners ─────────────────────────────────────────
        final GymDetailsFrame self = this;

        // Back
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                self.dispose();
                menuFrame.setVisible(true);
            }
        });

        // Sign Up Member
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(self, "Member Name:");
                if (name == null || name.trim().isEmpty()) return;
                String id = JOptionPane.showInputDialog(self, "Member ID (exactly 4 chars):");
                if (id == null) return;
                String monthsStr = JOptionPane.showInputDialog(self, "Membership Length (months):");
                if (monthsStr == null) return;
                try {
                    int months = Integer.parseInt(monthsStr.trim());
                    Member m = new Member(name.trim(), id.trim(), months);
                    Test.gyms[gymIndex].addMember(m);
                    JOptionPane.showMessageDialog(self, name.trim() + " signed up!");
                } catch (InvalidIdException ex) {
                    JOptionPane.showMessageDialog(self, ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Enter a valid number for months.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(self, ex.getMessage());
                }
            }
        });

        // Remove Member
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mc = Test.gyms[gymIndex].getmemberCount();
                if (mc == 0) {
                    JOptionPane.showMessageDialog(self, "No members in this gym.");
                    return;
                }
                String[] names = new String[mc];
                for (int i = 0; i < mc; i++) {
                    names[i] = Test.gyms[gymIndex].getMember(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(self,
                    "Select member to remove:", "Remove Member",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < mc; i++) {
                    Member rm = Test.gyms[gymIndex].getMember(i);
                    if (rm.name.equals(chosen)) {
                        Test.gyms[gymIndex].removeMember(rm);
                        JOptionPane.showMessageDialog(self, chosen + " removed.");
                        return;
                    }
                }
            }
        });

        // Workout
        btnWorkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mc = Test.gyms[gymIndex].getmemberCount();
                if (mc == 0) {
                    JOptionPane.showMessageDialog(self, "No members to workout.");
                    return;
                }
                String[] names = new String[mc];
                for (int i = 0; i < mc; i++) {
                    names[i] = Test.gyms[gymIndex].getMember(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(self,
                    "Who is working out?", "Workout",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < mc; i++) {
                    Member m = Test.gyms[gymIndex].getMember(i);
                    if (m.name.equals(chosen)) {
                        m.Workout();
                        JOptionPane.showMessageDialog(self, chosen + " finished their workout!");
                        return;
                    }
                }
            }
        });

        // Add Machine
        btnMachine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mName = JOptionPane.showInputDialog(self, "Enter machine name:");
                if (mName == null || mName.trim().isEmpty()) return;
                Test.gyms[gymIndex].AddMachines(mName.trim());
                JOptionPane.showMessageDialog(self, "Machine \"" + mName.trim() + "\" added.");
            }
        });

        // Add Coach
        btnCoach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(self, "Coach Name:");
                if (name == null || name.trim().isEmpty()) return;
                String id = JOptionPane.showInputDialog(self, "Coach ID (exactly 4 chars):");
                if (id == null) return;
                try {
                    Staff coach = new Coach(name.trim(), id.trim(), 0);
                    Test.gyms[gymIndex].addStaff(coach);
                    JOptionPane.showMessageDialog(self, "Coach \"" + name.trim() + "\" added!");
                } catch (InvalidIdException ex) {
                    JOptionPane.showMessageDialog(self, ex.getMessage());
                }
            }
        });

        // Coaching Lesson
        btnLesson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cc = Test.gyms[gymIndex].getcoachCount();
                if (cc == 0) {
                    JOptionPane.showMessageDialog(self, "No coaches in this gym.");
                    return;
                }
                String[] names = new String[cc];
                for (int i = 0; i < cc; i++) {
                    names[i] = Test.gyms[gymIndex].getCoach(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(self,
                    "Select a coach:", "Coaching Lesson",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < cc; i++) {
                    Staff s = Test.gyms[gymIndex].getCoach(i);
                    if (s.name.equals(chosen) && s instanceof Coach) {
                        Coach c = (Coach) s;
                        c.doCoachingLesson();
                        JOptionPane.showMessageDialog(self, "Lesson done with " + chosen + "!");
                        return;
                    }
                }
            }
        });

        // Display Info
        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(self,
                    Test.gyms[gymIndex].displayInfo(),
                    "Info: " + Test.gyms[gymIndex].getName(),
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
