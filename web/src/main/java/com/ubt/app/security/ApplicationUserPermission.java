package com.ubt.app.security;

public enum ApplicationUserPermission {
    // shto vetura

    SERVER_READ("vehicle:read"),
    SERVER_WRITE("vehicle:write"),
    ADMIN_SERVER_READ("server:read"),
    ADMIN_SERVER_WRITE("server:write");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
