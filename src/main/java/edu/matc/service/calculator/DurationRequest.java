package edu.matc.service.calculator;

/**
 * The type Duration request.
 */
public class DurationRequest {

    private int calories;
    private String unit;
    private Double weight;

    /**
     * Instantiates a new Duration request.
     */
    public DurationRequest() {}

    /**
     * Instantiates a new Duration request.
     *
     * @param weight   the weight
     * @param calories the calories
     * @param unit     the unit
     */
    public DurationRequest(Double weight, int calories, String unit) {
        this.weight = weight;
        this.calories = calories;
        this.unit = unit;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Double getWeight() {

        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {

        this.weight = weight;
    }

    /**
     * Gets calories.
     *
     * @return the calories
     */
    public int getCalories() {

        return calories;
    }

    /**
     * Sets duration.
     *
     * @param calories the calories
     */
    public void setCalories(int calories) {

        this.calories = calories;
    }

    @Override
    public String toString() {
        return "DurationRequest{" +
                "calories=" + calories +
                ", unit='" + unit + '\'' +
                ", weight=" + weight +
                '}';
    }
}
