package com.jurcik.coding_exercise;

import com.jurcik.coding_exercise.util.PermissionLevel;
import org.springframework.security.core.context.SecurityContextHolder;

public class PermissionValidator {

    public static PermissionLevel checkUserAccess(String accessedUsername) {
        var context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null || !context.getAuthentication().isAuthenticated()) {
           return PermissionLevel.DENIED;
        }

        if (accessedUsername != null && !accessedUsername.equals(context.getAuthentication().getName())) {
            return PermissionLevel.DENIED;
        }

        return PermissionLevel.ENABLED;
    }
}
