package com.dota2.metatracker.integration.graphql.model.graphql;

import java.util.List;

public class Advantage {

    private short heroId;
    private List<Vs> vs;
    private List<With> with;

    public short getHeroId() {
        return heroId;
    }

    public List<Vs> getVs() {
        return vs;
    }

    public List<With> getWith() {
        return with;
    }

}
