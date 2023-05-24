package org.example.service;

import org.example.customType.BirthDay;
import org.example.entity.Company;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class CompanyService {

    public static void main(String[] args) {
        createCompanyWithUsers();
    }

    public static void createCompanyWithUsers () {
        Company company = Company.builder()
                .name("Brio Group")
                .build();
        User user1 = User.builder()
                .age(20)
                .firstName("Eshmat")
                .lastName("Eshmatov")
                .username("eshmat@gmail.com")
                .birthDate(new BirthDay(LocalDate.of(2002, 11, 6)))
                .build();
        User user2 = User.builder()
                .age(21)
                .firstName("Toshmat")
                .lastName("Toshmatov")
                .username("toshmat@gmail.com")
                .birthDate(new BirthDay(LocalDate.of(2002, 11, 6)))
                .build();
        company.addUsersToCompany(List.of(user1, user2));
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(company);
            transaction.commit();
        }
    }
}
