package com.imdb.main.service;

import com.imdb.main.domain.Permission;
import com.imdb.main.domain.Role;
import com.imdb.main.domain.Users;
import com.imdb.main.repository.PermissionRepository;
import com.imdb.main.repository.RoleRepository;
import com.imdb.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.imdb.main.util.AdminDummyData.ADMIN_PASSWORD;
import static com.imdb.main.util.AdminDummyData.ADMIN_USERNAME;
import static com.imdb.main.util.PrivilegeTypes.*;
import static com.imdb.main.util.RoleTypes.ROLE_ADMIN;
import static com.imdb.main.util.RoleTypes.ROLE_USER;


@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        final Permission readPrivilege = createPrivilegeIfNotFound(READ_PRIVILEGE.name());
        final Permission writePrivilege = createPrivilegeIfNotFound(WRITE_PRIVILEGE.name());
        final Permission passwordPrivilege = createPrivilegeIfNotFound(CHANGE_PASSWORD_PRIVILEGE.name());

        final Set<Permission> adminPrivileges = new HashSet<>(Arrays.asList(readPrivilege, writePrivilege, passwordPrivilege));
        final Set<Permission> userPrivileges = new HashSet<>(Arrays.asList(readPrivilege, passwordPrivilege));

        final Role adminRole = createRoleIfNotFound(ROLE_ADMIN.name(), adminPrivileges);

        createRoleIfNotFound(ROLE_USER.name(), userPrivileges);

        createUserIfNotFound(new HashSet<>(Collections.singletonList(adminRole)));

        alreadySetup = true;
    }

    @Transactional
    public Permission createPrivilegeIfNotFound(final String name) {
        Permission privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Permission(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(final String name, final Set<Permission> permissions) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPermissions(permissions);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    public void createUserIfNotFound(final Set<Role> roles) {
        Users user = userRepository.findByUsername(ADMIN_USERNAME.getValue());
        if (user == null) {
            user = new Users();
            user.setUsername(ADMIN_USERNAME.getValue());
            user.setPassword(passwordEncoder.encode(ADMIN_PASSWORD.getValue()));
            user.setActive(true);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}