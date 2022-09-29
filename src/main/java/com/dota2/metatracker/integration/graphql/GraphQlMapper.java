package com.dota2.metatracker.integration.graphql;

import com.dota2.metatracker.integration.graphql.model.graphql.GraphqlRequestBody;
import com.dota2.metatracker.integration.graphql.util.GraphqlSchemaReaderUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class GraphQlMapper {

    private static final int NUMBER_OF_RESULTS = 100000;

    protected GraphqlRequestBody mapToGraphQlRequestBody(String fileSuffix, int minimumAmountOfGamesForMatchup) throws IOException {
        GraphqlRequestBody graphqlRequestBody = new GraphqlRequestBody();

        final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getHeroStats" + fileSuffix);

        graphqlRequestBody.setQuery(query);

        graphqlRequestBody.setVariables(Map.of(
                "take", NUMBER_OF_RESULTS,
                "matchLimit", minimumAmountOfGamesForMatchup));

        return graphqlRequestBody;
    }

}
