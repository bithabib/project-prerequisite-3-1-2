package habsida.spring.boot_security.demo.configs;

import habsida.spring.boot_security.demo.models.Role;
import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.repositories.RoleRepository;
import habsida.spring.boot_security.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepo,
                                      RoleRepository roleRepo,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Create roles if not exist
            Role roleAdmin = roleRepo.findByName("ROLE_ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role("ROLE_ADMIN")));
            Role roleUser = roleRepo.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepo.save(new Role("ROLE_USER")));

            // Create admin user if not exist
            if (!userRepo.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                admin.setRoles(Set.of(roleAdmin, roleUser));
                userRepo.save(admin);
            }

            // Create normal user if not exist
            if (!userRepo.existsByUsername("user")) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("12345"));
                user.setRoles(Set.of(roleUser));
                userRepo.save(user);
            }
        };
    }
}
