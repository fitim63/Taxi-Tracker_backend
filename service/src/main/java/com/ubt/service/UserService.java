package com.ubt.service;

import com.ubt.model.Server;
import com.ubt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Server> getAll() {
        return userRepository.findAll();
    }

    public Server getById(int id) {
        return userRepository.findById(id);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public Server getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(Server u) {
        Server server = new Server();
        if (u.getUuid() == null) {
            u.setUuid(UUID.randomUUID());
        }
        server.setUuid(u.getUuid());
        server.setFirstName(u.getFirstName());
        server.setLastName(u.getLastName());
        server.setEmail(u.getEmail());
        server.setPassword(passwordEncoder.encode(u.getPassword()));
        server.setUsername(u.getUsername());
        userRepository.save(server);
    }


}
