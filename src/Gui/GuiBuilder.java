package Gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;



public class GuiBuilder {
    public static void buildWindow() {
       
        // creating the frame
        JFrame frame = new JFrame("Windows 98");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        


        //creating panel and components
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter formula");
        JTextField tf = new JTextField(20);
        JButton result = new JButton("View solutions");
        panel.add(label);
        panel.add(tf);
        panel.add(result);


        tf.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    result.doClick();
                }
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                
            } 
            
        });



        JTextArea ta = new JTextArea();
        ta.setEditable(false);

        result.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ta.append(tf.getText());
            }

        });

        // Adding components to frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
