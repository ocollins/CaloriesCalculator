package edu.matc.service.calculator;

/**
 * Created by Ben on 3/11/2017.
 */
public class CaloriesBurnedCalculator {

    public double getCaloriesBurned(Double mets, Double weight, Double duration) {
        return (mets * weight * duration);
    }
}
