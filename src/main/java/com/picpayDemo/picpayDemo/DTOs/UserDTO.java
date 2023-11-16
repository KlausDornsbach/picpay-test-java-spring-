package com.picpayDemo.picpayDemo.DTOs;

import com.picpayDemo.picpayDemo.Domain.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String email, String password, UserType userType, String document, BigDecimal balance) {
}
