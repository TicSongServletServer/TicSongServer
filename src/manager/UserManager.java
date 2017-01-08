package manager;

import DAO.UserDAO;
import DTO.LoginView;
import DTO.UserDTO;

public class UserManager {

	private static final UserManager instance = new UserManager();
	private static UserDAO userDao;
	
	private UserManager() {
		userDao = UserDAO.getInstance();
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public UserDTO login(String userId) {
		return userDao.login(userId);
	}
	
	public UserDTO login(String userId, String name) {
		return userDao.login(userId, name);
	}
	
	public LoginView login(String userId, String name, int platform) {
		return userDao.login(userId, name, platform);
	}
	
	public int insertUser(String userId, String name) {
		return userDao.insertUser(userId, name);
	}
	
	public int insertUser(String userId, String name, int platform) {
		return userDao.insertUser(userId, name, platform);
	}
	
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}
	
}
