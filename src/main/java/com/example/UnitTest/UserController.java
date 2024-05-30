package com.example.UnitTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public User getUserbyId (@RequestParam int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser (@RequestBody User user) {
        return userService.createUser(user);
    }
    @PutMapping("/{id}")
    public User updateUser (@PathVariable int id, @RequestParam(required = false) String name,
                            @RequestParam(required = false) String surname) {
        return  userService.updateUser(id, name, surname);
    }
    @DeleteMapping ("/delete{id}")
    public void deleteUser (@PathVariable int id) {
        userService.deleteUser(id);
    }
}
