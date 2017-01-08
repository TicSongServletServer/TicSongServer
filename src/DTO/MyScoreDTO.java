package DTO;

import java.io.Serializable;

public class MyScoreDTO implements Serializable{

    private String userId;
    private int exp;
    private int userLevel;


    public MyScoreDTO() {
    }
    
    public MyScoreDTO(String userId, int exp, int userLevel) {
        this.userId = userId;
        this.exp = exp;
        this.userLevel = userLevel;
    }
    public MyScoreDTO(int exp, String userId, int userLevel) {
        this.exp = exp;
        this.userId = userId;
        this.userLevel = userLevel;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }


    @Override
    public String toString() {
        return "MyScoreDTO{" +
                "exp=" + exp +
                ", userId='" + userId + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}