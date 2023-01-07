package com.dota2.metatracker.service;

import com.dota2.metatracker.integration.graphql.GraphQlIntegration;
import com.dota2.metatracker.integration.graphql.model.graphql.Vs;
import com.dota2.metatracker.mapper.CounterDataMapper;
import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.integration.graphql.model.graphql.Advantage;
import com.dota2.metatracker.integration.graphql.model.graphql.HeroStatsDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MetaDataService {

    private final CounterDataMapper counterDataMapper;
    private final GraphQlIntegration graphQlIntegration;

    public MetaDataService(CounterDataMapper counterDataMapper, GraphQlIntegration graphQlIntegration) {
        this.counterDataMapper = counterDataMapper;
        this.graphQlIntegration = graphQlIntegration;
    }

    public List<CounterData> getCounterData(List<Hero> vsHeroes, List<Hero> withHeroes, List<Hero> heroesToInclude, int minimumAmountOfGamesForMatchup) throws IOException {
        HeroStatsDto heroStatsDto = graphQlIntegration.getHeroStats(minimumAmountOfGamesForMatchup);

        /* TODO - lös cachning sedan rensa detta.
        Optional<Vs> amVsDrow = heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups()
                .stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .filter(advantage -> advantage.getHeroId() == 1)
                .flatMap(advantage -> advantage.getVs().stream())
                .filter(vs -> vs.getHeroId2() == 6)
                .findFirst();

        int a = 123454;

         */

        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchups().stream()
                .flatMap(heroVsHeroMatchup -> heroVsHeroMatchup.getAdvantage().stream())
                .filter(advantage -> heroesToInclude == null
                        || heroesToInclude.contains(Hero.findById(advantage.getHeroId())))
                .map((Advantage advantage) -> counterDataMapper.mapToCounterData(advantage, vsHeroes, withHeroes))
                .sorted(Comparator.comparingDouble(CounterData::getWinrateAdvantage).reversed())
                .collect(Collectors.toList());
    }

    public void clearCache() {
        graphQlIntegration.clearCache();
    }

}
