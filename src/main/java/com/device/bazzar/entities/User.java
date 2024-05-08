package com.device.bazzar.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_Table")
public class User{
    @Id
    @Column(name = "User_Id")
    private String userId;
    @Column(name = "User_Name", nullable = false)
    private String userName;
    @Column(name = "User_Email", nullable = false, unique = true)
    private String userEmail;
    @Column(name = "User_Password")
    private String userPassword;
    @Column(name = "User_Gender")
    private String userGender;
    @Column(name = "User_About")
    private String userAbout;
    @Column(name = "User_ImageName")
    private String userImageName;
    private String userRole;

}
