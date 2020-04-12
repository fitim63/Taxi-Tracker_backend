package com.ubt.app.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.ubt.app.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    SERVER(Sets.newHashSet(SERVER_READ, SERVER_WRITE)),
    ADMIN_SERVER(Sets.newHashSet(SERVER_READ, SERVER_WRITE, ADMIN_SERVER_READ, ADMIN_SERVER_WRITE ));

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    private final Set<ApplicationUserPermission> permissions;


}
