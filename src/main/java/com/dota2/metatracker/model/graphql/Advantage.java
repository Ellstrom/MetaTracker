package com.dota2.metatracker.model.graphql;

import java.util.List;

public class Advantage {

    private List<Vs> vs;
    private List<With> with;

    public List<Vs> getVs() {
        return vs;
    }

    public List<With> getWith() {
        return with;
    }

}
