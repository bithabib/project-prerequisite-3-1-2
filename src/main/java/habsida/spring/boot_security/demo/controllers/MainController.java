package habsida.spring.boot_security.demo.controllers;

import habsida.spring.boot_security.demo.models.Role;
import habsida.spring.boot_security.demo.models.User;
import habsida.spring.boot_security.demo.repositories.RoleRepository;
import habsida.spring.boot_security.demo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        if (!userRepo.existsByUsername("admin")) {
            Role roleAdmin = roleRepo.save(new Role("ROLE_ADMIN"));
            Role roleUser = roleRepo.save(new Role("ROLE_USER"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("12345"));
            admin.setRoles(Set.of(roleAdmin, roleUser));
            userRepo.save(admin);
        }
        return "index"; // maps to src/main/resources/templates/index.html
    }

    @GetMapping("/index")
    public String index(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        if (!userRepo.existsByUsername("admin")) {
            Role roleAdmin = roleRepo.save(new Role("ROLE_ADMIN"));
            Role roleUser = roleRepo.save(new Role("ROLE_USER"));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("12345"));
            admin.setRoles(Set.of(roleAdmin, roleUser));
            userRepo.save(admin);
        }
        return "index"; // maps to src/main/resources/templates/index.html
    }

    @GetMapping("/user")
    public String userPage() {
        return "user"; // maps to src/main/resources/templates/user.html
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // maps to src/main/resources/templates/admin.html
    }
}
