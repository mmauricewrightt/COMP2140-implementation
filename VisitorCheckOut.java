import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;
import java.util.Scanner;

public class VisitorCheckOut extends JFrame{
    protected JTextField firstName;
    protected JTextField lastName;
    protected JTextField IDnumber;
    protected JTextField roomNumber;
    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdCheckOut;
    protected JButton cmdCancel;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();
    
    public VisitorCheckOut(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();


        pnlDisplay.add(new JLabel("ID Number to be Checked out:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);

        cmdCheckOut    = new JButton("Check out");
        cmdCancel     = new JButton("Cancel");

        cmdCheckOut.setBackground(Color.red);

        cmdCheckOut.addActionListener(new CheckOutButtonListener());
        cmdCancel.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdCheckOut);
        pnlCommand.add(cmdCancel);

        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private class CheckOutButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String ID = IDnumber.getText();

            ArrayList<Visitor> vList = new ArrayList<Visitor>();

            int found = 0;
            try {
                Scanner scanner = new Scanner(new File("visitor.txt"));

                while (scanner.hasNext()) {
                    String[] line = scanner.nextLine().split(":");
                    Visitor v = new Visitor(line[0], line[1], line[2], line[3]);

                    if (v.getID().equals(ID.toUpperCase())){
                        found = 1;
                        continue;
                    }

                    vList.add(v);
                }

                scanner.close();

            } catch (Exception p) {
                // TODO: handle exception
            }

            try {
                FileWriter fwrite = new FileWriter("visitor.txt");

                for(Visitor visitor: vList){
                    fwrite.write(visitor.getfName() + ":" + visitor.getlName() + ":" + visitor.getID() + ":" + visitor.getresID() + "\n");
                }

                fwrite.close();

            } catch (Exception p) {
                // TODO: handle exception
            }

            if(found == 0){
                JOptionPane.showMessageDialog(null, "ID was not checked in");
            }
            else{
                JOptionPane.showMessageDialog(null, "Check out was Successful");
                frame.dispose();
            }
        }
    }

    private class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
        }
    }
}
