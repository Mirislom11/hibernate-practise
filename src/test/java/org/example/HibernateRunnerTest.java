package org.example;

import org.apache.commons.compress.harmony.unpack200.bytecode.CPMember;
import org.example.customType.BirthDay;
import org.example.entity.*;
import org.example.entity.enumerations.Language;
import org.example.util.HibernateTestUtil;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaListJoin;
import org.hibernate.query.criteria.JpaRoot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /*@Test
    public void testTestPerClass() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = Company.builder()
                    .name("Google")
                    .build();
            session.persist(company);
            session.flush();

            Programmer programmer = Programmer.builder()
                    .username("ivan@gmail.com")
                    .language(Language.JAVA)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .birthDate(new BirthDay(LocalDate.of(2003, 6, 11)))
                    .company(company)
                    .build();

            Manager manager = Manager.builder()
                    .username("sergey@gmail.com")
                    .projectName("CEO")
                    .firstName("Sergey")
                    .lastName("Sergeev")
                    .birthDate(new BirthDay(LocalDate.of(2000, 6, 11)))
                    .company(company)
                    .build();
            company.addUserToCompany(programmer);
            company.addUserToCompany(manager);
            session.persist(programmer);
            session.persist(manager);

            session.clear();

            var company2 = session.get(Company.class, company.getId());
            System.out.println(company2.getUsers());
            session.getTransaction().commit();
        }
    }*/

    @BeforeAll
    public static void executeDatas() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Company company = createCompany();
            session.persist(company);
            List<User> users = createTestUsers();
            User user  = users.get(0);
            user.setCompany(company);
            users.forEach(session::persist);

        }
    }

    @Test
    public void testTestContainer() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<LocaleInfo> localeInfos = new ArrayList<>();
            localeInfos.add(LocaleInfo.of("uz", "Google UZB"));
            Company company = Company.builder()
                    .name("Google")
                    .locales(localeInfos)
                    .build();
            session.persist(company);
            session.getTransaction().commit();
        }
    }

    @Test
    public void testCriteriaApi() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();


            var cb = session.getCriteriaBuilder();

            var criteria = cb.createQuery(User.class);
            var user = criteria.from(User.class);
            String firsName = "Azizxon";
            criteria.select(user).where(cb.equal(user.get("firstName"), firsName));
            session.createQuery(criteria)
                    .list();
        }
    }

    @Test
    public void findLimitedUsersOrderedByBirthday() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();

            var criteria = criteriaBuilder.createQuery(User.class);
            var rootUser = criteria.from(User.class);
            criteria.select(rootUser).orderBy(criteriaBuilder.asc(rootUser.get(User_.age)));
            System.out.println(session.createQuery(criteria)
                    .list());
        }
    }

    @Test
    public void findAllByCompanyName() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
            JpaRoot<Company> rootCompany = criteria.from(Company.class);
            JpaListJoin<Company, User> users = rootCompany.join(Company_.users);
            String companyName = "Google";
            criteria.select(users).where(
                    criteriaBuilder.equal(rootCompany.get(Company_.NAME), companyName)
            );
            System.out.println(session.createQuery(criteria).list());
        }
    }

    private static List<User> createTestUsers() {


        User user1 = User.builder()
                .username("mirislom@gmail.com")
                .firstName("Mirislom")
                .lastName("Zoirov")
                .age(20)
                .birthDate(new BirthDay(LocalDate.of(2003, 5, 6)))
                .build();

        User user2 = User.builder()
                .username("aziz@gmail.com")
                .firstName("Azizxon")
                .lastName("Imomnazarov")
                .age(22)
                .birthDate(new BirthDay(LocalDate.of(2001, 5, 6)))
                .build();

        User user3 = User.builder()
                .username("shadiyar@gmail.com")
                .firstName("Shadiyar")
                .lastName("Kidirayev")
                .age(21)
                .birthDate(new BirthDay(LocalDate.of(2002, 5, 6)))
                .build();
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

    private static Company createCompany () {
        return Company.builder()
                .name("Google")
                .build();
    }

    /*@Test
    void checkManyToManyV2() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
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
    }*/

    /*@Test
    void testLocaleInfo() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Company company = session.get(Company.class, "1651");
            company.getLocales().add(LocaleInfo.of("ru", "Описание на русском"));
            company.getLocales().add(LocaleInfo.of("en", "English description"));
            session.getTransaction().commit();
        }
    }*/

    /*@Test
    void testMapUserCompany() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Company company = session.get(Company.class, "1651");
            company.getUsers().forEach((k, v) -> System.out.println("username: " + k + " value: " + v));

            session.getTransaction().commit();
        }
    }*/
}
