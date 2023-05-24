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
@Table(name = "employee", schema = "public")
public class Employee {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName="sequence_generator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "employee")
    private EmployeeTask employeeTask;
}
