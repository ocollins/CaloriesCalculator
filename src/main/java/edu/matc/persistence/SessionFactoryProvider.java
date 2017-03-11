package edu.matc.persistence;

/**
 * Created by student on 3/10/17.
 * This file provides a SessionFactory for use with DAOS using Hibernate
 */

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryProvider {
    private final Logger logger = Logger.getLogger(this.getClass());

    private static SessionFactory sessionFactory;

    public static void createSessionFactory() {
        SessionFactoryProvider provider = new SessionFactoryProvider();

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            provider.logger.info("Initial SessionFactory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;

    }
}
