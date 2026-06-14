package com.chaitu.fixmyride.model;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MechanicPrincipal implements UserDetails {

    private Mechanic mechanic;

    public MechanicPrincipal(Mechanic mechanic){
        this.mechanic = mechanic;
    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {

        return Collections.singleton(
                new SimpleGrantedAuthority(
                        mechanic.getRole()
                )
        );
    }

    @Override
    public String getPassword() {
        return mechanic.getPassword();
    }

    @Override
    public String getUsername() {
        return mechanic.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
