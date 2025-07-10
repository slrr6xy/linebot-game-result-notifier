package com.example.linebot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserPreference {

    @Id
    private String userId;

    private String teamName;

    public UserPreference() {}

    public UserPreference(String userId, String teamName) {
        this.userId = userId;
        this.teamName = teamName;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
}
