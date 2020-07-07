public class Inventory {

	private String Name;
	private String ID;
	private String Price;
	private String Quantity;
	private String Category;

	public Inventory(String Name, String ID, String Price, String Quantity, String Category) {
		// super();
		this.Name = Name;
		this.Quantity = Quantity;
		this.Price = Price;
		this.ID = ID;
		this.Category = Category;
	}

	public String getInventoryInfo(){
		return this.getName() +"\t" + this.getID() + "\t"  + this.getPrice() + "\t" + this.getQuantity() + "\t" + this.getCategory(); 
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		Quantity = quantity;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

}
