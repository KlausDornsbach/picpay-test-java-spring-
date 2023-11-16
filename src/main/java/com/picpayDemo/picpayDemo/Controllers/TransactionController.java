package com.picpayDemo.picpayDemo.Controllers;

import com.picpayDemo.picpayDemo.DTOs.TransactionDTO;
import com.picpayDemo.picpayDemo.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping
    ResponseEntity<TransactionDTO> postTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        TransactionDTO transaction = transactionService.transact(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
