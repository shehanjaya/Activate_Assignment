package com.example.demo.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class RequestDTO {
    public String user;
    public String restaurant;
    public String sessionId;
}
