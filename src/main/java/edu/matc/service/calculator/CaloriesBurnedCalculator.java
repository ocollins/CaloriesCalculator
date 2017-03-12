package edu.matc.service.calculator;

public class CaloriesBurnedCalculator {

    public Double calculateCaloriesBurned(Double mets, Double weight, Double duration) {
        return (mets * weight * duration);
    }
}
