package com.mp.matchword.ScoreOperations;

public class ScoreInfo {

    String UserName;
    long Score;

    public ScoreInfo(){}

    public ScoreInfo(String UserName, long Score) {
        this.UserName = UserName;
        this.Score = Score;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public long getScore() {
        return Score;
    }

    public void setScore(long Score) {
        this.Score = Score;
    }
}
