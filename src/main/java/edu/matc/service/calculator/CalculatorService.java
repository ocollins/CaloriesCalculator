package edu.matc.service.calculator;

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import org.apache.log4j.Logger;

public class CalculatorService {

    private Logger logger = Logger.getLogger(this.getClass());

    private ActivityDao dao;
    private CaloriesBurnedCalculator cbCalc;
    private CaloriesBurnedRequest caloriesBurnedRequest;

    public CalculatorService() {
        dao = new ActivityDao();
        cbCalc = new CaloriesBurnedCalculator();
    }

    public CalculatorService(CaloriesBurnedRequest caloriesBurnedRequest) {
        this();
        this.caloriesBurnedRequest = caloriesBurnedRequest;
    }

    public Double getCaloriesBurned(int id) {
        Activity activity = dao.getActivity(id);
        Double mets = activity.getMets().doubleValue();
        Double duration = caloriesBurnedRequest.getDuration();
        Double weight = caloriesBurnedRequest.getWeight();

        Double convertedWeight;
        if (caloriesBurnedRequest.getUnit().equals("lb")) {
            convertedWeight = convertToKg(weight);
        } else {
            convertedWeight = weight;
        }

        return cbCalc.calculateCaloriesBurned(mets, convertedWeight, duration);
    }

    public Double convertToKg(Double weight) {
        return (weight / 2.2);
    }
}