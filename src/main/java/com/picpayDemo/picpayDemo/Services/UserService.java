package com.picpayDemo.picpayDemo.Services;

import com.picpayDemo.picpayDemo.Domain.User;
import com.picpayDemo.picpayDemo.Domain.UserType;
import com.picpayDemo.picpayDemo.Exceptions.InvalidOperationException;
import com.picpayDemo.picpayDemo.Exceptions.NotEnoughFundsException;
import com.picpayDemo.picpayDemo.Repos.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType().equals(UserType.SELLER)) {
            throw new InvalidOperationException();
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughFundsException();
        }
    }

    public User getUserByDocument(String document) {
        Optional<User> user = userRepo.findUserByDocument(document);
        if (user.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return user.get();
    }

    public void saveUserToDB(User user) {
        try {
            userRepo.save(user);
        } catch (Exception e) {
            throw new KeyAlreadyExistsException("The document number or email is already taken");
        }
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
