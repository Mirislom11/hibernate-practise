package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import org.example.entity.User;
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
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = User.builder()
                    .age(20)
                    .username("@Mirislom_01")
                    .firstName("Mirislom")
                    .lastName("Zoirov")
                    .birthDate(LocalDate.of(2003, 11, 6))
                    .build();
            /*session.save(user);*/
            transaction.commit();

            String sql = """
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

            System.out.println(sql.formatted(tableName, columnNames, columnValues));
        }
    }
}