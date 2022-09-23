package com.dota2.metatracker.service;

import com.dota2.metatracker.configuration.MetaTrackerConfigProperties;
import com.dota2.metatracker.model.GraphqlRequestBody;
import com.dota2.metatracker.model.graphql.HeroStatsDto;
import com.dota2.metatracker.util.GraphqlSchemaReaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;

@Service
public class GraphQlClient {

    private final String url;
    private static final int NUMBER_OF_RESULTS = 1000;
    private static final int MINIMUM_AMOUNT_OF_GAMES = 0;

    private final MetaTrackerConfigProperties config;

    public GraphQlClient(@Value("https://api.stratz.com/graphql") String url, MetaTrackerConfigProperties config) {
        this.url = url;
        this.config = config;
    }

    public HeroStatsDto getHeroStats(final short heroId1) throws IOException {

        WebClient webClient = WebClient.builder().build();
        GraphqlRequestBody graphqlRequestBody = new GraphqlRequestBody();

        final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getHeroStats");

        graphqlRequestBody.setQuery(query);

        graphqlRequestBody.setVariables(Map.of(
                "take", NUMBER_OF_RESULTS,
                "matchLimit", MINIMUM_AMOUNT_OF_GAMES));

        return webClient.post()
                .uri(url + "?jwt=" + config.jwtToken())
                .bodyValue(graphqlRequestBody)
                .retrieve()
                .bodyToMono(HeroStatsDto.class)
                .block();
    }

}
