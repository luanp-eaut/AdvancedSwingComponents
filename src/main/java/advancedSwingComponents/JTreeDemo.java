package advancedSwingComponents;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class JTreeDemo {
    public static void main(String[] args) {
        // Tạo root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Tạo child nodes
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");

        // Thêm child nodes vào root
        root.add(child1);
        root.add(child2);

        // Thêm sub-child nodes
        child1.add(new DefaultMutableTreeNode("Sub-child 1.1"));
        child1.add(new DefaultMutableTreeNode("Sub-child 1.2"));
        child2.add(new DefaultMutableTreeNode("Sub-child 2.1"));

        // Tạo TreeModel tùy chỉnh
        TreeModel treeModel = new DefaultTreeModel(root);

        // Tạo JTree với TreeModel
        JTree tree = new JTree(treeModel);

        // Enable editing
        tree.setEditable(true);

        // Add the tree to a scroll pane
        JScrollPane scrollPane = new JScrollPane(tree);

        // Create the JFrame
        JFrame frame = new JFrame("JTree Demo with TreeModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add the scroll pane to the frame
        frame.add(scrollPane);

        // Display the frame
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
