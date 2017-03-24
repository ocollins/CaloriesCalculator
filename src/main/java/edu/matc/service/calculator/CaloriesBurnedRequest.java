package edu.matc.service.calculator;

public class CaloriesBurnedRequest {

    private Double duration;
    private String unit;
    private Double weight;

    public CaloriesBurnedRequest() {}

    public CaloriesBurnedRequest(Double weight, Double duration, String unit) {
        this.weight = weight;
        this.duration = duration;
        this.unit = unit;
    }

    public String getUnit() { return unit; }

    public void setUnit(String unit) { this.unit = unit; }

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
                "duration=" + duration +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                '}';
    }
}
