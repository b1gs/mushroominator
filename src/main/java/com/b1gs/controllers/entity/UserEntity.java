package com.b1gs.controllers.entity;

import com.b1gs.controllers.converter.RoleListConverter;
import com.b1gs.controllers.service.security.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "user", schema = "mushroominator")
@Data
@ToString
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_firstname")
    private String firstName;

    @Column(name = "u_lastname")
    private String lastName;

    @Column(name = "u_password")
    private String password;

    @Convert(converter = RoleListConverter.class)
    @Column(name = "u_roles")
    private List<Role> roles;

}
