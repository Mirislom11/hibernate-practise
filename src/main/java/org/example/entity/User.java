package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import org.example.converter.BirthDateConverter;
import org.example.customType.BirthDay;
import org.example.customType.MyJson;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Convert(converter = BirthDateConverter.class)
    @Column(name = "birth_date")
    private BirthDay birthDate;
    @Column(name = "age")
    private Integer age;

    @Column(name = "info")
    @JdbcTypeCode(SqlTypes.JSON)
    private MyJson info;
}
