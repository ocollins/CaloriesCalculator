package edu.matc.service.calculator;

public class CaloriesBurnedRequest {

    private Double weight;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    private Double duration;
    private String unit;

    public CaloriesBurnedRequest() {}

    public CaloriesBurnedRequest(Double weight, Double duration) {
        this.weight = weight;
        this.duration = duration;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CaloriesBurnedRequest{" +
                "weight=" + weight +
                ", duration=" + duration +
                '}';
    }
}
