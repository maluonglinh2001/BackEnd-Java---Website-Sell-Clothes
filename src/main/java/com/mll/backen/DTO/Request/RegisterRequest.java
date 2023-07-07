package com.mll.backen.DTO.Request;

import com.mll.backen.models.User.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String full_name;

    @Email(message = "Email is not valid!")
    private String email;

    private String password;

    private String address;

    private String birthday;

    private String phone_number;
}
