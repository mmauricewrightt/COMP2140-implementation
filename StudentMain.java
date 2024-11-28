import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentMain extends JFrame{
    private JFrame studentFrame = new JFrame("Security Access System");
    private JButton    cmdAddStudent;
    private JButton    cmdEditStudent;
    private JButton    cmdDeleteStudent;
    private JButton    cmdSearchStudent;
    private JButton    cmdCheckInVisitor;
    private JButton    cmdCheckOutVisitor;
    private JButton    cmdSearchVisitor;
    private JButton    cmdClose;
    private JPanel pnlCommand;
    private JPanel pnlDisplay;

    protected JTextField IDnumber;

    public StudentMain(){
        
        studentFrame.setDefaultCloseOperation(studentFrame.EXIT_ON_CLOSE);
        studentFrame.setPreferredSize(new Dimension(1000, 500));
        studentFrame.setResizable(false);

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();

    
        cmdAddStudent = new JButton("Add Student");
        cmdEditStudent = new JButton("Edit Student");
        cmdDeleteStudent = new JButton("Delete Student");
        cmdSearchStudent = new JButton("Search Student");
        cmdCheckInVisitor = new JButton("Check-In Visitor");
        cmdCheckOutVisitor = new JButton("Check-Out Visitor");
        cmdSearchVisitor = new JButton("Search Visitor");
        cmdClose = new JButton("Close");

        cmdAddStudent.addActionListener(new AddStudentButtonListener());
        cmdEditStudent.addActionListener(new EditStudentButtonListener());
        cmdDeleteStudent.addActionListener(new DeleteStudentButtonListener());
        cmdSearchStudent.addActionListener(new SearchStudentButtonListener());
        cmdCheckInVisitor.addActionListener(new CheckInButtonListener());
        cmdCheckOutVisitor.addActionListener(new CheckOutButtonListener());
        cmdSearchVisitor.addActionListener(new SearchVisitorButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());



        pnlCommand.add(cmdAddStudent);
        pnlCommand.add(cmdEditStudent);
        pnlCommand.add(cmdDeleteStudent);
        pnlCommand.add(cmdCheckInVisitor);
        pnlCommand.add(cmdCheckOutVisitor);
        pnlCommand.add(cmdSearchVisitor);
        pnlCommand.add(cmdClose);


        pnlDisplay.add(new JLabel("Search:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);   
        pnlDisplay.add(cmdSearchStudent);
        
        studentFrame.add(pnlCommand, BorderLayout.NORTH);
        studentFrame.add(pnlDisplay, BorderLayout.CENTER);

        studentFrame.pack();
        studentFrame.setLocationRelativeTo(null);

        new Login();
        studentFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new StudentMain();
    }

    private class AddStudentButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new StudentEntry();
        }
    }

    private class EditStudentButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new StudentEdit();
        }
    }

    private class DeleteStudentButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new StudentDelete();
        }
    }

    private class SearchStudentButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String ID = IDnumber.getText();

            ArrayList<Student> sList = new ArrayList<Student>();
            int found = 0;


            if(ID.split(" ").length != 1) {
                JOptionPane.showMessageDialog(null, "Invalid entry");
                return;
            }

            if(ID.replace(" ","").isEmpty()){
                JOptionPane.showMessageDialog(null, "Search field is empty");
                return;
            }

            try {
                Scanner scanner = new Scanner(new File("student.txt"));

                while (scanner.hasNext()) {
                    String[] line = scanner.nextLine().split(":");
                    Student s = new Student(line[0], line[1], line[2], line[3]);

                    if (s.getID().equals(ID.toUpperCase())){
                        JOptionPane.showMessageDialog(null, "Residency Confirmed\nSuccessful Search");
                        found = 1;
                        break;
                    }

                    sList.add(s);
                }

                if(found == 0){
                    JOptionPane.showMessageDialog(null, "Deny Entry\nUnseccessful Search");
                }

                scanner.close();

            } catch (Exception p) {
                JOptionPane.showMessageDialog(null, "Error accurred while searching\nUnseccessful Search");
                // TODO: handle exception
            }

        }
    }

    private class CheckInButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new VisitorCheckIn();
        }
    }

    private class CheckOutButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new VisitorCheckOut();
        }
    }

    private class SearchVisitorButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new VisitorSearch();
        }
    }

    private static class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

}