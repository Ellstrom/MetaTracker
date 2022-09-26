package com.dota2.metatracker;

import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.model.graphql.Advantage;
import com.dota2.metatracker.model.graphql.HeroStatsDto;
import com.dota2.metatracker.model.graphql.Vs;
import com.dota2.metatracker.model.graphql.With;
import com.dota2.metatracker.service.GraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MetaTrackerController {

    private final GraphQlClient graphQlClient;

    public MetaTrackerController(GraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    @GetMapping(value = "/matchups/vs/hero")
    public List<CounterData> findHeroMatchupsVsHero(@RequestParam Hero hero,
                                                    @RequestParam(defaultValue = "0") int minimumAmountOfGamesForMatchup) throws IOException {
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(minimumAmountOfGamesForMatchup);

        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups().stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .map((Advantage advantage) -> mapToCounterData(advantage, hero.getId()))
                .sorted(Comparator.comparingDouble(CounterData::getWinrateAdvantage).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/matchups/vs/heroes")
    public List<CounterData> findHeroMatchupsVsHeroes(@RequestParam List<Hero> heroes,
                                                    @RequestParam(defaultValue = "0") int minimumAmountOfGamesForMatchup) throws IOException {
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(minimumAmountOfGamesForMatchup);

        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups().stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .map((Advantage advantage) -> mapToCounterData(advantage, heroes))
                .sorted(Comparator.comparingDouble(CounterData::getWinrateAdvantage).reversed())
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/matchups/vs-and-with/heroes")
    public List<CounterData> findHeroMatchupsWithAndVsHeroes(@RequestParam(required = false) List<Hero> vsHeroes,
                                                             @RequestParam(required = false) List<Hero> withHeroes,
                                                             @RequestParam(defaultValue = "0") int minimumAmountOfGamesForMatchup) throws IOException {
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(minimumAmountOfGamesForMatchup);

        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups().stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .map((Advantage advantage) -> mapToCounterData(advantage, vsHeroes, withHeroes))
                .sorted(Comparator.comparingDouble(CounterData::getWinrateAdvantage).reversed())
                .collect(Collectors.toList());
    }

    private CounterData mapToCounterData(Advantage advantage, short vsHeroId) {
        String heroName = Hero.findById(advantage.getHeroId()).getName();

        Vs matchup = advantage.getVs().stream()
                .filter(vs -> vsHeroId == vs.getHeroId2())
                .findFirst()
                .orElseGet(Vs::new);

        return new CounterData(heroName, matchup.getSynergy(), matchup.getMatchCount());
    }

    private CounterData mapToCounterData(Advantage advantage, List<Hero> vsHeroes) {
        String heroName = Hero.findById(advantage.getHeroId()).getName();

        Vs matchup = null;
        for (Hero hero: vsHeroes) {
            if (matchup == null) {
                matchup = advantage.getVs().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
                        .findFirst()
                        .orElseGet(Vs::new);
            } else {
                Optional<Vs> newMatchup = advantage.getVs().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
                        .findFirst();

                if (newMatchup.isPresent()) {
                    double newSynergy = newMatchup.get().getSynergy();
                    double synergy = matchup.getSynergy();
                    double updatedSynergy = synergy + newSynergy;
                    matchup.setSynergy(updatedSynergy);

                    if (newMatchup.get().getMatchCount() < matchup.getMatchCount()) {
                        matchup.setMatchCount(newMatchup.get().getMatchCount());
                    }
                }
            }
        }

        assert matchup != null;
        return new CounterData(heroName, matchup.getSynergy(), matchup.getMatchCount());
    }

    private CounterData mapToCounterData(Advantage advantage, List<Hero> vsHeroes, List<Hero> withHeroes) {
        String heroName = Hero.findById(advantage.getHeroId()).getName();

        if (vsHeroes == null) {
            vsHeroes = new ArrayList<>();
        }
        if (withHeroes == null) {
            withHeroes = new ArrayList<>();
        }

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

        With withMatchup = null;
        for (Hero hero: withHeroes) {
            if (withMatchup == null) {
                withMatchup = advantage.getWith().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
                        .findFirst()
                        .orElseGet(With::new);
            } else {
                Optional<With> newMatchup = advantage.getWith().stream()
                        .filter(vs -> hero.getId() == vs.getHeroId2())
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

}
