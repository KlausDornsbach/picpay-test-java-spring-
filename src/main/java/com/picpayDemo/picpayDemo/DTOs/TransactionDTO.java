package com.picpayDemo.picpayDemo.DTOs;

import java.math.BigDecimal;

public record TransactionDTO(String senderDocument, String receiverDocument, BigDecimal amount) { }
