import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Test {

    // ── Shared state ──────────────────────────────────────────
    static Gym[]  gyms     = new Gym[3];
    static int    gymCount = 0;
    static int    selected = -1;
    static JFrame frame;

    // ── Entry point ───────────────────────────────────────────
    public static void main(String[] args) {
        showMainMenu();
    }

    // ── Helper: close the current frame and open a new one ────
    static JFrame newFrame(String title, int width, int height) {
        if (frame != null) {
            frame.dispose();
        }
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width, height);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(new Color(245, 246, 248));
        frame = f;
        return f;
    }

    // ─────────────────────────────────────────────────────────
    //  SCREENS
    // ─────────────────────────────────────────────────────────

    static void showMainMenu() {
        JFrame f = newFrame("Gymnasium Management", 440, 380);
        f.setLayout(new BorderLayout(12, 12));

        // North: title + gym count
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(new Color(245, 246, 248));
        top.setBorder(BorderFactory.createEmptyBorder(20, 24, 4, 24));

        JLabel title = new JLabel("Gymnasium Management");
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        title.setForeground(new Color(17, 24, 39));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = new JLabel(gymCount + " / 3 gyms created");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 13));
        sub.setForeground(new Color(107, 114, 128));
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        top.add(title);
        top.add(Box.createVerticalStrut(4));
        top.add(sub);
        f.add(top, BorderLayout.NORTH);

        // Center: 4 action buttons
        JPanel center = new JPanel(new GridLayout(4, 1, 0, 10));
        center.setBackground(new Color(245, 246, 248));
        center.setBorder(BorderFactory.createEmptyBorder(8, 24, 24, 24));

        final JButton btnCreate = makeBtn("+ Create Gym", true);
        final JButton btnManage = makeBtn("  Manage Gym", true);
        final JButton btnDelete = makeBtn("  Delete Gym", false);
        final JButton btnExit   = makeBtn("Exit",         false);
        btnDelete.setBackground(new Color(185, 28, 28));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));

        center.add(btnCreate);
        center.add(btnManage);
        center.add(btnDelete);
        center.add(btnExit);
        f.add(center, BorderLayout.CENTER);

        f.setVisible(true);

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gymCount == 3) {
                    JOptionPane.showMessageDialog(frame,
                        "No more space (max 3 gyms).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                showCreateGym();
            }
        });

        btnManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gymCount == 0) {
                    JOptionPane.showMessageDialog(frame,
                        "No gyms created yet.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                showSelectGym("manage");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gymCount == 0) {
                    JOptionPane.showMessageDialog(frame,
                        "No gyms created yet.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                showSelectGym("delete");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // ─────────────────────────────────────────────────────────

    static void showCreateGym() {
        JFrame f = newFrame("Create New Gym", 440, 380);
        f.setLayout(new BorderLayout(12, 12));

        JLabel title = new JLabel("Create New Gym");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(new Color(17, 24, 39));
        title.setBorder(BorderFactory.createEmptyBorder(20, 24, 4, 24));
        f.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(new Color(245, 246, 248));
        form.setBorder(BorderFactory.createEmptyBorder(8, 24, 24, 24));

        final JTextField fName = makeField();
        final JTextField fMem  = makeField();
        final JTextField fSta  = makeField();
        final JTextField fMac  = makeField();

        form.add(makeLabel("Gym Name:"));           form.add(fName);
        form.add(makeLabel("Max Members (<=10):")); form.add(fMem);
        form.add(makeLabel("Max Staff:"));           form.add(fSta);
        form.add(makeLabel("Max Machines:"));        form.add(fMac);

        JButton btnCancel = makeBtn("Cancel", false);
        JButton btnCreate = makeBtn("Create", true);
        form.add(btnCancel);
        form.add(btnCreate);

        f.add(form, BorderLayout.CENTER);
        f.setVisible(true);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = fName.getText().trim();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(frame,
                            "Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int numMem = Integer.parseInt(fMem.getText().trim());
                    int numSta = Integer.parseInt(fSta.getText().trim());
                    int numMac = Integer.parseInt(fMac.getText().trim());
                    if (numMem > 10) {
                        JOptionPane.showMessageDialog(frame,
                            "Max members cannot exceed 10.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    gyms[gymCount] = new Gym(name, numMem, numSta, numMac);
                    writeFile(gymCount);
                    gymCount++;
                    JOptionPane.showMessageDialog(frame, "Gym \"" + name + "\" created!");
                    showMainMenu();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                        "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // ─────────────────────────────────────────────────────────

    static void showSelectGym(final String mode) {
        int height = 120 + gymCount * 60;
        String title = mode.equals("manage") ? "Select Gym to Manage" : "Select Gym to Delete";

        JFrame f = newFrame(title, 440, height);
        f.setLayout(new BorderLayout(8, 8));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        titleLabel.setForeground(new Color(17, 24, 39));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 24, 4, 24));
        f.add(titleLabel, BorderLayout.NORTH);

        JPanel list = new JPanel(new GridLayout(gymCount + 1, 1, 0, 10));
        list.setBackground(new Color(245, 246, 248));
        list.setBorder(BorderFactory.createEmptyBorder(8, 24, 24, 24));

        for (int i = 0; i < gymCount; i++) {
            final int idx = i;
            JButton btn;
            if (mode.equals("manage")) {
                btn = makeBtn("  " + gyms[i].getName(), true);
            } else {
                btn = makeBtn("X  " + gyms[i].getName(), false);
                btn.setBackground(new Color(185, 28, 28));
                btn.setForeground(Color.WHITE);
                btn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
            }

            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (mode.equals("manage")) {
                        showManageGym(idx);
                    } else {
                        int confirm = JOptionPane.showConfirmDialog(frame,
                            "Delete \"" + gyms[idx].getName() + "\"?",
                            "Confirm Delete", JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                        if (confirm != JOptionPane.YES_OPTION) return;

                        File file = new File(gyms[idx].getName() + "_Gym.txt");
                        for (int j = idx; j < gymCount - 1; j++) {
                            gyms[j] = gyms[j + 1];
                        }
                        gyms[gymCount - 1] = null;
                        gymCount--;
                        file.delete();
                        JOptionPane.showMessageDialog(frame, "Gym deleted.");
                        showMainMenu();
                    }
                }
            });
            list.add(btn);
        }

        JButton btnBack = makeBtn("<- Back", false);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });
        list.add(btnBack);

        f.add(list, BorderLayout.CENTER);
        f.setVisible(true);
    }

    // ─────────────────────────────────────────────────────────

    static void showManageGym(final int gymIndex) {
        selected = gymIndex;

        JFrame f = newFrame("Manage: " + gyms[gymIndex].getName(), 460, 480);
        f.setLayout(new BorderLayout(12, 12));

        // North: gym name + live stats
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(new Color(245, 246, 248));
        top.setBorder(BorderFactory.createEmptyBorder(20, 24, 4, 24));

        JLabel gymName = new JLabel(gyms[gymIndex].getName());
        gymName.setFont(new Font("SansSerif", Font.BOLD, 17));
        gymName.setForeground(new Color(17, 24, 39));
        gymName.setAlignmentX(Component.LEFT_ALIGNMENT);

        int machineCount = countMachines(gymIndex);
        JLabel stats = new JLabel(
            "Members: " + gyms[gymIndex].getmemberCount() +
            "  |  Coaches: " + gyms[gymIndex].getcoachCount() +
            "  |  Machines: " + machineCount);
        stats.setFont(new Font("SansSerif", Font.PLAIN, 12));
        stats.setForeground(new Color(107, 114, 128));
        stats.setAlignmentX(Component.LEFT_ALIGNMENT);

        top.add(gymName);
        top.add(Box.createVerticalStrut(4));
        top.add(stats);
        f.add(top, BorderLayout.NORTH);

        // Center: 4x2 action button grid
        JPanel center = new JPanel(new GridLayout(4, 2, 10, 10));
        center.setBackground(new Color(245, 246, 248));
        center.setBorder(BorderFactory.createEmptyBorder(4, 24, 24, 24));

        final JButton btnSignUp  = makeBtn("+ Sign Up Member",  true);
        final JButton btnRemove  = makeBtn("- Remove Member",   true);
        final JButton btnWorkout = makeBtn("  Workout",          true);
        final JButton btnMachine = makeBtn("  Add Machine",      true);
        final JButton btnCoach   = makeBtn("  Add Coach",        true);
        final JButton btnLesson  = makeBtn("  Coaching Lesson",  true);
        final JButton btnInfo    = makeBtn("  Display Info",     true);
        final JButton btnBack    = makeBtn("<- Back",            false);

        center.add(btnSignUp);  center.add(btnRemove);
        center.add(btnWorkout); center.add(btnMachine);
        center.add(btnCoach);   center.add(btnLesson);
        center.add(btnInfo);    center.add(btnBack);
        f.add(center, BorderLayout.CENTER);

        f.setVisible(true);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainMenu();
            }
        });

        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpDialog(gymIndex);
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gyms[gymIndex].getmemberCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "No members in this gym.");
                    return;
                }
                int mc = gyms[gymIndex].getmemberCount();
                String[] names = new String[mc];
                for (int i = 0; i < mc; i++) {
                    names[i] = gyms[gymIndex].getMember(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(frame,
                    "Select member to remove:", "Remove Member",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < mc; i++) {
                    Member rm = gyms[gymIndex].getMember(i);
                    if (rm.name.equals(chosen)) {
                        gyms[gymIndex].removeMember(rm);
                        writeFile(gymIndex);
                        JOptionPane.showMessageDialog(frame, chosen + " removed.");
                        showManageGym(gymIndex);
                        return;
                    }
                }
            }
        });

        btnWorkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gyms[gymIndex].getmemberCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "No members to workout.");
                    return;
                }
                int mc = gyms[gymIndex].getmemberCount();
                String[] names = new String[mc];
                for (int i = 0; i < mc; i++) {
                    names[i] = gyms[gymIndex].getMember(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(frame,
                    "Who is working out?", "Workout",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < mc; i++) {
                    Member m = gyms[gymIndex].getMember(i);
                    if (m.name.equals(chosen)) {
                        m.Workout();
                        JOptionPane.showMessageDialog(frame,
                            chosen + " finished their workout!");
                        return;
                    }
                }
            }
        });

        btnMachine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mName = JOptionPane.showInputDialog(frame,
                    "Enter machine name:", "Add Machine", JOptionPane.PLAIN_MESSAGE);
                if (mName == null || mName.trim().isEmpty()) return;
                gyms[gymIndex].AddMachines(mName.trim());
                writeFile(gymIndex);
                JOptionPane.showMessageDialog(frame,
                    "Machine \"" + mName.trim() + "\" added.");
                showManageGym(gymIndex);
            }
        });

        btnCoach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCoachDialog(gymIndex);
            }
        });

        btnLesson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (gyms[gymIndex].getcoachCount() == 0) {
                    JOptionPane.showMessageDialog(frame, "No coaches in this gym.");
                    return;
                }
                int cc = gyms[gymIndex].getcoachCount();
                String[] names = new String[cc];
                for (int i = 0; i < cc; i++) {
                    names[i] = gyms[gymIndex].getCoach(i).name;
                }
                String chosen = (String) JOptionPane.showInputDialog(frame,
                    "Select a coach:", "Coaching Lesson",
                    JOptionPane.PLAIN_MESSAGE, null, names, names[0]);
                if (chosen == null) return;
                for (int i = 0; i < cc; i++) {
                    Staff s = gyms[gymIndex].getCoach(i);
                    if (s.name.equals(chosen) && s instanceof Coach) {
                        Coach c = (Coach) s;
                        c.doCoachingLesson();
                        writeFile(gymIndex);
                        JOptionPane.showMessageDialog(frame,
                            "Lesson done with " + chosen + "!");
                        return;
                    }
                }
            }
        });

        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoDialog(gymIndex);
            }
        });
    }

    // ─────────────────────────────────────────────────────────
    //  DIALOGS
    // ─────────────────────────────────────────────────────────

    static void signUpDialog(final int gymIndex) {
        final JDialog dialog = new JDialog(frame, "Sign Up Member", true);
        dialog.setSize(360, 260);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(new Color(245, 246, 248));
        dialog.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(new Color(245, 246, 248));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 16, 20));

        final JTextField fName   = makeField();
        final JTextField fId     = makeField();
        final JTextField fMonths = makeField();

        form.add(makeLabel("Name:"));                 form.add(fName);
        form.add(makeLabel("ID (exactly 4 chars):")); form.add(fId);
        form.add(makeLabel("Membership (months):"));  form.add(fMonths);

        JButton btnCancel = makeBtn("Cancel", false);
        JButton btnOk     = makeBtn("Sign Up", true);
        form.add(btnCancel);
        form.add(btnOk);

        dialog.add(form, BorderLayout.CENTER);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name   = fName.getText().trim();
                    String id     = fId.getText().trim();
                    int    months = Integer.parseInt(fMonths.getText().trim());
                    Member m = new Member(name, id, months);
                    gyms[gymIndex].addMember(m);
                    writeFile(gymIndex);
                    JOptionPane.showMessageDialog(dialog, name + " signed up!");
                    dialog.dispose();
                    showManageGym(gymIndex);
                } catch (InvalidIdException ex) {
                    JOptionPane.showMessageDialog(dialog,
                        ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog,
                        "Enter a valid number for months.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog,
                        ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.setVisible(true);
    }

    // ─────────────────────────────────────────────────────────

    static void addCoachDialog(final int gymIndex) {
        final JDialog dialog = new JDialog(frame, "Add Coach", true);
        dialog.setSize(340, 220);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(new Color(245, 246, 248));
        dialog.setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(new Color(245, 246, 248));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 16, 20));

        final JTextField fName = makeField();
        final JTextField fId   = makeField();

        form.add(makeLabel("Name:"));                 form.add(fName);
        form.add(makeLabel("ID (exactly 4 chars):")); form.add(fId);

        JButton btnCancel = makeBtn("Cancel", false);
        JButton btnOk     = makeBtn("Add Coach", true);
        form.add(btnCancel);
        form.add(btnOk);

        dialog.add(form, BorderLayout.CENTER);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = fName.getText().trim();
                    String id   = fId.getText().trim();
                    Staff coach = new Coach(name, id, 0);
                    gyms[gymIndex].addStaff(coach);
                    writeFile(gymIndex);
                    JOptionPane.showMessageDialog(dialog,
                        "Coach \"" + name + "\" added!");
                    dialog.dispose();
                    showManageGym(gymIndex);
                } catch (InvalidIdException ex) {
                    JOptionPane.showMessageDialog(dialog,
                        ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.setVisible(true);
    }

    // ─────────────────────────────────────────────────────────

    static void infoDialog(final int gymIndex) {
        final JDialog dialog = new JDialog(frame,
            "Info  —  " + gyms[gymIndex].getName(), false);
        dialog.setSize(440, 340);
        dialog.setLocationRelativeTo(frame);
        dialog.getContentPane().setBackground(new Color(245, 246, 248));
        dialog.setLayout(new BorderLayout(8, 8));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setMargin(new Insets(12, 12, 12, 12));

        File f = new File(gyms[gymIndex].getName() + "_Gym.txt");
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                StringBuilder sb = new StringBuilder();
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine()).append("\n");
                }
                sc.close();
                area.setText(sb.toString());
            } catch (FileNotFoundException ex) {
                area.setText(gyms[gymIndex].displayInfo());
            }
        } else {
            area.setText(gyms[gymIndex].displayInfo());
        }

        dialog.add(new JScrollPane(area), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(new Color(245, 246, 248));
        JButton btnClose = makeBtn("Close", false);
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        bottom.add(btnClose);
        dialog.add(bottom, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    // ─────────────────────────────────────────────────────────
    //  UTILITIES
    // ─────────────────────────────────────────────────────────

    static int countMachines(int gymIndex) {
        int count = 0;
        Node curr = gyms[gymIndex].getmachineList().getHead();
        while (curr != null) {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    static void writeFile(int idx) {
        try (FileWriter fw = new FileWriter(gyms[idx].getName() + "_Gym.txt")) {
            fw.write(gyms[idx].displayInfo());
        } catch (IOException ex) {
            System.out.println("Could not write file.");
        }
    }

    static JButton makeBtn(String label, boolean primary) {
        JButton b = new JButton(label);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (primary) {
            b.setBackground(new Color(37, 99, 235));
            b.setForeground(Color.WHITE);
            b.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        } else {
            b.setBackground(Color.WHITE);
            b.setForeground(new Color(17, 24, 39));
            b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                BorderFactory.createEmptyBorder(9, 17, 9, 17)));
        }
        return b;
    }

    static JTextField makeField() {
        JTextField t = new JTextField(18);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(209, 213, 219)),
            BorderFactory.createEmptyBorder(7, 9, 7, 9)));
        return t;
    }

    static JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.PLAIN, 13));
        l.setForeground(new Color(17, 24, 39));
        return l;
    }
}
