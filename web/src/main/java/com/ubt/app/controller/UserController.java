package com.ubt.app.controller;
import com.ubt.app.util.CustomErrorType;
import com.ubt.model.Server;
import com.ubt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<Server>> listAllUsers() {
        logger.info("List all servers");
        List<Server> servers = userService.getAll();
        if (servers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Server> getUser(@PathVariable ("id") int id) {
        logger.info("Get server with id: "+id);
        // service + repository help web to provide data from database
        Server server = userService.getById(id);
        if (server == null) {
            logger.error("Server with id:"+id+" doesnt exist.");
        }
        return new ResponseEntity<>(server, HttpStatus.OK);
    }

    // create a server
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody Server server, UriComponentsBuilder uriCBuilder) {
        logger.info("Creating Server: {}", server);

        if (userService.getById(server.getId()) != null) {
            logger.error("Server with id:"+ server.getId()+" already exist.");
            return new ResponseEntity<>(new CustomErrorType
                    ("Unable to create server with id:" + server.getId() + " exist."),
                    HttpStatus.CONFLICT);
        }
        userService.save(server);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriCBuilder.path("/api/server/{id}").buildAndExpand(server.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody Server server) {
        logger.info("Updating Server with id {}", id);
        Server currentServer = userService.getById(id);

        if (currentServer == null) {
            logger.error("Unable to update. Server with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Server with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentServer.setFirstName(server.getFirstName());
        currentServer.setLastName(server.getLastName());
        currentServer.setUsername(server.getUsername());
        currentServer.setPassword(server.getPassword());
        currentServer.setEmail(server.getEmail());
        userService.save(currentServer);
        return new ResponseEntity<>(currentServer, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting Server with id {}", id);
        Server server = userService.getById(id);
        if (server == null) {
            logger.error("Unable to delete. Server with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Server with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<Server>(HttpStatus.NO_CONTENT);
    }




}
