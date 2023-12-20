import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.io.*;

class Doctor {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    void insertDoctor(String name, String phno, String id, String spcln, String availablity) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO doctors (name, phno, id, specialization, availability) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phno);
            preparedStatement.setString(3, id);
            preparedStatement.setString(4, spcln);
            preparedStatement.setString(5, availablity);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Doctor details inserted successfully.");
            } else {
                System.out.println("Failed to insert doctor details.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void show() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors")) {

            while (resultSet.next()) {
                String n = resultSet.getString("name");
                String i = resultSet.getString("id");
                String p = resultSet.getString("phno");
                String s = resultSet.getString("specialization");
                String a = resultSet.getString("availability");

                System.out.println();
                System.out.println("Name: DR. " + n);
                System.out.println("ID: " + i);
                System.out.println("Phone No: " + p);
                System.out.println("Specialization: " + s);
                System.out.println("Availability: " + a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}

class DoctorGUI extends JFrame {
    private Doctor doctor = new Doctor();

    private JTextField nameField;
    private JTextField phnoField;
    private JTextField idField;
    private JTextField spclnField;
    private JTextField availabilityField;

    public DoctorGUI() {
        setTitle("Hospital Registry");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        createGUIComponents();

        setVisible(true);
    }

    private void createGUIComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(11, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel phnoLabel = new JLabel("Phone No:");
        phnoField = new JTextField();
        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel spclnLabel = new JLabel("Specialization:");
        spclnField = new JTextField();
        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityField = new JTextField();

        JButton insertButton = new JButton("Insert Doctor Details");
        JButton viewAllButton = new JButton("View All");
        JButton specializationButton = new JButton("Search by Specialization");
        JButton nameButton = new JButton("Search by Name");
        JButton deleteByNameButton = new JButton("Delete by Name");
        JButton deleteByIdButton = new JButton("Delete by ID");
        JButton exitButton = new JButton("Exit");

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phnoLabel);
        panel.add(phnoField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(spclnLabel);
        panel.add(spclnField);
        panel.add(availabilityLabel);
        panel.add(availabilityField);

        panel.add(insertButton);
        panel.add(viewAllButton);
        panel.add(specializationButton);
        panel.add(nameButton);
        panel.add(deleteByNameButton);
        panel.add(deleteByIdButton);
        panel.add(exitButton);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phno = phnoField.getText();
                String id = idField.getText();
                String spcln = spclnField.getText();
                String availability = availabilityField.getText();

                doctor.insertDoctor(name, phno, id, spcln, availability);
                clearFields();
            }
        });

        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor.show();
                clearFields();
            }
        });

        /*specializationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String specialization = spclnField.getText();
                doctor.showSpecialization(specialization);
                clearFields();
            }
        });

        nameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = nameField.getText();
                doctor.showName(doctorName);
                clearFields();
            }
        });

        deleteByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String docName = nameField.getText();
                doctor.deleteByName(docName);
                clearFields();
            }
        });

        deleteByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String docId = idField.getText();
                doctor.deleteById(docId);
                clearFields();
            }
        });*/

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting........");
                System.exit(0);
            }
        });

        add(panel);
    }

    private void clearFields() {
        nameField.setText("");
        phnoField.setText("");
        idField.setText("");
        spclnField.setText("");
        availabilityField.setText("");
    }
}

public class HospitalRegistryGUI {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new DoctorGUI());
    }
}

