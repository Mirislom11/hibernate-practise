package org.example.entity;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Manager extends User{
    private String projectName;
}
