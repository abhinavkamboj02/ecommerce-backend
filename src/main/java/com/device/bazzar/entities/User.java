package com.device.bazzar.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.ArrayList;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User_Table")
public class User {
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private  Cart cart;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.userPassword;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.userEmail;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
