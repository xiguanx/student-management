package net.javaguides.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid email format")
    private String email;
}
