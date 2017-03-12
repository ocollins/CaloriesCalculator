package edu.matc.persistence;

import edu.matc.entity.Activity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.log4j.*;

/**
 * Created by student on 3/10/17.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/3/17.
 *
 * @author O Collins
 */
public class ActivityDaoTest {
    ActivityDao dao;
    Activity testActivity;
    int testIndex;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        dao = new ActivityDao();
        Number mets = 7.5;
        BigDecimal metsDecimal = new BigDecimal(mets.toString());
        testActivity = new Activity(8, "tennis", metsDecimal);
        testIndex = 7;
    }

    @Test
    public void getAllActivities() throws Exception {
        ActivityDao dao = new ActivityDao();
        List<Activity> activityList = new ArrayList<Activity>();
        activityList = dao.getAllActivities();

        //Just testing
        logger.info("Total activities " + activityList.size());
        logger.info("Activity " + testActivity.getId() +" " + activityList.get(testIndex).getName());

        //Asserting
        assertEquals("tennis", activityList.get(testIndex).getName());
        assertTrue("Did not extract any activites", dao.getAllActivities().size() > 0);

    }

    @Test
    public void getActivity() throws Exception {
        Activity anActivity = dao.getActivity(testActivity.getId());

        //Just testing
        logger.info("Activity with id 8 " + anActivity.getName());

        //Asserting
        assertEquals("Activity is incorrect ", testActivity.getName(), anActivity.getName());
        assertEquals("Mets for the activity are incorrect ", testActivity.getMets(), anActivity.getMets());

    }

}