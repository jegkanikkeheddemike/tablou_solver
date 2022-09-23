package Gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class GuiBuilder {
    public static void buildWindow() {
        
        // creating the frame
        JFrame frame = new JFrame("Windows 98");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);

        //creating panel and components
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter formula");
        JTextField tf = new JTextField(10);
        JButton result = new JButton("View solutions");
        panel.add(label);
        panel.add(result);

        // Adding components to frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
    }
}
