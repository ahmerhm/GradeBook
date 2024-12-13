package Gradebook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GradebookGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private ArrayList<Student> students = new ArrayList<>();
    private static File gradeBook = new File("Gradebook\\Gradebook.csv");
    private Scanner fileReader;

    public void readGradebook(File gradeBook) {
        try {
            fileReader = new Scanner(gradeBook);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                String name = data[0];
                String letterGrade = data[1];
                double percentage = Double.parseDouble(data[2]);
                double homeworks = Double.parseDouble(data[3]);
                double quizzes = Double.parseDouble(data[4]);
                double midTerm1 = Double.parseDouble(data[5]);
                double midTerm2 = Double.parseDouble(data[6]);
                double midTerm3 = Double.parseDouble(data[7]);
                double finalTest = Double.parseDouble(data[8]);

                Student student = new Student(name, letterGrade, percentage, homeworks, quizzes, midTerm1, midTerm2, midTerm3, finalTest);
                students.add(student);
                addStudentToTable(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGradeBook(ArrayList<Student> students) {
        try {
            FileWriter writer = new FileWriter("Gradebook\\Gradebook.csv");
            for (Student student : students) {
                writer.write(student.getName() + ",");
                writer.write(student.getLetterGrade() + ",");
                writer.write(Integer.toString((int) Math.round(student.getPercentage())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getHomeworks())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getQuizzes())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getMidTerm1())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getMidTerm2())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getMidTerm3())) + ",");
                writer.write(Integer.toString((int) Math.round(student.getFinalTest())) + "\n");
            }
            writer.close();
            System.out.println("Gradebook updated successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the Gradebook: " + e.getMessage());
        }
    }

    public void sortStudentsByName() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getName().compareToIgnoreCase(student2.getName());
            }
        });
        
        tableModel.setRowCount(0); 
        for (Student student : students) {
            addStudentToTable(student); 
        }
    }

    public void sortStudentsByPercentageDescending() {
        
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
               
                return Double.compare(student2.getPercentage(), student1.getPercentage());
            }
        });

 
        tableModel.setRowCount(0); 
        for (Student student : students) {
            addStudentToTable(student); 
        }
    }

    public void displayFailingStudents() {
 
        tableModel.setRowCount(0);
    
       
        for (Student student : students) {
            if (student.getPercentage() < 60 && student.getLetterGrade().equalsIgnoreCase("F")) {
                addStudentToTable(student); 
            }
        }
    }

    public void addStudentToTable(Student student) {
        Object[] rowData = {
                student.getName(),
                student.getLetterGrade(),
                student.getPercentage(),
                student.getHomeworks(),
                student.getQuizzes(),
                student.getMidTerm1(),
                student.getMidTerm2(),
                student.getMidTerm3(),
                student.getFinalTest()
        };
        tableModel.addRow(rowData);
    }

    public boolean checkPercentage(double trialPercentage, double homeworks, double quizzes, double midTerm1, double midTerm2, double midTerm3, double finalTest) {
        double percentage = (homeworks + quizzes + midTerm1 + midTerm2 + midTerm3 + finalTest) / 6;
        return trialPercentage == percentage;
    }

    public GradebookGUI() {
        setTitle("Gradebook");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Name", "Letter Grade", "%", "Homeworks", "Quizzes", "Midterm 1", "Midterm 2", "Midterm 3", "Final"});

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add Student");
        JButton deleteBtn = new JButton("Delete Student");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddStudentDialog addStudentDialog = new AddStudentDialog();
                addStudentDialog.setVisible(true);
 
                addStudentDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        Student newStudent = addStudentDialog.getStudent();
                        if (newStudent != null) {
                            students.add(newStudent);
                            addStudentToTable(newStudent);
                            updateGradeBook(students);
                        }
                    }
                });
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open dialog to input student name
                String studentName = JOptionPane.showInputDialog(null, "Enter the name of the student to delete:", "Delete Student", JOptionPane.QUESTION_MESSAGE);
                
                // Check if studentName is null (cancel button clicked) or empty
                if (studentName != null && !studentName.isEmpty()) {
                    boolean studentFound = false;
                    for (int i = 0; i < students.size(); i++) {
                        Student student = students.get(i);
                        if (student.getName().equalsIgnoreCase(studentName)) {
                            students.remove(i); 
                            updateGradeBook(students); 
                            tableModel.removeRow(i); 
                            studentFound = true;
                            break;
                        }
                    }
                    if (!studentFound) {
                        JOptionPane.showMessageDialog(null, "Student '" + studentName + "' not found in the gradebook.", "Student Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);


        String[] sortOptions = {"Name", "Descending Grades", "Failing"};
        JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
    
                if (selectedOption.equals("Name")) {
                    sortStudentsByName();
                } else if (selectedOption.equals("Descending Grades")) {
                    sortStudentsByPercentageDescending();
                } else if (selectedOption.equals("Failing")) {
                    displayFailingStudents();
                }
            }
        });

        JPanel sortPanel = new JPanel();
        sortPanel.add(new JLabel("Sort by:"));
        sortPanel.add(sortComboBox);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(sortPanel, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GradebookGUI gradebookGUI = new GradebookGUI();
                gradebookGUI.readGradebook(gradeBook);
                gradebookGUI.setVisible(true);
            }
        });
    }
}

class AddStudentDialog extends JDialog {
    private JTextField nameField;
    private JTextField letterGradeField;
    private JTextField percentageField;
    private JTextField homeworksField;
    private JTextField quizzesField;
    private JTextField midTerm1Field;
    private JTextField midTerm2Field;
    private JTextField midTerm3Field;
    private JTextField finalTestField;

    private Student student;

    public AddStudentDialog() {
        setTitle("Add Student");
        setSize(300, 400);
        setLayout(new GridLayout(10, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Letter Grade:"));
        letterGradeField = new JTextField();
        add(letterGradeField);

        add(new JLabel("Percentage:"));
        percentageField = new JTextField();
        add(percentageField);

        add(new JLabel("Homeworks:"));
        homeworksField = new JTextField();
        add(homeworksField);

        add(new JLabel("Quizzes:"));
        quizzesField = new JTextField();
        add(quizzesField);

        add(new JLabel("Midterm 1:"));
        midTerm1Field = new JTextField();
        add(midTerm1Field);

        add(new JLabel("Midterm 2:"));
        midTerm2Field = new JTextField();
        add(midTerm2Field);

        add(new JLabel("Midterm 3:"));
        midTerm3Field = new JTextField();
        add(midTerm3Field);

        add(new JLabel("Final:"));
        finalTestField = new JTextField();
        add(finalTestField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String letterGrade = letterGradeField.getText();
                double percentage = Double.parseDouble(percentageField.getText());
                double homeworks = Double.parseDouble(homeworksField.getText());
                double quizzes = Double.parseDouble(quizzesField.getText());
                double midTerm1 = Double.parseDouble(midTerm1Field.getText());
                double midTerm2 = Double.parseDouble(midTerm2Field.getText());
                double midTerm3 = Double.parseDouble(midTerm3Field.getText());
                double finalTest = Double.parseDouble(finalTestField.getText());

                student = new Student(name, letterGrade, percentage, homeworks, quizzes, midTerm1, midTerm2, midTerm3, finalTest);
                dispose();
            }
        });
        add(addButton);
    }

    public Student getStudent() {
        return student;
    }
}
