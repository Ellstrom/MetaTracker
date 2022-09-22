package com.dota2.metatracker.model;

public class CounterData {

    private String heroName;
    private double winrateAdvantage;

    public CounterData() {
    }

    public CounterData(String heroName, double winrateAdvantage) {
        this.heroName = heroName;
        this.winrateAdvantage = winrateAdvantage;
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

}
