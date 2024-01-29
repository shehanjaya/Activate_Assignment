package com.example.demo.serviceImpl;

import com.example.demo.Exception.SessionExistException;
import com.example.demo.Exception.SessionNotFoundException;
import com.example.demo.Exception.UnSupportedAccessException;
import com.example.demo.entity.LunchSession;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.LunchSessionManager;
import com.example.demo.service.SessionManagerService;
import com.example.demo.util.SimpleRandomPickStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionManagerServiceImpl implements SessionManagerService {
    LunchSessionManager lunchSessionManager = LunchSessionManager.getInstance();

    @Override
    public Long createSession(String initiator) throws SessionExistException {
        LunchSession lunchSession = lunchSessionManager.createSession(initiator);
        return lunchSession.getSessionId();
    }

    @Override
    public LunchSession joinSession(String sessionId, String user) throws SessionNotFoundException {
        return lunchSessionManager.joinSession(sessionId, user);
    }

    @Override
    public LunchSession submitRestaurant(String sessionId, String user, String restaurant) throws SessionNotFoundException {
        return lunchSessionManager.submitRestaurant(sessionId, user, restaurant);
    }

    @Override
    public Restaurant endSession(String sessionId, String user) throws IllegalStateException, UnSupportedAccessException {
        lunchSessionManager.setRandomPickStrategy(new SimpleRandomPickStrategy());
        return lunchSessionManager.endSession(sessionId, user);
    }

    @Override
    public void explicitlyendSession() {
        lunchSessionManager.explicitlyendSession();
    }
}
