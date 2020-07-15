package com.ubt.app.controller;

import com.ubt.app.util.Utils;
import com.ubt.model.Driver;
import com.ubt.model.Server;
import com.ubt.service.DriverService;
import com.ubt.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class OpenRequestsController {

    public static final Logger logger = LoggerFactory.getLogger(OpenRequestsController.class);

    @Autowired
    private ServerService serverService;

    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin("http://localhost:3000/login")
    public void login() {
        // Login default by spring security /login endpoint
    }

    @PostMapping("/createDriver")
    public ResponseEntity<?> createUser(@RequestBody Driver driver, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating driver: {}", driver);

        if (driverService.getById(driver.getId()) != null) {
            logger.error("driver with id:" + driver.getId() + " already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create driver with id:" + driver.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        driverService.save(driver);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/driver/{id}").buildAndExpand(driver.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/createServer", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Server server, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating Server: {}", server);

        if (serverService.getById(server.getId()) != null) {
            logger.error("Server with id:" + server.getId() + " already exist.");
            return new ResponseEntity<>(new Utils
                    ("Unable to create server with id:" + server.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        serverService.save(server);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/server/{id}").buildAndExpand(server.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
