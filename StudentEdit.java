import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentEdit {
    protected JTextField eID;
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField IDnumber;
    protected JTextField roomNumber;
    protected JPanel pnlPrompt;
    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdEdit;
    protected JButton cmdClose;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();


    public StudentEdit(){
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
 
        pnlPrompt = new JPanel();
        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();

        pnlPrompt.add(new JLabel("ID number to be edited:"));
        eID = new JTextField(15);
        pnlPrompt.add(eID);

        cmdEdit      = new JButton("Edit");
        pnlPrompt.add(cmdEdit);



        pnlDisplay.add(new JLabel("First Name:")); 
        firstName = new JTextField(15);
        pnlDisplay.add(firstName);

        pnlDisplay.add(new JLabel("Last Name:")); 
        lastName = new JTextField(15);
        pnlDisplay.add(lastName);

        pnlDisplay.add(new JLabel("ID Number:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);

        pnlDisplay.add(new JLabel("Room Number:")); 
        roomNumber = new JTextField(15);
        pnlDisplay.add(roomNumber);   
       
        cmdEdit      = new JButton("Edit");
        cmdClose     = new JButton("Close");

        cmdEdit.setBackground(Color.green);
        cmdClose.setBackground(Color.red);

        cmdEdit.addActionListener(new EditButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());


        pnlCommand.add(cmdEdit);
        pnlCommand.add(cmdClose);
        frame.add(pnlPrompt, BorderLayout.NORTH);
        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class EditButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String edit = eID.getText();
            String fName = firstName.getText();
            String lName = lastName.getText();
            String ID = IDnumber.getText();
            String rmNum = roomNumber.getText();

            

            if(!(fName.split(" ").length == 1 && lName.split(" ").length == 1 && ID.split(" ").length == 1 && rmNum.split(" ").length == 1)){
                JOptionPane.showMessageDialog(null, "Please recheck entry\nNo spcae allowed");
            }else{

                if(fName.replace(" ","").isEmpty() || lName.replace(" ","").isEmpty() || ID.replace(" ","").isEmpty() || rmNum.replace(" ","").isEmpty()){
                    JOptionPane.showMessageDialog(null, "One or more fields are empty");
                    return;
                }


                try {
                    Scanner scanner = new Scanner(new File("student.txt"));

                    while (scanner.hasNext()) {
                        String[] line = scanner.nextLine().split(":");
                        Student s = new Student(line[0], line[1], line[2], line[3]);
                        

                        if (s.getID().equals(ID.toUpperCase())){
                            JOptionPane.showMessageDialog(null, "This ID number has already been added");
                            return;
                        }
                    }

                    scanner.close();

                } catch (Exception p) {
                    // TODO: handle exception
                }

                Student student = new Student(fName, lName, ID, rmNum);

                ArrayList<Student> sList = new ArrayList<Student>();

                int found = 0;
                try {
                    Scanner scanner = new Scanner(new File("student.txt"));

                    while (scanner.hasNext()) {
                        String[] line = scanner.nextLine().split(":");
                        Student s = new Student(line[0], line[1], line[2], line[3]);
                        

                        if (s.getID().equals(edit.toUpperCase())){
                            found = 1;
                            sList.add(student);
                            continue;
                        }
                        sList.add(s);
                    }

                    scanner.close();

                } catch (Exception p) {
                    // TODO: handle exception
                }
                
                try {
                    FileWriter fwrite = new FileWriter("student.txt");

                    for(Student s: sList){
                        fwrite.write(s.getfName().toUpperCase() + ":" + s.getlName().toUpperCase() + ":" + s.getID().toUpperCase() + ":" + s.getRmNum().toUpperCase() + "\n");
                    }

                    fwrite.close();

                    if(found == 0){
                        JOptionPane.showMessageDialog(null, "ID NOT FOUND\nNO EDIT MADE!");    
                    }else{
                        JOptionPane.showMessageDialog(null, "Student's information was edited successfully");
                        frame.dispose();
                    }

                } catch (Exception p) {
                    // TODO: handle exception
                }

                
            }

        }
    }

    private class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
        }
    }
}
