package advancedSwingComponents;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class MenuAndToolBarDemo {
	public static void main(String[] args) {
        // Create JFrame
        JFrame frame = new JFrame("Menu and Toolbar with Actions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create Action for "New"
        Action newAction = new AbstractAction("New") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New File Created");
            }
        };
        newAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N); // Alt+N
        newAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK)); // Ctrl+N

        // Create Action for "Open"
        Action openAction = new AbstractAction("Open") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "File Opened");
            }
        };
        openAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_O); // Alt+O
        openAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK)); // Ctrl+O

        // Create Action for "Exit"
        Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X); // Alt+X
        exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK)); // Ctrl+X

        // Create MenuBar
        JMenuBar menuBar = new JMenuBar();

        // Create "File" menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F); // Alt+F
        fileMenu.add(new JMenuItem(newAction));
        fileMenu.add(new JMenuItem(openAction));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem(exitAction));

        // Add MenuBar to JFrame
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // Create Toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton(newAction));
        toolBar.add(new JButton(openAction));
        toolBar.addSeparator();
        toolBar.add(new JButton(exitAction));

        // Add Toolbar to JFrame
        frame.add(toolBar, BorderLayout.NORTH);

        // Display JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

