package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.example.converter.BirthDateConverter;
import org.example.customType.BirthDay;
import org.example.customType.MyJson;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            /*MyJson myJson = new MyJson();
            myJson.setLongProp(1l);
            myJson.setStringProp("Mirislom");
            User user = User.builder()
                    .age(20)
                    .username("@Mirislom_09")
                    .firstName("Mirislom")
                    .lastName("Zoirov")
                    .birthDate(new BirthDay(LocalDate.of(2003, 11, 6)))
//                    .info(myJson)
                    .build();*/
            User user1 = session.get(User.class, "@Mirislom_01");
            User user2 = session.get(User.class, "@Mirislom_01");
            transaction.commit();

            /*String sql = """
                    insert into\s
                    %s
                    (%s)
                    values
                    (%s)
                    """;
            String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                    .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                    .orElse(user.getClass().getName());

            Class<?>[] fields = user.getClass().getDeclaredClasses();

            String columnNames = Arrays.stream(fields)
                    .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                            .map(Column::name)
                            .orElse(field.getName()))
                    .collect(Collectors.joining(", "));

            String columnValues = Arrays.stream(fields)
                    .map(field -> "?")
                    .collect(Collectors.joining(", "));

            System.out.println(sql.formatted(tableName, columnNames, columnValues));*/
        }
    }
}