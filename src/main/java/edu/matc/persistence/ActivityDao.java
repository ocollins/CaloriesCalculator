package edu.matc.persistence;

/**
 * Created by Calories Calculator team on 3/10/17.
 */

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

import edu.matc.entity.Activity;

public class ActivityDao {
    private final Logger logger = Logger.getLogger(this.getClass());

    /** Return a list of all activities
     * @return All activities
     */
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<Activity>();
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        try {
            activities = session.createCriteria(Activity.class).list();
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }
        return activities;
    }

    /**
     * Gets activity.
     *
     * @param id the id
     * @return the activity
     */
    public Activity getActivity(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Activity activity = null;

        try {
            activity = (Activity) session.get(Activity.class, id);
        } catch (HibernateException he) {
            logger.info("Hibernate Exception " + he);
        } finally {
            session.close();
        }

        return activity;
    }

}
