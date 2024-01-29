package com.example.demo.service;

import com.example.demo.Exception.SessionExistException;
import com.example.demo.Exception.SessionNotFoundException;
import com.example.demo.Exception.UnSupportedAccessException;
import com.example.demo.entity.LunchSession;
import com.example.demo.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public interface SessionManagerService {
    Long createSession(String initiator) throws SessionExistException;
    LunchSession joinSession(String sessionId, String user) throws SessionNotFoundException;
    LunchSession submitRestaurant(String sessionId, String user, String restaurant) throws SessionNotFoundException;
    Restaurant endSession(String sessionId, String user) throws UnSupportedAccessException;
    void explicitlyendSession();
}
