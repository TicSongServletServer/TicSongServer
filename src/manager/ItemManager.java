package manager;

import java.util.List;

import DAO.ItemDAO;
import DTO.ItemDTO;

public class ItemManager {
	
	private static final ItemManager instance = new ItemManager();
	private static ItemDAO itemDAO;
	
	private ItemManager() {
		itemDAO = ItemDAO.getInstance();
	}
	
	public static ItemManager getInstance() {
		return instance;
	}
	
	 public int insertItem(ItemDTO item) {
		 return itemDAO.insertItem(item);
	 }
	 
	 public int updateItem(ItemDTO item) {
		 return itemDAO.updateItem(item);
	 }
	
	 public ItemDTO getItem(String userId) {
		 return itemDAO.getItem(userId);
	 }

}
