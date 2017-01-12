package DTO;

import java.io.Serializable;

public class Item5 extends ItemDTO  implements Serializable {
	
	private int item5Cnt;
	
	public Item5() {}
	
	public Item5(String userId, int item1Cnt, int item2Cnt, int item3Cnt, int item4Cnt, int item5Cnt) {
		super(userId, item1Cnt, item2Cnt, item3Cnt, item4Cnt);
		this.item5Cnt = item5Cnt;
	}

	public int getItem1Cnt() {
		return super.getItem1Cnt();
	}
	
	public int getItem2Cnt() {
		return super.getItem2Cnt();
	}
	
	public int getItem3Cnt() {
		return super.getItem3Cnt();
	}
	
	public int getItem4Cnt() {
		return super.getItem4Cnt();
	}
	
	public int getItem5Cnt() {
		return item5Cnt;
	}

	public void setItem5Cnt(int item5Cnt) {
		this.item5Cnt = item5Cnt;
	}

	@Override
	public String toString() {
		return super.toString() + " Item5 [item5Cnt=" + item5Cnt + "]";
	}
	
}
