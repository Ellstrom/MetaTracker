package com.dota2.metatracker.integration.graphql.util;

import java.io.IOException;
import java.util.Objects;

public class GraphqlSchemaReaderUtil {

    public static String getSchemaFromFileName(final String filename) throws IOException {
        return new String(
                Objects.requireNonNull(GraphqlSchemaReaderUtil.class.getClassLoader().getResourceAsStream("graphql/" + filename + ".graphql")).readAllBytes());
    }

}
