import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

  public class OverView extends JPanel {
	private DefaultTableModel inventoryTableModel;

	private FileOperations inventoryFileOperation;

	private ArrayList<Inventory> allInventories = new ArrayList<>();
	private JTable table;
	private HashMap<String, Integer> hmap = new HashMap<>();
	String Category;
	String Quantity;


	 public OverView() {                                                                             
		
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("OverView");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		add(panel_1, BorderLayout.SOUTH);

		JButton btnSearch = new JButton("OverView");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//allInventories = OverView(Category, Quantity);
				try{
					
						hmap = OverView();
						//System.out.println(hmap.keySet()+"OK, Writing...");
						loadTable();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Thanks for using!");
				}

				// list.get(0);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(btnSearch);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		table = new JTable();

		scrollPane.setViewportView(table);

		inventoryFileOperation = new FileOperations();
		allInventories = inventoryFileOperation.getAllInventories();
	}

	JScrollPane scrollPane;

	private void loadTable() {

		String heading[] = {"Category" ,"Total Quantity"};

		inventoryTableModel = new DefaultTableModel(heading, 0);

		for (String str : hmap.keySet()) {

			Object[] inventoryData = {str, String.valueOf(hmap.get(str))};
			inventoryTableModel.addRow(inventoryData);
		}

		table.setModel(inventoryTableModel);

		scrollPane.setViewportView(table);
	}
	private HashMap<String, Integer> OverView() {

		inventoryFileOperation = new FileOperations();
		allInventories = inventoryFileOperation.getAllInventories();
		HashMap<String, Integer> map = new HashMap<>();
		System.out.println(allInventories);
		for(Inventory inv : allInventories){
			int currentCount = 0;
			try{currentCount += map.get(inv.getCategory());}catch(Exception e){
				
			}
			
			//System.out.println(currentCount);
			currentCount += Integer.parseInt(inv.getQuantity());
			map.put(inv.getCategory(), currentCount);
		}
		return map;
		
	}

}