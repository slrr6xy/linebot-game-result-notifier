package com.example.linebot.model;

public class GameResult {
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public String getDate() {
        return date;
    }

    public void setData(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return  homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
