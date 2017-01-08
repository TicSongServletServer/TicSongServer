package manager;

import java.util.List;

import DAO.MyScoreDAO;
import DTO.MyScoreDTO;
import DTO.ScoreView;

public class MyScoreManager {
	
	private static final MyScoreManager instance = new MyScoreManager();
	private static MyScoreDAO myScoreDAO;
	
	private MyScoreManager() {
		myScoreDAO = MyScoreDAO.getInstance();
	}
	
	public static MyScoreManager getInstance() {
		return instance;
	}
	
	public int insertMyScore(MyScoreDTO myScore) {
		return myScoreDAO.insertMyScore(myScore);
	}
	
	public int updateMyScore(MyScoreDTO myScore) {
		return myScoreDAO.updateMyScore(myScore);
	}
	
	public MyScoreDTO getMyScore(String userId) {
		return myScoreDAO.getMyScore(userId);
	}
	
	public List<ScoreView> getScores() {
		return myScoreDAO.getScores();
	}
	
	public List<ScoreView> getFriendScores(List<String> friendList) {
		return myScoreDAO.getFriendScores(friendList);
	}

}
