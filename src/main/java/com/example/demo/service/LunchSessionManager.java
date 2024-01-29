package com.example.demo.service;

import com.example.demo.Exception.SessionExistException;
import com.example.demo.Exception.SessionNotFoundException;
import com.example.demo.Exception.UnSupportedAccessException;
import com.example.demo.entity.LunchSession;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.User;
import com.example.demo.util.Observer;
import com.example.demo.util.RandomPickStrategy;
import com.example.demo.util.Subject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LunchSessionManager implements Subject {
    private static final LunchSessionManager INSTANCE = new LunchSessionManager();

//    @Autowired
//    SessionManagerRepository sessionManagerRepository;

    LunchSession lunchSession = new LunchSession();
    boolean isInitiated = false;
    private List<Observer> observers = new ArrayList<>();
    private RandomPickStrategy randomPickStrategy;

    // Private constructor to prevent instantiation
    private LunchSessionManager() {
    }

    public static LunchSessionManager getInstance() {
        return INSTANCE;
    }

    // Setter for the strategy
    public void setRandomPickStrategy(RandomPickStrategy randomPickStrategy) {
        this.randomPickStrategy = randomPickStrategy;
    }

    public LunchSession createSession(String initiator) throws SessionExistException {
        if (!isInitiated) {
            // Generate a unique long value using UUID
            long uniqueLong = System.currentTimeMillis();
            lunchSession = new LunchSession.SessionBuilder(initiator, uniqueLong).build();
            isInitiated = true;
            System.out.println(lunchSession);
        } else {
            throw new SessionExistException("Session already created!.");
        }
        return lunchSession;
    }

    public LunchSession joinSession(String sessionId, String joiner) throws SessionNotFoundException {
        if (isInitiated && lunchSession.getSessionId().equals(sessionId)) {
            User user = new User(joiner);
            List<User> list = lunchSession.getUserList();
            list.add(user);
            lunchSession.setUserList(list);
            //joined the oberserver list
            addObserver(user);
            System.out.println("Joined list" + list);
        } else {
            throw new SessionNotFoundException("Session not found! Session Id:" + sessionId);
        }
        return lunchSession;
    }

    public LunchSession submitRestaurant(String sessionId, String user, String restaurant) throws SessionNotFoundException {
        if (isInitiated && lunchSession.getSessionId().equals("sessionId")) {
            List<Restaurant> list = lunchSession.getRestaurantList();
            list.add(new Restaurant(user, restaurant));
            lunchSession.setRestaurantList(list);
            //notify the other users
            notifyObservers(restaurant);
            System.out.println("Submitted:" + list);
        } else {
            throw new SessionNotFoundException("Session not found! Session Id:" + sessionId);
        }
        return lunchSession;
    }

    public Restaurant endSession(String sessionId, String initiator) throws UnSupportedAccessException, IllegalStateException {
        if (isInitiated && lunchSession.getInitiator().equals(initiator)) {
            isInitiated = false;
            lunchSession = null;
            if (randomPickStrategy != null) {
                Restaurant res = randomPickStrategy.pickRandomRestaurant(lunchSession.getRestaurantList());
                System.out.println("Ended :" + res);
                return res;
            } else {
                throw new IllegalStateException("RandomPickStrategy not set.");
            }
        } else {
            throw new UnSupportedAccessException("Can't end the session since the initiator is different!");
        }
    }

    public void explicitlyendSession() {
        isInitiated = false;
        lunchSession = null;
        System.out.println("Cleared:" + lunchSession + " isInitiated:" + isInitiated);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String restaurantName) {
        for (Observer observer : observers) {
            observer.pushNotification(restaurantName);
        }
    }
}
