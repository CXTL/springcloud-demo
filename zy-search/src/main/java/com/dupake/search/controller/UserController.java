package com.dupake.search.controller;

import com.dupake.search.entity.User;
import com.dupake.search.service.EsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserResource
 *
 * @author star
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private EsUserService esUserService;

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        esUserService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/batch")
    public ResponseEntity<Void> saveBatchUser(@RequestBody List<User> users) {
        esUserService.saveAllUser(users);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        User user = esUserService.getUser(id);

        return ResponseEntity.ok(user);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> searchUserByName(@RequestParam String name) {
        List<User> users = esUserService.searchUserByName(name);

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        esUserService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{name}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable("name") String name) {
        esUserService.deleteUserByName(name);

        return ResponseEntity.ok().build();

    }
}
