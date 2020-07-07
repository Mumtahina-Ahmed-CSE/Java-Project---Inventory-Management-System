import java.io.BufferedReader;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class InventoryFileOperation {

	private Formatter inventoryFileFormatter;
	private Scanner inventoryFileScanner;
	private FileWriter inventoryFileWriter;

	private String fileName;
	private final static String myFile = "Inventory.txt";

	public InventoryFileOperation(String fileName) {
		this.fileName = fileName;
	}

	public void openFormatter(boolean append) {

		try {
 
			inventoryFileWriter = new FileWriter(fileName, append);
			inventoryFileFormatter = new Formatter(inventoryFileWriter);
			inventoryFileScanner = new Scanner(new File(fileName));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "File Scanner Openning Fialed");
		}

	}

	public void addInventory(Inventory inventory) {

		openFormatter(true);
		inventoryFileFormatter.format("%s %d %d %d %s\n", inventory.getName(), inventory.getQuantity(),
				inventory.getPrice(), inventory.getID(), inventory.getCategory());
		inventoryFileFormatter.close();

	}

	public ArrayList<Inventory> getInventories() {

		openFormatter(true);
		ArrayList<Inventory> inventories = new ArrayList<>();

		while (inventoryFileScanner.hasNext()) {

			String Name = inventoryFileScanner.next();
			String Quantity = inventoryFileScanner.next();
			String Price = inventoryFileScanner.next();
			String ID = inventoryFileScanner.next();
			String Category = inventoryFileScanner.next();

			inventories.add(new Inventory(Name, Quantity, Price, ID, Category));
		}
		
		return inventories;
	}

	public void writeInventories(ArrayList<Inventory> allInventories) {

		openFormatter(false);

		for (Inventory inventory : allInventories) {
			inventoryFileFormatter.format("%s %d %d %d %s \n", inventory.getName(), inventory.getQuantity(),
					inventory.getPrice(), inventory.getID(), inventory.getCategory());

		}
		inventoryFileFormatter.close();
	}

}
