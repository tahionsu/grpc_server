package com.ntnv.gldva.json;

import java.io.Serializable;

public class CustomJSONb implements Serializable {
    private String scoreSCA;

    private String grind;

    public String getScoreSCA() {
        return scoreSCA;
    }

    public void setScoreSCA(String score) {
        this.scoreSCA = score;
    }

    public String getGrind() {
        return grind;
    }

    public void setGrind(String grind) {
        this.grind = grind;
    }

    @Override
    public String toString() {
        return "{\"scoreSCA\": " + "\"" + scoreSCA + "\", " +
                "\"grind\": " + "\"" + grind + "\"}";
    }
}
