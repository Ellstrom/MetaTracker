package com.dota2.metatracker.model;

public class GraphqlRequestBody {

    private String query;
    private Object variables;

    public GraphqlRequestBody() {
    }

    public GraphqlRequestBody(String query, Object variables) {
        this.query = query;
        this.variables = variables;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Object getVariables() {
        return variables;
    }

    public void setVariables(Object variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "GraphqlRequestBody{" +
                "query='" + query + '\'' +
                ", variables=" + variables +
                '}';
    }
}
