package com.ubt.app.service;

import com.ubt.app.security.UserPrincipal;
import com.ubt.model.Server;
import com.ubt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Server server = userRepository.findByUsername(username);
        if (server == null) {
            throw new UsernameNotFoundException("Server 404");
        }
        return new UserPrincipal(server);
    }
}
