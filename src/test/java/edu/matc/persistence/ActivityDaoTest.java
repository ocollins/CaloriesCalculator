package edu.matc.persistence;

import edu.matc.entity.Activity;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.log4j.*;

/**
 * Created by student on 3/10/17.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/3/17.
 *
 * @author O Collins
 */
public class ActivityDaoTest {
    ActivityDao dao;
    private final Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void setup() {
        dao = new ActivityDao();
    }

    @Test
    public void getAllActivities() throws Exception {
        ActivityDao dao = new ActivityDao();
        List<Activity> activityList = new ArrayList<Activity>();
        activityList = dao.getAllActivities();

        assertEquals("tennis", activityList.get(7).getName());
        //logger.info("Total activities " + activityList.size());

        assertTrue("Did not extract any activites", dao.getAllActivities().size() > 0);

    }

}