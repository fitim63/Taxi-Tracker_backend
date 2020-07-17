package com.ubt.unitTest;

import com.ubt.model.Server;
import com.ubt.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Server> getAll() {
        return serverRepository.findAll();
    }

    public Server getById(int id) {
        return serverRepository.findById(id);
    }

    public void deleteById(int id) {
        serverRepository.deleteById(id);
    }

    public Server getByUsername(String username) {
        return serverRepository.findByUsername(username);
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
        server.setPassword(u.getPassword());
        server.setPassword(passwordEncoder.encode(u.getPassword()));
        server.setUsername(u.getUsername());
        serverRepository.save(server);
    }


}
