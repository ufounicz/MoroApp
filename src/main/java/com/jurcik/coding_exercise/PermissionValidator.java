package com.jurcik.coding_exercise;

import com.jurcik.coding_exercise.util.PermissionLevel;
import com.jurcik.coding_exercise.util.UserRole;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class PermissionValidator {

    public static PermissionLevel checkUserAccess(String accessedUsername) {
        var context = SecurityContextHolder.getContext();
        if (context.getAuthentication() == null || !context.getAuthentication().isAuthenticated()) {
           return PermissionLevel.DENIED;
        }

        if (accessedUsername != null && !accessedUsername.equals(context.getAuthentication().getName())) {
            List<String> authorities = context.getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).toList();
            if (authorities.contains(UserRole.ADMIN.name())) {
                return PermissionLevel.ENABLED;
            } else {
                return PermissionLevel.DENIED;
            }
        }
        return PermissionLevel.ENABLED;
    }
}
