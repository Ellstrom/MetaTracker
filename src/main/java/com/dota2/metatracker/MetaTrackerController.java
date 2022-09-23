package com.dota2.metatracker;

import com.dota2.metatracker.model.CounterData;
import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.model.graphql.HeroStatsDto;
import com.dota2.metatracker.model.graphql.Vs;
import com.dota2.metatracker.service.GraphQlClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MetaTrackerController {

    private static final Logger logger = LoggerFactory.getLogger(MetaTrackerController.class);

    private final GraphQlClient graphQlClient;

    public MetaTrackerController(GraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

/* - TODO
    @GetMapping(value = "/matchups-vs/hero")
    public List<CounterData> findHeroMatchupsVsHero(@RequestParam Hero hero) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(hero.getId());
        logger.info(ow.writeValueAsString(heroStatsDto));
        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchup1().getAdvantage().get(0).getVs().stream()
                .map(this::mapToCounterData)
                .collect(Collectors.toList());
    }

 */

    @GetMapping(value = "/matchups-as/hero")
    public List<CounterData> findHeroMatchupsAsHero(@RequestParam Hero hero) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(hero.getId());
        logger.info(ow.writeValueAsString(heroStatsDto));
        return heroStatsDto.getData().getHeroStats().getHeroVsHeroMatchup1().getAdvantage().get(0).getVs().stream()
                .map(this::mapToCounterData)
                .collect(Collectors.toList());
    }

    private CounterData mapToCounterData(Vs heroMatchUp) {
        String counteredHeroName = Hero.findById(heroMatchUp.getHeroId2()).getName();
        double winrateAdvantage = heroMatchUp.getSynergy();
        return new CounterData(counteredHeroName, winrateAdvantage);
    }

}
