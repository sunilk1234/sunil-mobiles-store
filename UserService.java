package com.example.ecom.service;

import com.example.ecom.model.User;
import com.example.ecom.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public boolean emailExists(String email) { return repo.existsByEmail(email); }

    public User register(String name, String email, String rawPassword) {
        String hash = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPasswordHash(hash);
        u.setRole("USER");
        return repo.save(u);
    }

    public User authenticate(String email, String rawPassword) {
        return repo.findByEmail(email)
                .filter(u -> BCrypt.checkpw(rawPassword, u.getPasswordHash()))
                .orElse(null);
    }
}
