package Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
//import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import org.javatuples.Pair;

import com.formdev.flatlaf.FlatDarculaLaf;

import tablou.VarMap;
import tablou.parser.FailedToParseException;
import tablou.parser.TParser;
import tablou.solver.Value;

public class GuiBuilder {

    public static void buildWindow() {

        // creating the frame
        JFrame frame = new JFrame("Windows 98");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setUndecorated(true);
        // frame.setShape(new RoundRectangle2D.Double(10, 10, 550, 750, 50, 50));
        frame.setSize(600, 800);
        frame.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // creating panel and components
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter formula");
        JTextField tf = new JTextField(20);
        JButton result = new JButton("View solutions");
        result.setPreferredSize(new Dimension(200, 30));
        tf.setPreferredSize(new Dimension(70, 30));
        panel.add(label);
        panel.add(tf);
        panel.add(result);

        tf.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    result.doClick();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        ta.setLineWrap(true);

        result.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Pair<Value, VarMap> output = TParser.start_parse(tf.getText());
                    Value root = output.getValue0();
                    VarMap variables = output.getValue1();

                    ta.setText(root.printf(0));

                    ArrayList<VarMap> solutions = root.solve(variables, true);

                    for (VarMap solution : solutions) {
                        String solution_str = "";
                        for (String key : solution.keySet()) {
                            solution_str += key + ": " + solution.get(key).value() + "\n";
                        }
                        ta.append("\n_____________________" + solution_str);
                    }

                } catch (FailedToParseException err) {
                    ta.setText(err.getMessage());
                } catch (UnsupportedOperationException err) {
                    ta.append(err.getMessage());
                }

            }

        });

        // Adding components to frame
        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);
    }
}
