package com.domain.id;

import java.util.Objects;

public class UserID {
    private final String userId;

    public UserID(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID1 = (UserID) o;
        return getUserId().equals(userID1.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
