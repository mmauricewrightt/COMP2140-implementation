import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class VisitorCheckIn{
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField IDnumber;
    protected JTextField residentID;
    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdSave;
    protected JButton cmdClose;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();


    public VisitorCheckIn(){
        // frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

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

        pnlDisplay.add(new JLabel("Resident ID:")); 
        residentID = new JTextField(15);
        pnlDisplay.add(residentID);   
       
        cmdSave      = new JButton("Check In");
        cmdClose     = new JButton("Cancel");

        cmdSave.setBackground(Color.green);
        cmdClose.setBackground(Color.red);

        cmdSave.addActionListener(new CheckInButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class CheckInButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String fName = firstName.getText();
            String lName = lastName.getText();
            String ID = IDnumber.getText();
            String resID = residentID.getText();


            int found = 0;
            try {
                Scanner scanner = new Scanner(new File("student.txt"));

                while (scanner.hasNext()) {
                    String[] line = scanner.nextLine().split(":");
                    Student s = new Student(line[0], line[1], line[2], line[3]);

                    if (s.getID().equals(resID.toUpperCase())){
                        found = 1;
                        break;
                    }
                }

                scanner.close();

            } catch (Exception p) {
                JOptionPane.showMessageDialog(null, "Error accurred while searching\nUnseccessful Search");
                // TODO: handle exception
            }



            if(fName.split(" ").length == 1 && lName.split(" ").length == 1 && ID.split(" ").length == 1 && resID.split(" ").length == 1 && found == 1){
                try {
                    if(fName.replace(" ","").isEmpty() || lName.replace(" ","").isEmpty() || ID.replace(" ","").isEmpty()){
                        JOptionPane.showMessageDialog(null, "One or more fields are empty");
                        return;
                    }
               
                    FileWriter fwrite = new FileWriter("visitor.txt", true);

                    fwrite.write(fName.toUpperCase() + ":" + lName.toUpperCase() + ":" + ID.toUpperCase() + ":" + resID.toUpperCase() + "\n");



                    fwrite.close();

                    JOptionPane.showMessageDialog(null, "Successful Check in");

                    frame.dispose();
                

                } catch (Exception p) {
                    JOptionPane.showMessageDialog(null, "Unseccessful Entry");
                    // TODO: handle exception
                }
            }
            else{
                if(found == 0){
                    JOptionPane.showMessageDialog(null, "Resident not found in residency\nCan't check in");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Unseccessful check in");
                }
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
