import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentEntry{
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField IDnumber;
    protected JTextField roomNumber;
    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdSave;
    protected JButton cmdClose;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();


    public StudentEntry(){
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();

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
       
        cmdSave      = new JButton("Save");
        cmdClose     = new JButton("Close");

        cmdSave.setBackground(Color.green);
        cmdClose.setBackground(Color.red);

        cmdSave.addActionListener(new SaveButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class SaveButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String fName = firstName.getText();
            String lName = lastName.getText();
            String ID = IDnumber.getText();
            String rmNum = roomNumber.getText();

            if(fName.split(" ").length == 1 && lName.split(" ").length == 1 && ID.split(" ").length == 1 && rmNum.split(" ").length == 1) {
                try {
                    if(fName.replace(" ","").isEmpty() || lName.replace(" ","").isEmpty() || ID.replace(" ","").isEmpty() || rmNum.replace(" ","").isEmpty()){
                        JOptionPane.showMessageDialog(null, "One or more fields are empty");
                        return;
                    }

                    // Student student = new Student(fName, lName, ID, rmNum);
                    int found = 0;
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
                

                    
                    try {
                        FileWriter fwrite = new FileWriter("student.txt", true);

                        fwrite.write(fName.toUpperCase() + ":" + lName.toUpperCase() + ":" + ID.toUpperCase() + ":" + rmNum.toUpperCase() + "\n");

                        fwrite.close();

                        JOptionPane.showMessageDialog(null, "Student's information was added successfully");


                    } catch (Exception p) {
                        // TODO: handle exception
                    }

                    frame.dispose();
                    
                

                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Unseccessful Entry");
                    frame.dispose();
                    // TODO: handle exception
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Unseccessful entry");
                return;
            }

        }
    }

    private class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
        }
    }
    
}
