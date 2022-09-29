package com.dota2.metatracker.mapper;

import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.integration.graphql.model.graphql.Advantage;
import com.dota2.metatracker.integration.graphql.model.graphql.Vs;
import com.dota2.metatracker.integration.graphql.model.graphql.With;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CounterDataMapper {

    public CounterData mapToCounterData(Advantage advantage, List<Hero> vsHeroes, List<Hero> withHeroes) {
        String heroName = Hero.findById(advantage.getHeroId()).getName();

        if (vsHeroes == null) {
            vsHeroes = new ArrayList<>();
        }
        if (withHeroes == null) {
            withHeroes = new ArrayList<>();
        }

        Vs vsMatchup = populateVsMatchup(advantage, vsHeroes);
        With withMatchup = populateWithMatchup(advantage, withHeroes);

        double withAndVsSynergy;
        long lowestMatchupCount;

        if (vsMatchup == null & withMatchup == null) {
            throw new RuntimeException("A matchup must be selected.");
        } else if (vsMatchup == null) {
            withAndVsSynergy = withMatchup.getSynergy();
            lowestMatchupCount = withMatchup.getMatchCount();
        } else if (withMatchup == null) {
            withAndVsSynergy = vsMatchup.getSynergy();
            lowestMatchupCount = vsMatchup.getMatchCount();
        } else {
            withAndVsSynergy = vsMatchup.getSynergy() + withMatchup.getSynergy();

            if (vsMatchup.getMatchCount() != 0 && withMatchup.getMatchCount() != 0) {
                lowestMatchupCount = Math.min(vsMatchup.getMatchCount(), withMatchup.getMatchCount());
            } else if (vsMatchup.getMatchCount() != 0) {
                lowestMatchupCount = vsMatchup.getMatchCount();
            } else if (withMatchup.getMatchCount() != 0) {
                lowestMatchupCount = withMatchup.getMatchCount();
            } else {
                lowestMatchupCount = 0;
            }
        }

        return new CounterData(heroName, withAndVsSynergy, lowestMatchupCount);
    }

    private Vs populateVsMatchup(Advantage advantage, List<Hero> vsHeroes) {
        Vs vsMatchup = null;
        for (Hero hero: vsHeroes) {
            if (vsMatchup == null) {
                vsMatchup = advantage.getVs().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
                        .findFirst()
                        .orElseGet(Vs::new);
            } else {
                Optional<Vs> newMatchup = advantage.getVs().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
                        .findFirst();

                if (newMatchup.isPresent()) {
                    double newSynergy = newMatchup.get().getSynergy();
                    double synergy = vsMatchup.getSynergy();
                    double updatedSynergy = synergy + newSynergy;
                    vsMatchup.setSynergy(updatedSynergy);

                    if (newMatchup.get().getMatchCount() != 0 && vsMatchup.getMatchCount() != 0) {
                        vsMatchup.setMatchCount(Math.min(newMatchup.get().getMatchCount(), vsMatchup.getMatchCount()));
                    } else if (newMatchup.get().getMatchCount() != 0) {
                        vsMatchup.setMatchCount(newMatchup.get().getMatchCount());
                    }
                }
            }
        }
        return vsMatchup;
    }

    private With populateWithMatchup(Advantage advantage, List<Hero> withHeroes) {
        With withMatchup = null;
        for (Hero hero: withHeroes) {
            if (withMatchup == null) {
                withMatchup = advantage.getWith().stream()
                        .filter(with -> hero.getId() == with.getHeroId2())
                        .findFirst()
                        .orElseGet(With::new);
            } else {
                Optional<With> newMatchup = advantage.getWith().stream()
                        .filter(with -> hero.getId() == with.getHeroId2())
                        .findFirst();

                if (newMatchup.isPresent()) {
                    double newSynergy = newMatchup.get().getSynergy();
                    double synergy = withMatchup.getSynergy();
                    double updatedSynergy = synergy + newSynergy;
                    withMatchup.setSynergy(updatedSynergy);

                    if (newMatchup.get().getMatchCount() != 0 && withMatchup.getMatchCount() != 0) {
                        withMatchup.setMatchCount(Math.min(newMatchup.get().getMatchCount(), withMatchup.getMatchCount()));
                    } else if (newMatchup.get().getMatchCount() != 0) {
                        withMatchup.setMatchCount(newMatchup.get().getMatchCount());
                    }
                }
            }
        }
        return withMatchup;
    }

}
