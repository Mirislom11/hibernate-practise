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
@Table(name = "employee_task", schema = "public")

public class EmployeeTask {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName="sequence_generator")
    private Long id;
    @Column(name = "task_name")
    private  String taskName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
