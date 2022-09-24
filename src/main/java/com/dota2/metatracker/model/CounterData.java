package com.dota2.metatracker.model;

public class CounterData {

    private String heroName;
    private double winrateAdvantage;
    private long matchCount;

    public CounterData() {
    }

    public CounterData(String heroName, double winrateAdvantage, long matchCount) {
        this.heroName = heroName;
        this.winrateAdvantage = winrateAdvantage;
        this.matchCount = matchCount;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public double getWinrateAdvantage() {
        return winrateAdvantage;
    }

    public void setWinrateAdvantage(double winrateAdvantage) {
        this.winrateAdvantage = winrateAdvantage;
    }

    public long getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(long matchCount) {
        this.matchCount = matchCount;
    }
}
