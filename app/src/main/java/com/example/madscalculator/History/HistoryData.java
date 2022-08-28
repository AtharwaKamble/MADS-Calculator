package com.example.madscalculator.History;

import java.io.Serializable;

public class HistoryData implements Serializable {
    private String operation;
    private String results;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }


}
