package com.example.demo.util;
import com.example.demo.entity.Restaurant;

import java.util.List;

public interface RandomPickStrategy {
    Restaurant pickRandomRestaurant(List<Restaurant> restaurantList);
}
