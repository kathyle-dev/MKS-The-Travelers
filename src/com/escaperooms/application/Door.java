package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Door {
    @JsonProperty("destination")
    private String destination;
    @JsonProperty("solution")
    private List<String> solution;

    @JsonCreator
    public Door(@JsonProperty("destination") String destination, @JsonProperty("solution") List<String> solution) {
        this.destination = destination;
        this.solution = solution;
    }

    public String getDestination() {
        return destination;
    }

    public List<String> getSolution() {
        return solution;
    }

}