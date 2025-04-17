package advancedSwingComponents;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class JTreeAdvancedDemo {
	public static void main(String[] args) {
		// Tạo root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

		// Tạo child nodes
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");

		// Thêm child nodes vào root
		root.add(child1);
		root.add(child2);

		// Tạo TreeModel
		DefaultTreeModel treeModel = new DefaultTreeModel(root);

		// Tạo JTree với TreeModel
		JTree tree = new JTree(treeModel);
		tree.setEditable(true);

		// Tạo các nút điều khiển
		JButton addButton = new JButton("Add Node");
		JButton removeButton = new JButton("Remove Node");
		JButton updateButton = new JButton("Update Node");

		// Panel chứa các nút
		JPanel controlPanel = new JPanel();
		controlPanel.add(addButton);
		controlPanel.add(removeButton);
		controlPanel.add(updateButton);

		// Xử lý sự kiện thêm node
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TreePath selectedPath = tree.getSelectionPath();
				if (selectedPath != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New Node");
					treeModel.insertNodeInto(newNode, selectedNode, selectedNode.getChildCount());
					tree.scrollPathToVisible(new TreePath(newNode.getPath()));
				} else {
					JOptionPane.showMessageDialog(null, "Please select a node to add a child.");
				}
			}
		});

		// Xử lý sự kiện xóa node
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TreePath selectedPath = tree.getSelectionPath();
				if (selectedPath != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
					if (selectedNode.getParent() != null) {
						treeModel.removeNodeFromParent(selectedNode);
					} else {
						JOptionPane.showMessageDialog(null, "Cannot remove the root node.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a node to remove.");
				}
			}
		});

		// Xử lý sự kiện cập nhật node
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TreePath selectedPath = tree.getSelectionPath();
				if (selectedPath != null) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
					String newName = JOptionPane.showInputDialog("Enter new name for the node:");
					if (newName != null && !newName.trim().isEmpty()) {
						selectedNode.setUserObject(newName);
						treeModel.nodeChanged(selectedNode);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a node to update.");
				}
			}
		});

		// Tạo JFrame
		JFrame frame = new JFrame("Advanced JTree Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);

		// Thêm JTree và control panel vào JFrame
		frame.setLayout(new BorderLayout());
		frame.add(new JScrollPane(tree), BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.SOUTH);

		// Hiển thị JFrame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}