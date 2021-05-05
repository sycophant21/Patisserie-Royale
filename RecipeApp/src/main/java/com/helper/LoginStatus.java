package com.helper;

import java.util.UUID;

public class LoginStatus {
    private final Status status;
    private final UUID uuid;

    public LoginStatus(Status status, UUID uuid) {
        this.status = status;
        this.uuid = uuid;
    }

    public Status getStatus() {
        return status;
    }

    public UUID getUuid() {
        return uuid;
    }
}
