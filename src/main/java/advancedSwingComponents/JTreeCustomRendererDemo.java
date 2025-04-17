package advancedSwingComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class JTreeCustomRendererDemo {
    public static void main(String[] args) {
        // Create root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Create child nodes
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
        DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Special Node");

        // Add child nodes to root
        root.add(child1);
        root.add(child2);
        root.add(child3);

        // Create TreeModel
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        // Create JTree with TreeModel
        JTree tree = new JTree(treeModel);

        // Set custom TreeCellRenderer
        tree.setCellRenderer(new CustomTreeCellRenderer());

        // Create JFrame
        JFrame frame = new JFrame("JTree Custom Renderer Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Add JTree to JScrollPane
        frame.add(new JScrollPane(tree));

        // Display JFrame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Custom TreeCellRenderer
    static class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {
            // Use default rendering
            Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            // Customize appearance
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            String nodeName = node.getUserObject().toString();

            if ("Child 1".equals(nodeName)) {
                component.setForeground(Color.BLUE);
            } else if ("Special Node".equals(nodeName)) {
                component.setFont(component.getFont().deriveFont(Font.BOLD));
                component.setForeground(Color.RED);
            }

            return component;
        }
    }
}