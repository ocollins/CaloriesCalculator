package edu.matc.service.calculator;

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import org.apache.log4j.Logger;

public class CalculatorService {

    private Logger logger = Logger.getLogger(this.getClass());

    private ActivityDao dao;
    private CaloriesBurnedCalculator cbCalc;
    private CaloriesBurnedRequest caloriesBurnedRequest;
    private DurationRequest durationRequest;

    public CalculatorService() {
        dao = new ActivityDao();
        cbCalc = new CaloriesBurnedCalculator();
    }

    public CalculatorService(CaloriesBurnedRequest caloriesBurnedRequest) {
        this();
        this.caloriesBurnedRequest = caloriesBurnedRequest;
    }

    public CalculatorService(DurationRequest durationRequest) {
        this();
        this.durationRequest = durationRequest;
    }

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

    public Double getDuration(int id) {
        int calories = durationRequest.getCalories();
        Activity activity = dao.getActivity(id);
        Double mets = activity.getMets().doubleValue();
        Double weight = caloriesBurnedRequest.getWeight();
        Double convertedWeight;

        if (durationRequest.getUnit().equals("lb")) {
            convertedWeight = convertToKg(weight);
        } else {
            convertedWeight = weight;
        }

        return cbCalc.calculateDuration(mets, weight, calories);

    }

    public Double convertToKg(Double weight) {
        return (weight / 2.2);
    }
}
