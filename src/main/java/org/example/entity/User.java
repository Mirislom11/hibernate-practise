package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import org.example.converter.BirthDateConverter;
import org.example.customType.BirthDay;
import org.example.customType.MyJson;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"profile", "company", "userChats"})
@EqualsAndHashCode(of = {"username", "firstName", "lastName", "birthDate", "age", "info", })
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    /*@ManyToMany()
    @JoinTable(
            name = "users_chat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private Set<Chat> chats = new HashSet<>();*/

   @Builder.Default
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   private List<UserChat> userChats = new ArrayList<>();


    /*public void addChat(Chat chat) {
        chats.add(chat);
        if (Objects.isNull(chat.getUsers())){
            Set<User> users = new HashSet<>();
            users.add(this);
            chat.setUsers(users);
        }else {
            chat.getUsers().add(this);
        }
    }*/

}
