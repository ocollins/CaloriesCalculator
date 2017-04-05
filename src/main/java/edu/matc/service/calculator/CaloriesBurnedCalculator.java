package edu.matc.service.calculator;

/**
 * The type Calories burned calculator.
 */
public class CaloriesBurnedCalculator {

    /**
     * Calculate calories burned double.
     *
     * @param mets     the mets
     * @param weight   the weight
     * @param duration the duration
     * @return the double
     */
    public Double calculateCaloriesBurned(Double mets, Double weight, Double duration) {
        return (mets * weight * duration);
    }

    /**
     * Calculate duration double.
     *
     * @param mets     the mets
     * @param weight   the weight
     * @param calories the calories
     * @return the double
     */
    public Double calculateDuration(Double mets, Double weight, int calories) {
        return Math.round(calories /(mets * weight)*100)/100.0;
    }
}
