package net.santhosh.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "User DTO Model information")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @Schema(description = "user first name")
    // User first Name should not be null or empty
    @NotEmpty(message = "User  first name should not be empty or null")
    private String firstName;

    @Schema(description = "user last name")
    // User last Name should not be null or empty
    @NotEmpty(message = "User last name should not be empty or null")
    private String lastName;

    @Schema(description = "user email")
    // User email should not be null or empty
    // Email address should be valid
    @NotEmpty(message = "User email should not be empty or null")
    @Email(message = "email address should be valid")
    private String email;
}
