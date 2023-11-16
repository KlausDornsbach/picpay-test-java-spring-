package com.picpayDemo.picpayDemo.Controllers;

import com.picpayDemo.picpayDemo.DTOs.ExceptionDTO;
import com.picpayDemo.picpayDemo.Exceptions.ExternalAuthorizationFailedException;
import com.picpayDemo.picpayDemo.Exceptions.InvalidOperationException;
import com.picpayDemo.picpayDemo.Exceptions.NotEnoughFundsException;
import com.picpayDemo.picpayDemo.Exceptions.NotificationFailedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.openmbean.KeyAlreadyExistsException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidTransactionSender() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Erro: Vendedor nao pode transferir valores para outras entidades", "400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<ExceptionDTO> handleNotEnoughFundsException() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Erro: Entidade que envia nao tem fundos suficientes", "400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleEntityNotFound() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Erro: Usuario nao encontrado", "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotificationFailedException.class)
    public ResponseEntity<ExceptionDTO> handleNotificationFailedException() {
        System.out.println("could not send the notification"); // log
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Erro: Falha externa ao mandar notificacao", HttpStatus.CREATED.toString()), HttpStatus.CREATED);
    }

    @ExceptionHandler(ExternalAuthorizationFailedException.class)
    public ResponseEntity<ExceptionDTO> handleExternalAuthorizationFailedException() {
        System.out.println("could not authenticate externally"); // log
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Erro: Falha externa ao autenticar", HttpStatus.UNAUTHORIZED.toString()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleKeyAlreadyExistsException() {
        return new ResponseEntity<ExceptionDTO>(new ExceptionDTO("Numero de documento ou email ja foi registrado", HttpStatus.CONFLICT.toString()), HttpStatus.CONFLICT);
    }
}
