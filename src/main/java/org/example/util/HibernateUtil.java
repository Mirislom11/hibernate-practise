package org.example.util;

import org.example.converter.BirthDateConverter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory buildSessionFactory () {
        Configuration configuration = new Configuration();
        configuration.addAttributeConverter(new BirthDateConverter(), true);
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
