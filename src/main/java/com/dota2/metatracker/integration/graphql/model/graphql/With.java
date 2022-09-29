package com.dota2.metatracker.integration.graphql.model.graphql;

public class With {

    private short heroId2;
    private double synergy;
    private long matchCount;

    public short getHeroId2() {
        return heroId2;
    }

    public double getSynergy() {
        return synergy;
    }

    public long getMatchCount() {
        return matchCount;
    }

    public void setHeroId2(short heroId2) {
        this.heroId2 = heroId2;
    }

    public void setSynergy(double synergy) {
        this.synergy = synergy;
    }

    public void setMatchCount(long matchCount) {
        this.matchCount = matchCount;
    }

}
