import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class SearchPanel extends JPanel {
	private JTextField textFieldName;
	private JTextField textFieldQuantity;
	private JTextField textFieldPrice;
	private JTextField textFieldID;
	private JTextField textFieldCategory;

	private DefaultTableModel inventoryTableModel;

	private FileOperations inventoryFileOperation;

	private ArrayList<Inventory> allInventories = new ArrayList<>();
	private JTable table;

	public SearchPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Searching");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		add(panel_1, BorderLayout.SOUTH);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Name= textFieldName.getText().toString();
				String Price = textFieldPrice.getText().toString();
				String ID= textFieldID.getText().toString();

				textFieldName.setText("");
				textFieldPrice.setText("");
				textFieldID.setText("");

				allInventories = searchinventories(Name, Price, ID);
				loadTable();

				// list.get(0);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(btnSearch);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setAlignmentX(CENTER_ALIGNMENT);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblName.setBounds(62, 57, 95, 23);
		panel_3.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(37, 102, 135, 51);
		panel_3.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblId.setBounds(69, 300, 67, 23);
		panel_3.add(lblId);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(28, 222, 144, 45);
		panel_3.add(textFieldPrice);

		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(28, 340, 144, 45);
		panel_3.add(textFieldID);
		
				JLabel lblPrice = new JLabel("Price");
				lblPrice.setBounds(81, 180, 63, 31);
				panel_3.add(lblPrice);
				lblPrice.setFont(new Font("Tahoma", Font.BOLD, 25));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		table = new JTable();

		scrollPane.setViewportView(table);

		inventoryFileOperation = new FileOperations();
		allInventories = inventoryFileOperation.getAllInventories();

	}

	private void loadTable() {

		String heading[] = { "Name", "Quantity", "Price", "ID", "Category" };

		inventoryTableModel = new DefaultTableModel(heading, 0);

		for (Inventory inventory : allInventories) {

			Object[] inventoryData = { inventory.getName(), inventory.getQuantity(), inventory.getPrice(),
					inventory.getID(), inventory.getCategory() };
			inventoryTableModel.addRow(inventoryData);

		}

		table.setModel(inventoryTableModel);
	}

	private void writeInventoryFile() {

		inventoryFileOperation.addNewInventories(allInventories);

	}

	private ArrayList<Inventory> searchinventories(String Name, String Price, String ID) {

		inventoryFileOperation = new FileOperations();
		allInventories = inventoryFileOperation.getAllInventories();

		ArrayList<Inventory> inv = new ArrayList<>();
		for (int i = 0; i < allInventories.size(); i++) {
			Inventory inventory1 = allInventories.get(i);

			if (inventory1.getName().equals(Name) || (inventory1.getPrice() + "").equals(Price)
					|| (inventory1.getID() + "").equals(ID)) {

				inv.add(inventory1);
				// loadTable();
			}
		}
		return inv;
	}
}
