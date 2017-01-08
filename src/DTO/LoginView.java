package DTO;

import java.io.Serializable;

public class LoginView implements Serializable{
	
	private String userId;
	private String name;
	private int platform;
	private int exp;
	private int userLevel;
	private int item1Cnt;
	private int item2Cnt;
	private int item3Cnt;
	private int item4Cnt;
	
	public LoginView() {
		// TODO Auto-generated constructor stub
	}

	public LoginView(String userId, String name, int platform, int exp,
			int userLevel, int item1Cnt, int item2Cnt, int item3Cnt,
			int item4Cnt) {
		super();
		this.userId = userId;
		this.name = name;
		this.platform = platform;
		this.exp = exp;
		this.userLevel = userLevel;
		this.item1Cnt = item1Cnt;
		this.item2Cnt = item2Cnt;
		this.item3Cnt = item3Cnt;
		this.item4Cnt = item4Cnt;
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

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
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

	public int getItem1Cnt() {
		return item1Cnt;
	}

	public void setItem1Cnt(int item1Cnt) {
		this.item1Cnt = item1Cnt;
	}

	public int getItem2Cnt() {
		return item2Cnt;
	}

	public void setItem2Cnt(int item2Cnt) {
		this.item2Cnt = item2Cnt;
	}

	public int getItem3Cnt() {
		return item3Cnt;
	}

	public void setItem3Cnt(int item3Cnt) {
		this.item3Cnt = item3Cnt;
	}

	public int getItem4Cnt() {
		return item4Cnt;
	}

	public void setItem4Cnt(int item4Cnt) {
		this.item4Cnt = item4Cnt;
	}

	@Override
	public String toString() {
		return "LoginView [userId=" + userId + ", name=" + name + ", platform="
				+ platform + ", exp=" + exp + ", userLevel=" + userLevel
				+ ", item1Cnt=" + item1Cnt + ", item2Cnt=" + item2Cnt
				+ ", item3Cnt=" + item3Cnt + ", item4Cnt=" + item4Cnt + "]";
	}
	
}
