package manager;

import java.util.List;

import DAO.MyScoreDAO;
import DTO.MyScoreDTO;
import DTO.ScoreRank;
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
	
	public int insertMyScore(String userId) {
		return myScoreDAO.insertMyScore(userId);
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
	
	public int getMyRank(String userId) {
		return myScoreDAO.getMyRank(userId);
	}
	
	public List<ScoreRank> getRankers() {
		return myScoreDAO.getRankers();
	}

}
