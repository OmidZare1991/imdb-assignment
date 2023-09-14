package com.imdb.main.security;

import com.imdb.main.domain.Permission;
import com.imdb.main.domain.Role;
import com.imdb.main.domain.Users;
import com.imdb.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername()
                , user.getPassword()
                , user.isActive()
                , true
                , true
                , true
                , getAuthorities(user.getRoles())
        );
    }
    private Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }
    private List<String> getPermissions(final Collection<Role> roles) {

        final List<String> permissionNames = new ArrayList<>();
        final List<Permission> permissions = new ArrayList<>();
        for (final Role role : roles) {
            permissions.addAll(role.getPermissions());
        }
        for (final Permission permission : permissions) {
            permissionNames.add(permission.getName());
        }
        return permissionNames;
    }
    private List<GrantedAuthority> getGrantedAuthorities(final List<String> permissions) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }
}