package advancedSwingComponents;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
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

		// Set default selection mode
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Add TreeSelectionListener
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath[] selectedPaths = tree.getSelectionPaths();
				if (selectedPaths != null) {
					System.out.println("Selected Nodes:");
					for (TreePath path : selectedPaths) {
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
						System.out.println(node.getUserObject());
					}
				} else {
					System.out.println("No nodes selected.");
				}
			}
		});

		// Create JFrame
		JFrame frame = new JFrame("JTree Selection Model Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);

		// Create JPanel for selection mode options
		JPanel selectionModePanel = new JPanel();
		selectionModePanel.setLayout(new FlowLayout());

		// Create radio buttons for selection modes
		JRadioButton singleSelectionButton = new JRadioButton("Single Selection");
		singleSelectionButton.setSelected(true);
		JRadioButton contiguousSelectionButton = new JRadioButton("Contiguous Selection");
		JRadioButton discontiguousSelectionButton = new JRadioButton("Discontiguous Selection");

		// Add radio buttons to ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(singleSelectionButton);
		buttonGroup.add(contiguousSelectionButton);
		buttonGroup.add(discontiguousSelectionButton);

		// Add action listeners to radio buttons
		singleSelectionButton.addActionListener(
				e -> tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION));
		contiguousSelectionButton.addActionListener(
				e -> tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION));
		discontiguousSelectionButton.addActionListener(
				e -> tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION));

		// Add radio buttons to panel
		selectionModePanel.add(singleSelectionButton);
		selectionModePanel.add(contiguousSelectionButton);
		selectionModePanel.add(discontiguousSelectionButton);

		// Add components to frame
		frame.setLayout(new BorderLayout());
		frame.add(new JScrollPane(tree), BorderLayout.CENTER);
		frame.add(selectionModePanel, BorderLayout.SOUTH);

		// Display JFrame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
