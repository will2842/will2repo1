import java.util.*;
public class Inventory {
	
	private class Item_Details{
		int Item_ID;
		String Item_Name;
		int Item_Quantity;
		Item_Details(int Item_ID, String Item_Name, int Item_Quantity){
			this.Item_ID = Item_ID;
			this.Item_Name = Item_Name;
			this.Item_Quantity = Item_Quantity;
		}
	}
	public HashMap <Integer, Item_Details> Main_Inventory = new HashMap<Integer, Item_Details>();
	public HashMap <String, Integer> Converter = new HashMap<String, Integer>();
	
	public int Get_Item_Qty(int ID){
		return Main_Inventory.get(ID).Item_Quantity;
	}
	
	public String Get_Item_Name(int ID){
		return Main_Inventory.get(ID).Item_Name;
	}
	
	public int Get_Inventory_ID(String name){
		return Converter.get(name);
	}
	
}


