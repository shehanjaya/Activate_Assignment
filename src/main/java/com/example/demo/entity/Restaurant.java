package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
@Data
//@Table(name="RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String restaurantName;
    private User user;

    public Restaurant(String user, String restaurant) {
    }
}
