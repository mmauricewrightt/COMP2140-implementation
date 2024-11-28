import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;
import java.util.Scanner;

public class VisitorSearch extends JFrame{
    protected JTextField IDnumber;
    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdSearch;
    protected JButton cmdCancel;
    protected JFrame frame = new JFrame();
    
    public VisitorSearch(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();


        pnlDisplay.add(new JLabel("Visitor ID:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);

        cmdSearch     = new JButton("Search");
        cmdCancel     = new JButton("Cancel");

        cmdSearch.setBackground(Color.green);
        cmdCancel.setBackground(Color.red);

        cmdSearch.addActionListener(new SearchButtonListener());
        cmdCancel.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdSearch);
        pnlCommand.add(cmdCancel);

        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private class SearchButtonListener implements ActionListener{
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

            if(found == 0){
                JOptionPane.showMessageDialog(null, "ID was not checked in");
            }
            else{
                JOptionPane.showMessageDialog(null, "Grant Access\nSearch was Successful");
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
