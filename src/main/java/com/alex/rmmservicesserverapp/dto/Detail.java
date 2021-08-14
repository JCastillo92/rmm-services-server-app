package com.alex.rmmservicesserverapp.dto;

public class Detail {

    private String concept;
    private double cost;

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Detail() {
    }

    public Detail(String concept, double cost) {
        this.concept = concept;
        this.cost = cost;
    }
}
