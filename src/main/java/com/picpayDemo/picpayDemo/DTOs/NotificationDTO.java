package com.picpayDemo.picpayDemo.DTOs;

import java.math.BigDecimal;

public record NotificationDTO(String receiver, BigDecimal amount) {
}
