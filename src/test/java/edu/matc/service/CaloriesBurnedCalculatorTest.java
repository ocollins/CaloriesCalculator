package edu.matc.service;

import edu.matc.entity.Activity;
import edu.matc.service.calculator.CaloriesBurnedCalculator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CaloriesBurnedCalculatorTest {

    private final Logger logger = Logger.getLogger(this.getClass());

    Activity activity;
    CaloriesBurnedCalculator cbCalc;
    Double mets;
    Double weight;
    Double duration;

    @Before
    public void setup() {
        cbCalc = new CaloriesBurnedCalculator();

        // TODO: update when getActivity(id) gets implemented
        activity = new Activity();
        activity.setId(999);
        activity.setName("soccer");
        activity.setMets(BigDecimal.valueOf(11.5));

        mets = activity.getMets().doubleValue();
        weight = 85.0;
        duration = 1.5;
    }

    @Test
    public void testCalculateCaloriesBurned() {
        Double expected = 1466.25;
        Double actual = cbCalc.calculateCaloriesBurned(mets, weight, duration);
        logger.info("Cals Burned: " + actual.toString());
        Assert.assertTrue("Calories burned calculation incorrect", actual.equals(expected));
    }
}
