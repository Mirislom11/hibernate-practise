package org.example;

import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class HibernateRunnerTest {

    /*@Test
    void checkManyToMany () {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()){
            session.beginTransaction();
            var user = session.get(User.class, "1701");

            Chat chat = Chat.builder().build();
            chat.setName("Developers");
            user.addChat(chat);
            session.persist(chat);

            session.getTransaction().commit();

        }
    }*/

    @Test
    void checkManyToManyV2() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var user = session.get(User.class, "1701");
            var chat = session.get(Chat.class, "2251");

            var userChat = UserChat.builder()
                    .createdBy("Mirislom")
                    .createdAt(Instant.now())
                    .build();

            userChat.setUser(user);
            userChat.setChat(chat);
            session.persist(userChat);


            session.getTransaction().commit();
        }
    }

    @Test
    void testLocaleInfo() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.get(Company.class, "1651");
            company.getLocales().add(LocaleInfo.of("ru", "Описание на русском"));
            company.getLocales().add(LocaleInfo.of("en", "English description"));
            session.getTransaction().commit();
        }
    }
}
