package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
@Table(name = "profile", schema = "public")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "sequence_generator")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "street")
    private String street;
    @Column(name = "language")
    private String language;

    public void setUser(User user) {
        user.setProfile(this);
        this.user = user;
    }
}
