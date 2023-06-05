package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "company", schema = "public")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy(value = "username DESC, lastName ASC")
    private List<User> users;*/
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @OrderBy(value = "username DESC, lastName ASC")
    private List<User> users = new ArrayList<>();
    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locale", joinColumns = @JoinColumn(name = "company_id"))
    private List<LocaleInfo> locales = new ArrayList<>();

    /*public void addUsersToCompany(List<User> users) {
        Map<String, List<User>> userMapList = users.stream()
                .collect(Collectors.groupingBy(User::getUsername));
        Map<String, User> userMap = new HashMap<>();
        for (Map.Entry<String, List<User>> stringListEntry : userMapList.entrySet()) {
            userMap.put(stringListEntry.getKey(), stringListEntry.getValue().get(0));
        }
        this.setUsers(userMap);
        users.forEach(user -> user.setCompany(this));
    }*/

    public void addUserToCompany(User user) {
        this.getUsers().add(user);
    }
}
