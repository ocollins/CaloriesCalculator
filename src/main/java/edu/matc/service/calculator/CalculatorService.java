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
        return cbCalc.calculateCaloriesBurned(mets, convertToKG(caloriesBurnedRequest.getWeight(), caloriesBurnedRequest.getUnit()), caloriesBurnedRequest.getDuration());
    }

    public Double convertToKG(Double weight, String unit) {
        if (unit == "kg") {
            return weight;
        } else {
            //convert me
            return 1.0;
        }
    }
}
