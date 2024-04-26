package com.device.bazzar.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String userId;
    @Size(min = 3, max = 20, message = "name should have atleast 3 character and max 20 character")
    private String userName;
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+[a-z]{2,5}$", message = "Invalid Email")
    private String userEmail;
    @NotBlank(message = "Password is required!!")
    private String userPassword;
    @Size(min = 4, max = 6, message = "Gender should have atleast 4 character and max 6 character")
    private String userGender;
    @NotBlank(message = "Please enter About")
    private String userAbout;
    private String userImageName;
}
