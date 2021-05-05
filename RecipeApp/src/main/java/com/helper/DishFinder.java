package com.helper;

import com.domain.id.DishID;
import com.domain.id.UserID;

public class DishFinder {
    private final String dishName;
    private final UserID uploadedBy;
    private final DishID dishID;
    private final DishPreference dishPreference;

    public DishFinder(String dishName, UserID uploadedBy, DishID dishID, DishPreference dishPreference) {
        this.dishName = dishName;
        this.uploadedBy = uploadedBy;
        this.dishID = dishID;
        this.dishPreference = dishPreference;
    }

    public String getDishName() {
        return dishName;
    }

    public DishPreference getDishPreference() {
        return dishPreference;
    }

    public UserID getUploadedBy() {
        return uploadedBy;
    }

    public DishID getDishID() {
        return dishID;
    }
}
