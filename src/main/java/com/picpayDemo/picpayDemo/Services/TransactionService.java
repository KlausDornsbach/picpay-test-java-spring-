package com.picpayDemo.picpayDemo.Services;

import com.picpayDemo.picpayDemo.DTOs.TransactionDTO;
import com.picpayDemo.picpayDemo.Domain.Transaction;
import com.picpayDemo.picpayDemo.Domain.User;
import com.picpayDemo.picpayDemo.Exceptions.ExternalAuthorizationFailedException;
import com.picpayDemo.picpayDemo.Repos.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public TransactionDTO transact(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.getUserByDocument(transactionDTO.senderDocument());
        User receiver = userService.getUserByDocument(transactionDTO.receiverDocument());

        userService.validateTransaction(sender, transactionDTO.amount());


        if (!authorizeTransaction()) {
            throw new Exception("Error: external authorization of transaction failed");
        };

        Transaction transaction = createTransaction(sender, receiver, transactionDTO.amount());
        sender.setBalance(sender.getBalance().subtract(transactionDTO.amount()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.amount()));

        saveTransactionToDB(transaction);
        userService.saveUserToDB(sender);
        userService.saveUserToDB(receiver);

        notificationService.notify(transaction);

        return transactionDTO;
    }

    boolean authorizeTransaction() throws ExternalAuthorizationFailedException {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().get("message").equals("Autorizado");
            }
        } catch (Exception e) {
            throw new ExternalAuthorizationFailedException();
        }
        return false;
    }

    public Transaction createTransaction(User sender, User receiver, BigDecimal value) {
        Transaction t = new Transaction(sender, receiver, value);
        return t;
    }

    public void saveTransactionToDB(Transaction transaction) {
        transactionRepo.save(transaction);
    }
}
