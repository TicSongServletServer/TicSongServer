package DTO;

import java.io.Serializable;

/**
 * Created by Jeon on 2016-08-08.
 */
public class ItemDTO implements Serializable {

    private String userId;
    private int item1Cnt;
    private int item2Cnt;
    private int item3Cnt;
    private int item4Cnt;

    public ItemDTO() {
    }
    
    public ItemDTO( String userId, int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt) {
    	this.userId = userId;
    	this.item1Cnt = item1Cnt;
        this.item2Cnt = item2Cnt;
        this.item3Cnt = item3Cnt;
        this.item4Cnt = item4Cnt;
    }

    public ItemDTO(int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt, String userId) {
        this.item1Cnt = item1Cnt;
        this.item2Cnt = item2Cnt;
        this.item3Cnt = item3Cnt;
        this.item4Cnt = item4Cnt;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "ItemDTO [userId=" + userId + ", item1Cnt=" + item1Cnt
				+ ", item2Cnt=" + item2Cnt + ", item3Cnt=" + item3Cnt
				+ ", item4Cnt=" + item4Cnt + "]";
	}

}