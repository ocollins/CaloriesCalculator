package edu.matc.service.calculator;

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import org.apache.log4j.Logger;

/**
 * The type Calculator service.
 */
public class CalculatorService {

    private Logger logger = Logger.getLogger(this.getClass());

    private ActivityDao dao;
    private CaloriesBurnedCalculator cbCalc;
    private CaloriesBurnedRequest caloriesBurnedRequest;
    private DurationRequest durationRequest;

    /**
     * Instantiates a new Calculator service.
     */
    public CalculatorService() {
        dao = new ActivityDao();
        cbCalc = new CaloriesBurnedCalculator();
    }

    /**
     * Instantiates a new Calculator service.
     *
     * @param caloriesBurnedRequest the calories burned request
     */
    public CalculatorService(CaloriesBurnedRequest caloriesBurnedRequest) {
        this();
        this.caloriesBurnedRequest = caloriesBurnedRequest;
    }

    /**
     * Instantiates a new Calculator service.
     *
     * @param durationRequest the duration request
     */
    public CalculatorService(DurationRequest durationRequest) {
        this();
        this.durationRequest = durationRequest;
    }

    /**
     * Gets calories burned.
     *
     * @param id the id
     * @return the calories burned
     */
    public Double getCaloriesBurned(int id) {
        Double duration = caloriesBurnedRequest.getDuration();

        Activity activity = dao.getActivity(id);
        Double mets = activity.getMets().doubleValue();

        Double weight = caloriesBurnedRequest.getWeight();

        Double convertedWeight;
        if (caloriesBurnedRequest.getUnit().equals("lb")) {
            convertedWeight = convertToKg(weight);
        } else {
            convertedWeight = weight;
        }

        return cbCalc.calculateCaloriesBurned(mets, convertedWeight, duration);
    }

    /**
     * Gets duration.
     *
     * @param id the id
     * @return the duration
     */
    public Double getDuration(int id) {
        int calories = durationRequest.getCalories();
        Activity activity = dao.getActivity(id);
        Double mets = activity.getMets().doubleValue();
        Double weight = durationRequest.getWeight();
        Double convertedWeight;

        if (durationRequest.getUnit().equals("lb")) {
            convertedWeight = convertToKg(weight);
        } else {
            convertedWeight = weight;
        }

        return cbCalc.calculateDuration(mets, convertedWeight, calories);

    }

    /**
     * Convert to kg double.
     *
     * @param weight the weight
     * @return the double
     */
    public Double convertToKg(Double weight) {
        return (weight / 2.2);
    }
}
