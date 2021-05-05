package com.manager;

import com.domain.id.UserID;

import java.util.Map;

public class ActivityManager {
    private final Map<String, UserID> activeUserMap;

    public ActivityManager(Map<String, UserID> activeUserMap) {
        this.activeUserMap = activeUserMap;
    }

    public boolean addNewActiveUser(String uuid, UserID userID) {
        boolean inserted = false;
        if (!activeUserMap.containsValue(userID)) {
            activeUserMap.put(uuid, userID);
            inserted = true;
        }
        return inserted;
    }
    public boolean isUserAuthenticated(String uuid) {
        return activeUserMap.containsKey(uuid);
    }
}
