package edu.matc.service;

import edu.matc.entity.Activity;
import edu.matc.persistence.ActivityDao;
import edu.matc.service.calculator.CaloriesBurnedCalculator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Ben on 3/11/2017.
 */
public class CaloriesBurnedCalculatorTest {

    Activity activity;
    CaloriesBurnedCalculator cbCalc;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        cbCalc = new CaloriesBurnedCalculator();

        activity = new Activity();
        activity.setId(999);
        activity.setName("soccer");
        activity.setMets(BigDecimal.valueOf(11.5));
    }

    @Test
    public void testGetCaloriesBurned() {
        Double expected = 1466.25;

        Double mets = activity.getMets().doubleValue();
        Double weight = 85.0;
        Double duration = 1.5;

        Double actual = cbCalc.getCaloriesBurned(
                mets, weight, duration
        );

        logger.info("Cals Burned: " + actual.toString());

        Assert.assertTrue("Calculation incorrect", actual.equals(expected));
    }
}
