package com.dota2.metatracker;

import com.dota2.metatracker.model.Hero;
import com.dota2.metatracker.model.HeroStatsDto;
import com.dota2.metatracker.service.GraphQlClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MetaTrackerController {

    private static final Logger logger = LoggerFactory.getLogger(MetaTrackerController.class);

    private final GraphQlClient graphQlClient;

    public MetaTrackerController(GraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    @GetMapping("/meta-tracker/hero")
    public String findHeroWithHighestCounteredRating(@RequestParam String name) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        HeroStatsDto heroStatsDto = graphQlClient.getHeroStats(Hero.findByName(name).getId());
        logger.info(ow.writeValueAsString(heroStatsDto));
        Hero heroWithHighestCounteredRating = Hero.findById(heroStatsDto.getData().getHeroStats().getMatchUp().get(0).getVs().get(0).getHeroId2());
        return heroWithHighestCounteredRating.getName();
    }

}
