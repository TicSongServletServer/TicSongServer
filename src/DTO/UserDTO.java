package DTO;

import java.io.Serializable;

public class UserDTO implements Serializable{
    private String userId;
    private String name;
    private int platform;

    public UserDTO() {
        // TODO Auto-generated constructor stub
    }

    public UserDTO(String userId, String name, int platform) {
        super();
        this.userId = userId;
        this.name = name;
        this.platform=platform;
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", platform=" + platform +
                '}';
    }
}