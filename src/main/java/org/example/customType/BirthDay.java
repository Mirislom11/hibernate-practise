package org.example.customType;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BirthDay {
    private LocalDate birthDate;

}
