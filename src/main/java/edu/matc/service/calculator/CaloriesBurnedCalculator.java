package edu.matc.service.calculator;

public class CaloriesBurnedCalculator {

    public Double calculateCaloriesBurned(Double mets, Double weight, Double duration) {
        return (mets * weight * duration);
    }

    public Double calculateDuration(Double mets, Double weight, int calories) {
        return Math.round(calories /(mets * weight)*100)/100.0;
    }
}
