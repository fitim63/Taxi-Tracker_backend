package com.ubt.app.service;

import com.ubt.app.security.DriverPrincipal;
import com.ubt.app.security.ServerPrincipal;
import com.ubt.app.util.Utils;
import com.ubt.model.Driver;
import com.ubt.model.Server;
import com.ubt.repository.DriverRepository;
import com.ubt.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ServerRepository userRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String lastDigits = username.substring(username.length() - 3);
        Server server;
        Driver driver;

        if (username.startsWith("+383") && Utils.isNumberic(lastDigits)) {
            driver = driverRepository.findByUsername(username);
            if (driver == null) {
                throw new UsernameNotFoundException("Server 404");
            }
            return new DriverPrincipal(driver);
        }
        server = userRepository.findByUsername(username);
        if (server == null) {
            throw new UsernameNotFoundException("Server 404");
        }
        return new ServerPrincipal(server);
    }
}
