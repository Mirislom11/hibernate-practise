package org.example.service;

import org.example.customType.BirthDay;
import org.example.entity.Profile;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class UserService {
    public static void main(String[] args) {
        checkOneToOne();
    }

    public static void checkOneToOne() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.get(User.class, "2101");
            transaction.commit();
        }
    }


}

/*User user = User.builder()
                    .username("test@2gmail.com")
                    .lastName("adsdsd")
                    .firstName("sdsad")
                    .age(23)
                    .birthDate(new BirthDay(LocalDate.of(2000, 6, 11)))
                    .build();
            Profile profile = Profile.builder()
                    .language("ru")
                    .street("Labzak 55")
                    .build();

            profile.setUser(user);
            session.persist(user);*/
