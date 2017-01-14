package DTO;

public class ScoreRank {
	
	private int rank;
	private String userId;
	private String name;
	private int exp;
	private int userLevel;
	
	public ScoreRank() {}

	public ScoreRank(int rank, String userId, String name, int exp, int userLevel) {
		super();
		this.rank = rank;
		this.userId = userId;
		this.name = name;
		this.exp = exp;
		this.userLevel = userLevel;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	@Override
	public String toString() {
		return "ScoreRank [rank=" + rank + ", userId=" + userId + ", name=" + name + ", exp=" + exp + ", userLevel="
				+ userLevel + "]";
	}
}
