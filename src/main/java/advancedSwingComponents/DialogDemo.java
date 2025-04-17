package advancedSwingComponents;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DialogDemo {
    public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Dialog Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        
        
        
        
     // Create JComboBox for dialog selection
        String[] dialogOptions = {"Message", "Confirmation", "Input"};
        JComboBox<String> dialogComboBox = new JComboBox<>(dialogOptions);

        // Add ActionListener to JComboBox
        dialogComboBox.addActionListener(e -> {
            String selectedOption = (String) dialogComboBox.getSelectedItem();
            if ("Message".equals(selectedOption)) {
                JOptionPane.showMessageDialog(frame, "This is a message dialog.", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else if ("Confirmation".equals(selectedOption)) {
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                System.out.println("Confirmation result: " + result);
            } else if ("Input".equals(selectedOption)) {
                String input = JOptionPane.showInputDialog(frame, "Enter your name:");
                System.out.println("Input: " + input);
            }
        });

        // JButton for JDialog
        JButton customDialogButton = new JButton("Show Custom JDialog");
        customDialogButton.addActionListener(e -> {
            JDialog dialog = new JDialog(frame, "Custom Dialog", true);
            dialog.setSize(200, 150);
            dialog.setLayout(new FlowLayout());
            dialog.add(new JLabel("This is a custom dialog."));
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(event -> dialog.dispose());
            dialog.add(closeButton);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });

        // JButton for JFileChooser
        JButton fileChooserButton = new JButton("Show JFileChooser");
        fileChooserButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected file: " + fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        // JButton for JColorChooser
        JButton colorChooserButton = new JButton("Show JColorChooser");
        colorChooserButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(frame, "Choose a Color", Color.WHITE);
            if (selectedColor != null) {
                System.out.println("Selected color: " + selectedColor);
            }
        });

        // Add buttons to frame
        frame.add(dialogComboBox);
//        frame.add(optionPaneButton);
        frame.add(customDialogButton);
        frame.add(fileChooserButton);
        frame.add(colorChooserButton);

        // Display JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
