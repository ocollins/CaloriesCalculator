package edu.matc.service;

import edu.matc.service.calculator.CalculatorService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class CalculatorServiceTest {

    private Logger logger = Logger.getLogger(this.getClass());
    CalculatorService calculatorService;

    @Before
    public void setup() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testGetCaloriesBurned() {}
}
