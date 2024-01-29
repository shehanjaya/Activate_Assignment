package com.example.demo.util;

import com.example.demo.entity.Restaurant;

import java.util.List;
import java.util.Random;

public class SimpleRandomPickStrategy implements RandomPickStrategy {
    @Override
    public Restaurant pickRandomRestaurant(List<Restaurant> restaurantList) {
        Random random = new Random();
        return restaurantList.get(random.nextInt(restaurantList.size()));
    }
}
