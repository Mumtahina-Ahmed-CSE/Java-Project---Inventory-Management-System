import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {

	private final static String myFile = "Inventory.txt";

	public void addNewInventories(ArrayList<Inventory> inventoryList) {

		try {
			BufferedWriter bwriter = new BufferedWriter(new FileWriter(myFile));

			for (int i = 0; i < inventoryList.size(); i++) {

				Inventory inventory = inventoryList.get(i);
				bwriter.append(inventory.getInventoryInfo());
				bwriter.newLine();
			}

			bwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNewInventory(Inventory inventory) {

		try {
			BufferedWriter bwriter = new BufferedWriter(new FileWriter(myFile, true));

			bwriter.append(inventory.getInventoryInfo());
			bwriter.newLine();

			bwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Inventory> getAllInventories() {

		ArrayList<Inventory> list = new ArrayList<>();

		try {
			BufferedReader bREader = new BufferedReader(new FileReader(myFile));

			String aline;

			while ((aline = bREader.readLine()) != null) {
				String[] row = aline.split("\t");

				Inventory in = new Inventory(row[0], row[1], row[2], row[3], row[4]);

				list.add(in);
			}

			bREader.close();

		} catch (Exception e) {
			System.out.println("Error Occured! ALL: " + e.toString());
		}
		return list;
	}

	public ArrayList<Inventory> SEarchAnInventory(String ID) {

		ArrayList<Inventory> list = new ArrayList<>();

		try {
			BufferedReader bREader = new BufferedReader(new FileReader(myFile));

			String aline;

			while ((aline = bREader.readLine()) != null) {
				String[] row = aline.split("\t");

				if (ID.equals(row[1])) {
					Inventory in = new Inventory(row[0], row[1], row[2], row[4], row[5]);

					list.add(in);
				}
			}

		} catch (Exception e) {
			System.out.println("Error Occured!");
		}
		return list;
	}

	public void deleteAnInventory(String ID) {
		
	}

}
