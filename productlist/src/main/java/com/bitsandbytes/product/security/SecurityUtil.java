package com.bitsandbytes.product.security;

import com.bitsandbytes.product.exception.RoleBasedAuthorizationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static void checkUserHasRole(String role){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority(role))){
            String roleName = role.replace("ROLE_", "");
            throw new RoleBasedAuthorizationException("Access denied: Only "+roleName+"s can perform this action.");
        }
    }
}
