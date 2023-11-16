package com.picpayDemo.picpayDemo.Services;

import com.picpayDemo.picpayDemo.DTOs.NotificationDTO;
import com.picpayDemo.picpayDemo.Domain.Transaction;
import com.picpayDemo.picpayDemo.Exceptions.NotificationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {
    @Autowired
    RestTemplate restTemplate;

    public void notify(Transaction transaction) throws Exception {
        NotificationDTO notification = new NotificationDTO(transaction.getReceiver().getDocument(), transaction.getAmount());

        ResponseEntity<Map> postNotificationResponse = restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notification ,Map.class);
        if (!postNotificationResponse.getStatusCode().is2xxSuccessful() || !postNotificationResponse.getBody().get("message").equals(true)) {
            throw new NotificationFailedException();
        }
    }
}
