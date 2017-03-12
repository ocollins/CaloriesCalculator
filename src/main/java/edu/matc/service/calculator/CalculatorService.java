package edu.matc.service.calculator;

import edu.matc.persistence.ActivityDao;
import org.apache.log4j.Logger;

public class CalculatorService {

    private Logger logger = Logger.getLogger(this.getClass());
    private ActivityDao dao;
    private CaloriesBurnedRequest caloriesBurnedRequest;

    public CalculatorService() {
        dao = new ActivityDao();
    }

    public CalculatorService(CaloriesBurnedRequest caloriesBurnedRequest) {
        this();
        this.caloriesBurnedRequest = caloriesBurnedRequest;
    }

    public Double getCaloriesBurned() {
        // TODO: Activity activity = getActivity(id);
        // TODO: Double mets = activity.getMets();
        // TODO: return calculateCaloriesBurned(mets, caloriesBurnedRequest.getWeight(), caloriesBurnedRequest.getDuration());
        return 0.0;
    }
}
