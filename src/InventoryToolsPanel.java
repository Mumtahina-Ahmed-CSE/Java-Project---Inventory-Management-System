import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.Color;

public class InventoryToolsPanel extends JPanel {

	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JTextField textFieldQuantity;
	private JTextField textFieldID;
	private JTextField textFieldCategory;

	private DefaultTableModel inventoryTableModel;

	private FileOperations inventoryFileOperation;

	private ArrayList<Inventory> allInventories = new ArrayList<>();
	private JTable table;
	private JTextField textFieldSearch;

	/**
	 * @wbp.nonvisual location=150,-31
	 */

	/**
	 * Create the panel.
	 */
	public InventoryToolsPanel() {

		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		add(panel_1, BorderLayout.SOUTH);
		JButton btnInsertNew = new JButton("InsertNew");
		btnInsertNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String Name = textFieldName.getText().toString();
				String Quantity = textFieldID.getText();
				String Price = textFieldPrice.getText();
				String ID = textFieldQuantity.getText();
				String Category = textFieldCategory.getText().toString();

				textFieldName.setText("");
				textFieldQuantity.setText("");
				textFieldPrice.setText("");
				textFieldID.setText("");
				textFieldCategory.setText("");
				
				ArrayList<Inventory> inv = new ArrayList<>();
				int a = 5;
				for (int i = 0; i < allInventories.size(); i++) {
					Inventory inventory1 = allInventories.get(i);

					if (inventory1.getName().equals(Name) && (inventory1.getCategory() + "").equals(Category))
					{
						JOptionPane.showMessageDialog(null, inventory1.getName() + " Already Exists. Use Insert Button to insert Existing Item !");
						a = 0;
						break;
					}
			
				}
				if(a==5)
				{
					saveInventoryData(Name, Quantity, Price, ID, Category);
				}
			}
			
		});
		
		btnInsertNew.setFont(new Font("Tahoma", Font.BOLD, 15));

		panel_1.add(btnInsertNew);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
						int index = table.getSelectedRow();
						Inventory i = allInventories.get(index);
						allInventories.remove(index);
						int a = (Integer.parseInt(i.getQuantity()) + 1);
						String q = a + "";
						i.setQuantity(q);
						allInventories.add(index, i);
						writeInventoryFile();
						loadTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Use InsertNew Button to Insert New Item!");
			}
						
				
;
			}
		});
		panel_1.add(btnInsert);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int index = table.getSelectedRow();
				Inventory i = allInventories.get(index);
				allInventories.remove(index);
				int a = (Integer.parseInt(i.getQuantity()) - 1);
				
				if(a>0 || a==0)
				{
					String q = a + "";
					i.setQuantity(q);
					allInventories.add(index, i);
					writeInventoryFile();
					loadTable();
					
				}
				else if(a<0)
				{
					JOptionPane.showMessageDialog(null, i.getName() + " Does not exits !");
					int index1 = table.getSelectedRow();
					Inventory i1 = allInventories.get(index1);
					allInventories.remove(index1);
					
				}

				if (a < 5)
					JOptionPane.showMessageDialog(null, i.getName() + " Low Quantity!");
			}
		});
		panel_1.add(btnRemove);



		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel(" Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 11, 143, 29);
		panel_3.add(lblNewLabel_1);

		textFieldName = new JTextField();
		textFieldName.setBounds(22, 51, 161, 35);
		panel_3.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(22, 97, 89, 29);
		panel_3.add(lblNewLabel_2);

		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(22, 137, 161, 35);
		panel_3.add(textFieldPrice);

		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(208, 51, 161, 35);
		panel_3.add(textFieldQuantity);

		JLabel lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(208, 13, 117, 25);
		panel_3.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(" ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(218, 97, 133, 29);
		panel_3.add(lblNewLabel_4);

		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(208, 137, 161, 35);
		panel_3.add(textFieldID);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCategory.setBounds(22, 183, 136, 25);
		panel_3.add(lblCategory);

		textFieldCategory = new JTextField();
		textFieldCategory.setColumns(10);
		textFieldCategory.setBounds(22, 219, 161, 35);
		panel_3.add(textFieldCategory);


		JLabel lblNewLabel = new JLabel("Inventory Tools");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(lblNewLabel);

		scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		table = new JTable();

		scrollPane.setViewportView(table);
		inventoryFileOperation = new FileOperations();

	
		allInventories = inventoryFileOperation.getAllInventories();
		loadTable();
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

		scrollPane.setViewportView(table);
	}

	JScrollPane scrollPane;

	private void writeInventoryFile() {
		// inventoryFileOperation.writeInventories(allInventories);

		inventoryFileOperation.addNewInventories(allInventories);

	}

	private void saveInventoryData(String Name, String quantity, String price, String iD, String Category) {

		Inventory inventory = new Inventory(Name, quantity, price, iD, Category);
		allInventories.add(inventory);
		inventoryFileOperation.addNewInventory(inventory);
		allInventories = inventoryFileOperation.getAllInventories();

		loadTable();
	}

	/*private ArrayList<Inventory> searchinventories(String Name, String Price, String ID) {

		ArrayList<Inventory> inv = new ArrayList<>();
		for (Inventory inventory1 : allInventories) {
			if (inventory1.getName().equals(Name) || inventory1.getPrice() == Price || inventory1.getID() == ID) {
				inv.add(inventory1);

			}
		}
		return inv;
	}*/
}
