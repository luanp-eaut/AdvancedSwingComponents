package advancedSwingComponents;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class JTreeSelectionModelDemo {
    public static void main(String[] args) {
        // Create root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Create child nodes
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
        DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Child 3");

        // Add child nodes to root
        root.add(child1);
        root.add(child2);
        root.add(child3);

        // Create TreeModel
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        // Create JTree with TreeModel
        JTree tree = new JTree(treeModel);

        // Set selection mode
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Add TreeSelectionListener
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    System.out.println("Selected Node: " + selectedNode.getUserObject());
                }
            }
        });

        // Create JFrame
        JFrame frame = new JFrame("JTree Selection Model Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add JTree to JScrollPane
        frame.add(new JScrollPane(tree));

        // Display JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
