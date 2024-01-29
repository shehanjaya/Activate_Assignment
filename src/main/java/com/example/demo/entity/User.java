package com.example.demo.entity;

import com.example.demo.util.Observer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
//@Table(name="USERS")
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;

    public User(String initiator) {
    }

    @Override
    public void pushNotification(String restaurantName) {
        System.out.println("Send the message to user:" + userName + ", new restaurant added:" + restaurantName);
    }
}
