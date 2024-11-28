import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.concurrent.CountDownLatch;

public class Login extends JFrame{

    private static CountDownLatch latch;
    protected JTextField IDnumber;

    protected JPanel pnlDisplay;
    protected JPanel pnlCommand;
    protected JButton cmdEnter;
    protected JButton cmdClose;
    protected StudentMain studentMain;
    protected JFrame frame = new JFrame();
    
    public Login(){


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnlDisplay = new JPanel();
        pnlCommand = new JPanel();


        pnlDisplay.add(new JLabel("Password:")); 
        IDnumber = new JTextField(15);
        pnlDisplay.add(IDnumber);

        cmdEnter    = new JButton("Enter");
        cmdClose     = new JButton("Close");

        cmdEnter.setBackground(Color.green);

        cmdEnter.addActionListener(new EnterButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());

        pnlCommand.add(cmdEnter);
        pnlCommand.add(cmdClose);

        frame.add(pnlDisplay, BorderLayout.CENTER);
        frame.add(pnlCommand, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class EnterButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String password = IDnumber.getText();
            
            if(password.replace(" ","").isEmpty()){
                JOptionPane.showMessageDialog(null, "Password field is empty");
                return;
            }
            
            if(!(password.equals("THallSecurity123"))){
                JOptionPane.showMessageDialog(null, "Wrong Passwrod");
                return;
            }
            frame.dispose();
            new StudentMain();
            

            
        }
    }

    private class CloseButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
        }
    }
    
}

