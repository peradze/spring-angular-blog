package com.tornikeperadze.blog.bootstrap;

import com.tornikeperadze.blog.model.Role;
import com.tornikeperadze.blog.model.User;
import com.tornikeperadze.blog.repository.RoleRepository;
import com.tornikeperadze.blog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.tornikeperadze.blog.model.Role.Roles.ROLE_ADMIN;
import static com.tornikeperadze.blog.model.Role.Roles.ROLE_USER;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role adminRole = new Role(ROLE_ADMIN);
            Role userRole = new Role(ROLE_USER);
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            User user = User.builder()
                    .email("admin@test.com")
                    .password(passwordEncoder.encode("secret"))
                    .isAccExpired(false)
                    .isAccLocked(false)
                    .isCredExpired(false)
                    .isEnabled(true)
                    .roles(Set.of(adminRole, userRole))
                    .build();
            userRepository.save(user);
        }

    }
}
