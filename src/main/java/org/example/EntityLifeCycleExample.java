package org.example;

import org.example.converter.BirthDateConverter;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EntityLifeCycleExample {
    public static void main(String[] args) {
        User user = User.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .username("ivan4@gmail.com")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(user);
                transaction.commit();
            }
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                user.setFirstName("Sveta");
//                session.refresh(user);
                session.merge(user);
                transaction.commit();
            }
        }
    }
}
