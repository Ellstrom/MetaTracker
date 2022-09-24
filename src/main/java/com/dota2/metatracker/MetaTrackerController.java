package com.dota2.metatracker;

import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.model.graphql.Advantage;
import com.dota2.metatracker.model.graphql.HeroStatsDto;
import com.dota2.metatracker.model.graphql.Vs;
import com.dota2.metatracker.service.GraphQlClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MetaTrackerController {

    private final GraphQlClient graphQlClient;

    public MetaTrackerController(GraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    @GetMapping(value = "/matchups-vs/hero")
    public List<CounterData> findHeroMatchupsVsHero(@RequestParam Hero hero,
                                                    @RequestParam(defaultValue = "0") int minimumAmountOfGamesForMatchup) throws IOException {
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(minimumAmountOfGamesForMatchup);

        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups().stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .map((Advantage advantage) -> mapToCounterData(advantage, hero.getId()))
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

}
