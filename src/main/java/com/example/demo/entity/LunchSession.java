package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


import java.util.List;

//@Entity
@Data
public class LunchSession {
    private Long sessionId;
    private String initiator;
    private List<User> userList;
    private List<Restaurant> restaurantList;
    private boolean endedStatus = false;
    private Restaurant selectedRestaurant;

    public LunchSession(String initiator) {
        this.initiator = initiator;
    }

    public LunchSession(Long sessionId, String initiator) {
        this.sessionId = sessionId;
        this.initiator = initiator;
    }

    public LunchSession() {

    }

    public static class SessionBuilder {

        private Long sessionId;
        private String initiator;

        private List<User> userList;
        private List<Restaurant> restaurantList;

        public SessionBuilder(String initiator) {
            this.initiator = initiator;
        }

        public SessionBuilder(String initiator, Long sessionId) {
            this.sessionId = sessionId;
            this.initiator = initiator;
        }

        public SessionBuilder setUserList(List<User> userList) {
            this.userList = userList;
            return this;
        }

        public SessionBuilder setRestaurantList(List<Restaurant> restaurantList) {
            this.restaurantList = restaurantList;
            return this;
        }

        public LunchSession build() {
            return new LunchSession(sessionId, initiator);
        }
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public boolean isEndedStatus() {
        return endedStatus;
    }

    public void setEndedStatus(boolean endedStatus) {
        this.endedStatus = endedStatus;
    }

    public Restaurant getSelectedRestaurant() {
        return selectedRestaurant;
    }

    public void setSelectedRestaurant(Restaurant selectedRestaurant) {
        this.selectedRestaurant = selectedRestaurant;
    }

}
