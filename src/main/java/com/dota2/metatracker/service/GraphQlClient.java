package com.dota2.metatracker.service;

import com.dota2.metatracker.configuration.MetaTrackerConfigProperties;
import com.dota2.metatracker.model.GraphqlRequestBody;
import com.dota2.metatracker.model.graphql.Data;
import com.dota2.metatracker.model.graphql.HeroStats;
import com.dota2.metatracker.model.graphql.HeroStatsDto;
import com.dota2.metatracker.model.graphql.HeroVsHeroMatchup;
import com.dota2.metatracker.util.GraphqlSchemaReaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    public HeroStatsDto getHeroStats() throws IOException {

        WebClient webClient = WebClient.builder().build();

        HeroStatsDto heroStats1 = performRequest(webClient, generateGraphqlRequestBody("1"));
        HeroStatsDto heroStats2 = performRequest(webClient, generateGraphqlRequestBody("2"));
        HeroStatsDto heroStats3 = performRequest(webClient, generateGraphqlRequestBody("3"));
        HeroStatsDto heroStats4 = performRequest(webClient, generateGraphqlRequestBody("4"));
        HeroStatsDto heroStats5 = performRequest(webClient, generateGraphqlRequestBody("5"));
        HeroStatsDto heroStats6 = performRequest(webClient, generateGraphqlRequestBody("6"));

        return mergeHeroStats(heroStats1, heroStats2, heroStats3, heroStats4, heroStats5, heroStats6);
    }

    private GraphqlRequestBody generateGraphqlRequestBody(String fileSuffix) throws IOException {
        GraphqlRequestBody graphqlRequestBody = new GraphqlRequestBody();

        final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getHeroStats" + fileSuffix);

        graphqlRequestBody.setQuery(query);

        graphqlRequestBody.setVariables(Map.of(
                "take", NUMBER_OF_RESULTS,
                "matchLimit", MINIMUM_AMOUNT_OF_GAMES));

        return graphqlRequestBody;
    }

    private HeroStatsDto performRequest(WebClient webClient, GraphqlRequestBody graphqlRequestBody) {
        return webClient.post()
                .uri(url + "?jwt=" + config.jwtToken())
                .bodyValue(graphqlRequestBody)
                .retrieve()
                .bodyToMono(HeroStatsDto.class)
                .block();
    }

    private HeroStatsDto mergeHeroStats(HeroStatsDto heroStats1, HeroStatsDto heroStats2, HeroStatsDto heroStats3,
                                        HeroStatsDto heroStats4, HeroStatsDto heroStats5, HeroStatsDto heroStats6) {
        HeroStatsDto heroStatsCombined = new HeroStatsDto();

        List<HeroVsHeroMatchup> heroVsHeroMatchups = new ArrayList<>();
        populateHeroStats(heroVsHeroMatchups, heroStats1);
        populateHeroStats(heroVsHeroMatchups, heroStats2);
        populateHeroStats(heroVsHeroMatchups, heroStats3);
        populateHeroStats(heroVsHeroMatchups, heroStats4);
        populateHeroStats(heroVsHeroMatchups, heroStats5);
        populateHeroStats(heroVsHeroMatchups, heroStats6);

        HeroStats heroStats = new HeroStats();
        heroStats.setHeroVsHeroMatchups(heroVsHeroMatchups);

        Data data = new Data();
        data.setHeroStats(heroStats);
        heroStatsCombined.setData(data);

        return heroStatsCombined;
    }

    private void populateHeroStats(List<HeroVsHeroMatchup> heroVsHeroMatchups, HeroStatsDto heroStatsDto) {
        HeroStats heroStats = heroStatsDto.getData().getHeroStats();

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup1())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup1());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup2())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup2());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup3())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup3());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup4())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup4());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup5())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup5());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup6())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup6());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup7())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup7());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup8())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup8());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup9())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup9());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup10())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup10());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup11())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup11());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup12())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup12());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup13())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup13());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup14())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup14());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup15())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup15());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup16())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup16());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup17())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup17());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup18())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup18());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup19())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup19());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup20())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup20());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup21())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup21());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup22())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup22());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup23())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup23());

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup25())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup25());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup26())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup26());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup27())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup27());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup28())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup28());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup29())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup29());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup30())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup30());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup31())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup31());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup32())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup32());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup33())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup33());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup34())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup34());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup35())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup35());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup36())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup36());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup37())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup37());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup38())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup38());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup39())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup39());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup40())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup40());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup41())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup41());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup42())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup42());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup43())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup43());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup44())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup44());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup45())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup45());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup46())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup46());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup47())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup47());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup48())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup48());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup49())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup49());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup50())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup50());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup51())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup51());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup52())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup52());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup53())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup53());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup54())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup54());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup55())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup55());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup56())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup56());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup57())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup57());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup58())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup58());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup59())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup59());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup60())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup60());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup61())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup61());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup62())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup62());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup63())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup63());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup64())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup64());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup65())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup65());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup66())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup66());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup67())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup67());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup68())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup68());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup69())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup69());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup70())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup70());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup71())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup71());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup72())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup72());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup73())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup73());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup74())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup74());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup75())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup75());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup76())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup76());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup77())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup77());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup78())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup78());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup79())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup79());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup80())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup80());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup81())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup81());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup82())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup82());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup83())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup83());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup84())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup84());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup85())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup85());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup86())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup86());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup87())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup87());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup88())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup88());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup89())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup89());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup90())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup90());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup91())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup91());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup92())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup92());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup93())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup93());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup94())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup94());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup95())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup95());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup96())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup96());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup97())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup97());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup98())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup98());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup99())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup99());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup100())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup100());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup101())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup101());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup102())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup102());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup103())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup103());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup104())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup104());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup105())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup105());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup106())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup106());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup107())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup107());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup108())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup108());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup109())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup109());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup110())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup110());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup111())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup111());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup112())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup112());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup113())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup113());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup114())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup114());

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup119())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup119());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup120())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup120());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup121())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup121());

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup123())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup123());

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup126())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup126());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup128())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup128());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup129())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup129());

        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup135())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup135());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup136())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup136());
        if (!Objects.isNull(heroStats.getHeroVsHeroMatchup137())) heroVsHeroMatchups.add(heroStats.getHeroVsHeroMatchup137());
    }

}
