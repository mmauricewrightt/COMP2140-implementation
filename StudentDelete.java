import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDelete extends JFrame{
    protected JTextField IDnumber;

    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdDelete;
    protected JButton cmdClose;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();
    
    public StudentDelete(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();


        pnlDisplay.add(new JLabel("ID Number:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);

        cmdDelete    = new JButton("Delete");
        cmdClose     = new JButton("Close");

        cmdDelete.setBackground(Color.green);

        cmdDelete.addActionListener(new DeleteButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdDelete);
        pnlCommand.add(cmdClose);

        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class DeleteButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String delete = IDnumber.getText();


            if(delete.split(" ").length != 1) {
                JOptionPane.showMessageDialog(null, "Invalid entry");
                return;
            }

            if(delete.replace(" ","").isEmpty()){
                JOptionPane.showMessageDialog(null, "Delete field is empty");
                return;
            }
            

            ArrayList<Student> sList = new ArrayList<Student>();

            int found = 0;
            try {
                Scanner scanner = new Scanner(new File("student.txt"));

                while (scanner.hasNext()) {
                    String[] line = scanner.nextLine().split(":");
                    Student s = new Student(line[0], line[1], line[2], line[3]);
                        

                    if (s.getID().equals(delete.toUpperCase())){
                        found = 1;
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
                    JOptionPane.showMessageDialog(null, "ID NUMBER NOT FOUND");    
                }else{
                    JOptionPane.showMessageDialog(null, "Student's information was deleted successfully");
                    frame.dispose();
                }

                


            } catch (Exception p) {
                // TODO: handle exception
            }

            
        }
    }

    private class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
        }
    }
    
}
