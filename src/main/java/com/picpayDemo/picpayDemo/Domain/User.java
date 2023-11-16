package com.picpayDemo.picpayDemo.Domain;

import com.picpayDemo.picpayDemo.DTOs.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="users")
@Table(name="users")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String document;

    private BigDecimal balance;

    private Enum<UserType> userType;

    public User(UserDTO userDTO){
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.document = userDTO.document();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
        this.password = userDTO.password();
    }
}
