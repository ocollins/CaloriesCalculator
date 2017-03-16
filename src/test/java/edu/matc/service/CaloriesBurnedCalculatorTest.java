package edu.matc.service;

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import edu.matc.service.calculator.CaloriesBurnedCalculator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CaloriesBurnedCalculatorTest {

    private final Logger logger = Logger.getLogger(this.getClass());

    private CaloriesBurnedCalculator cbCalc;
    private Double mets;
    private Double weight;
    private Double duration;

    @Before
    public void setup() {
        cbCalc = new CaloriesBurnedCalculator();

        ActivityDao activityDao = new ActivityDao();
        Activity activity = activityDao.getActivity(1);

        mets = activity.getMets().doubleValue();
        weight = 85.0;
        duration = 1.5;
    }

    @Test
    public void testCalculateCaloriesBurned() {
        Double expected = 318.75;
        Double actual = cbCalc.calculateCaloriesBurned(mets, weight, duration);
        logger.info("Cals Burned: " + actual.toString());
        Assert.assertTrue("Calories burned calculation incorrect", actual.equals(expected));
    }
}
