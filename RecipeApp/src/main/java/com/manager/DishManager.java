package com.manager;

import com.dao.DishDAO;
import com.domain.classes.Dish;
import com.helper.DishFinder;

import java.util.ArrayList;
import java.util.List;

public class DishManager {
    private final DishDAO dishDAO;

    public DishManager(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public List<Dish> findDishes(DishFinder dishFinder) {
        List<Dish> dishes = new ArrayList<>();
        if (dishFinder.getDishID() != null) {
            dishes.add(dishDAO.getDishesFromDishID(dishFinder.getDishID()));
        }
        else if (dishFinder.getDishName() != null) {
            dishes.addAll(dishDAO.getDishesFromName(dishFinder.getDishName()));
        }
        else if (dishFinder.getDishPreference() != null) {
            dishes.addAll(dishDAO.getDishesFromIngredients(dishFinder.getDishPreference()));
        }
        else if (dishFinder.getUploadedBy() != null) {
            dishes.addAll(dishDAO.getDishesFromUserID(dishFinder.getUploadedBy()));
        }
        return dishes;
    }

    public boolean createDish(Dish dish) {
        return dishDAO.createDish(dish);
    }
}
