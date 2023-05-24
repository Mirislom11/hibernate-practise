package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToMany(mappedBy = "chats")
    private Set<User> users = new HashSet<>();
}
