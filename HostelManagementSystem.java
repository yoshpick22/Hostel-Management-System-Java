Import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HostelManagementSystem extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;
    JTextField usernameField, nameField, roomField;
    JPasswordField passwordField;
    JTextArea studentArea;
    JLabel welcomeLabel;

    ArrayList studentList = new ArrayList();

    public HostelManagementSystem() {
        setTitle("Hostel Management System");
        setSize(600, 450);
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

    // 🔐 Login Panel
    private JPanel loginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 241, 210));

        JLabel title = new JLabel("Hostel Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(180, 30, 240, 30);
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 100, 100, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(220, 100, 200, 25);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 140, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 140, 200, 25);
        panel.add(passwordField);

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
