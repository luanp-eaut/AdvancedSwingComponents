package advancedSwingComponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JTableDemo {
	public static void main(String[] args) {
		// Dữ liệu sinh viên
		String[][] studentData = { { "1", "Nguyễn Văn An", "20" }, { "2", "Trần Thị Bình", "21" },
				{ "3", "Lê Quang Thiện", "22" } };

		// Tên cột
		String[] columnNames = { "ID", "Tên", "Tuổi" };

		// Tạo DefaultTableModel với dữ liệu và tên cột
		DefaultTableModel model = new DefaultTableModel(studentData, columnNames);

		// Tạo JTable với DefaultTableModel
		JTable table = new JTable(model);

		// Cho phép chọn từng ô (cell)
		table.setCellSelectionEnabled(true);

		// Tạo custom renderer cho cột "Tuổi"
		DefaultTableCellRenderer ageRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value != null) {
					int age = Integer.parseInt(value.toString());
					
					if (age > 21) {
						cell.setFont(cell.getFont().deriveFont(Font.BOLD)); // Hiển thị chữ đậm
						cell.setForeground(Color.RED); // Màu chữ đỏ
						cell.setBackground(Color.YELLOW); // Màu nền vàng
					} else {
						cell.setFont(cell.getFont().deriveFont(Font.PLAIN));
						cell.setForeground(Color.BLACK);
					}
					if (isSelected) {
						cell.setBackground(table.getSelectionBackground()); // Use default selection background
					} else if (age > 21) {
						cell.setBackground(Color.YELLOW); // Yellow background for age > 21
					} else {
						cell.setBackground(Color.WHITE); // Default background
					}
				}

				return cell;
			}
		};

		// Áp dụng renderer cho cột "Tuổi"
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(2).setCellRenderer(ageRenderer); // Cột "Tuổi" là cột thứ 3 (index 2)

		// Tạo custom editor cho cột "Tuổi"
		JTextField textField = new JTextField();
		DefaultCellEditor ageEditor = new DefaultCellEditor(textField) {
			@Override
			public boolean stopCellEditing() {
				String value = (String) getCellEditorValue();
				try {
					int age = Integer.parseInt(value);
					if (age < 0) {
						throw new NumberFormatException("Invalid age");
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Tuổi không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				return super.stopCellEditing();
			}
		};

		// Áp dụng editor cho cột "Tuổi"
		columnModel.getColumn(2).setCellEditor(ageEditor); // Cột "Tuổi" là cột thứ 3 (index 2)

		// Căn giữa nội dung cho cột "ID" và "Tuổi"
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		ageRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // Cột "ID"

		// Thay đổi kích thước cột
		int tableWidth = 400; // Tổng chiều rộng bảng
		table.getColumnModel().getColumn(0).setPreferredWidth((int) (tableWidth * 0.2)); // Cột "ID" 20%
		table.getColumnModel().getColumn(1).setPreferredWidth((int) (tableWidth * 0.6)); // Cột "Tên" 60%
		table.getColumnModel().getColumn(2).setPreferredWidth((int) (tableWidth * 0.2)); // Cột "Tuổi" 20%

		// Tạo JLabel để hiển thị hàng và cột được chọn
		JLabel statusBar = new JLabel("Selected Cell: ");
		statusBar.setFont(new Font("Arial", Font.PLAIN, 14));

		// Lắng nghe sự kiện thay đổi lựa chọn hàng
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateStatusBar(table, statusBar);
			}
		});

		// Lắng nghe sự kiện thay đổi lựa chọn cột
		table.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateStatusBar(table, statusBar);
			}
		});

		// Tạo JScrollPane cho phép cuộn bảng
		JScrollPane scrollPane = new JScrollPane(table);

		// Tạo JFrame
		JFrame frame = new JFrame("Danh sách sinh viên");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(statusBar, BorderLayout.SOUTH); // Thêm status bar vào vị trí SOUTH

		// Hiển thị JFrame
		frame.setLocationRelativeTo(null); // Center the frame on the screen
		frame.setVisible(true);
	}

	// Phương thức cập nhật status bar
	private static void updateStatusBar(JTable table, JLabel statusBar) {
		int selectedRow = table.getSelectedRow();
		int selectedColumn = table.getSelectedColumn();
		if (selectedRow != -1 && selectedColumn != -1) {
			statusBar.setText("Selected Cell: Row " + selectedRow + ", Column " + selectedColumn);
		} else {
			statusBar.setText("Selected Cell: None");
		}
	}
}
