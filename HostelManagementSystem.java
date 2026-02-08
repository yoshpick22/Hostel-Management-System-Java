import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HostelManagementSystem extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;
    JTextField usernameField, nameField, roomField;
    JPasswordField passwordField;
    JLabel welcomeLabel;
    ArrayList<String> studentList = new ArrayList<>();
    private final String FILE_NAME = "hostel_records.txt"; // Permanent storage file

    public HostelManagementSystem() {
        loadDataFromFile(); // Automatically loads students when app starts
        setTitle("Official School Hostel System - NK Digital Services");
        setSize(650, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(loginPanel(), "Login");
        mainPanel.add(dashboardPanel(), "Dashboard");

        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
        setVisible(true);
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 245, 250));

        JLabel title = new JLabel("School Admin Portal", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setBounds(175, 40, 300, 40);
        panel.add(title);

        usernameField = new JTextField();
        usernameField.setBounds(250, 120, 200, 30);
        panel.add(new JLabel("Username:")).setBounds(150, 120, 100, 30);
        panel.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 170, 200, 30);
        panel.add(new JLabel("Password:")).setBounds(150, 170, 100, 30);
        panel.add(passwordField);

        JButton loginBtn = new JButton("Secure Login");
        loginBtn.setBounds(250, 230, 150, 40);
        loginBtn.setBackground(new Color(50, 100, 150));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> {
            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("1234")) {
                welcomeLabel.setText("Authorized Access: Admin");
                cardLayout.show(mainPanel, "Dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Unauthorized: Invalid Login", "Security Alert", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(loginBtn);
        return panel;
    }

    private JPanel dashboardPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(235, 255, 240));

        welcomeLabel = new JLabel("Management Dashboard", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        welcomeLabel.setBounds(125, 20, 400, 30);
        panel.add(welcomeLabel);

        String[] actions = {"Register New Student", "View Student Directory", "Search for Student", "Logout System"};
        for (int i = 0; i < actions.length; i++) {
            JButton btn = new JButton(actions[i]);
            btn.setBounds(175, 80 + (i * 70), 300, 50);
            final String act = actions[i];
            btn.addActionListener(e -> {
                if (act.contains("Register")) registerStudent();
                else if (act.contains("View")) viewStudents();
                else if (act.contains("Search")) searchStudent();
                else {
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(mainPanel, "Login");
                }
            });
            panel.add(btn);
        }
        return panel;
    }

    private void registerStudent() {
        nameField = new JTextField();
        roomField = new JTextField();
        Object[] fields = {"Student Full Name:", nameField, "Assigned Room No:", roomField};
        int option = JOptionPane.showConfirmDialog(this, fields, "New Student Entry", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String room = roomField.getText().trim();
            if (!name.isEmpty() && !room.isEmpty()) {
                studentList.add(name + " | Room: " + room);
                saveDataToFile(); // Automatically saves to file
                JOptionPane.showMessageDialog(this, "Success: Student record saved.");
            } else {
                JOptionPane.showMessageDialog(this, "Error: All fields are required.");
            }
        }
    }

    private void viewStudents() {
        StringBuilder sb = new StringBuilder("--- Current School Hostel Records ---\n\n");
        if (studentList.isEmpty()) sb.append("No records found.");
        else for (String s : studentList) sb.append("✔ ").append(s).append("\n");
        
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(area), "Student Directory", JOptionPane.PLAIN_MESSAGE);
    }

    private void searchStudent() {
        String query = JOptionPane.showInputDialog(this, "Enter Student Name to Search:");
        if (query != null && !query.trim().isEmpty()) {
            boolean found = false;
            for (String s : studentList) {
                if (s.toLowerCase().contains(query.toLowerCase())) {
                    JOptionPane.showMessageDialog(this, "Record Found:\n" + s, "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }
            if (!found) JOptionPane.showMessageDialog(this, "No record for '" + query + "'", "Search Result", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveDataToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String s : studentList) pw.println(s);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) studentList.add(line);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HostelManagementSystem::new);
    }
}

        JButton okBtn = new JButton("OK");
        okBtn.setBounds(220, 200, 100, 30);
        okBtn.setBackground(new Color(255, 153, 51));
        okBtn.setForeground(Color.WHITE);
        okBtn.addActionListener(e -&gt; {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if (user.equals("admin") &amp;&amp; pass.equals("1234")) {
                welcomeLabel.setText("Welcome, " + user + "!");
                cardLayout.show(mainPanel, "Dashboard");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        });
        panel.add(okBtn);

        return panel;
    }

    // 🏠 Dashboard Panel
    private JPanel dashboardPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(220, 248, 198));

        welcomeLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setBounds(180, 20, 250, 30);
        panel.add(welcomeLabel);

        JButton regBtn = new JButton("Register Student");
        regBtn.setBounds(200, 80, 180, 30);
        regBtn.addActionListener(e -&gt; registerStudent());
        panel.add(regBtn);

        JButton viewBtn = new JButton("View Students");
        viewBtn.setBounds(200, 130, 180, 30);
        viewBtn.addActionListener(e -&gt; viewStudents());
        panel.add(viewBtn);

        JButton roomBtn = new JButton("Allocate Room");
        roomBtn.setBounds(200, 180, 180, 30);
        roomBtn.addActionListener(e -&gt; allocateRoom());
        panel.add(roomBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(200, 240, 180, 30);
        logoutBtn.setBackground(Color.LIGHT_GRAY);
        logoutBtn.addActionListener(e -&gt; {
            usernameField.setText("");
            passwordField.setText("");
            cardLayout.show(mainPanel, "Login");
        });
        panel.add(logoutBtn);

        return panel;
    }

    // 📝 Register Student
    private void registerStudent() {
        JPanel regPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        nameField = new JTextField();
        roomField = new JTextField();

        regPanel.add(new JLabel("Student Name:"));
        regPanel.add(nameField);
        regPanel.add(new JLabel("Room Number:"));
        regPanel.add(roomField);

        int result = JOptionPane.showConfirmDialog(this, regPanel, "Register Student",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String room = roomField.getText();
            if (!name.isEmpty() &amp;&amp; !room.isEmpty()) {
                studentList.add(name + " - Room " + room);
                JOptionPane.showMessageDialog(this, "Student Registered Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        }
    }

    // 📋 View Students
    private void viewStudents() {
        studentArea = new JTextArea();
        if (studentList.isEmpty()) {
            studentArea.setText("No students registered yet.");
        } else {
            for (String s : studentList) {
                studentArea.append(s + "\n");
            }
        }
        studentArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Registered Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // 🛏 Allocate Room (Simulation)
    private void allocateRoom() {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students available for room allocation.");
        } else {
            JOptionPane.showMessageDialog(this, "Rooms allocated successfully to all registered students.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HostelManagementSystem::new);
    }
}
