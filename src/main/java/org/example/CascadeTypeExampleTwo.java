package org.example;

import org.example.customType.BirthDay;
import org.example.entity.Company;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class CascadeTypeExampleTwo {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Company company = new Company();
            company.setName("Amazon");
            session.persist(company);
            User user = User.builder()
                    .firstName("Eldor")
                    .lastName("Ergashev")
                    .age(25)
                    .birthDate(new BirthDay(LocalDate.of(1997, 11, 12)))
                    .username("eldor@gmail.com")
                    .company(company)
                    .build();
            session.persist(user);
            transaction.commit();
        }
    }
}

/*Company company = new Company();
            company.setName("Bilim Makon");
            session.persist(company);
            User user = User.builder()
                    .firstName("Bekzod")
                    .lastName("Baratov")
                    .age(26)
                    .birthDate(new BirthDay(LocalDate.of(1996, 11, 12)))
                    .username("bekzod@gmail.com")
                    .company(company)
                    .build();
            session.persist(user);*/

/*User user = session.get(User.class, "1701");
            Company company = user.getCompany();
            session.detach(user);*/