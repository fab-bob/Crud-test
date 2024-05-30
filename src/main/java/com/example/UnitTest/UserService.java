package com.example.UnitTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser (User user) {
        return userRepository.save(user);
    }
    public User getUserById (int id) {
        return userRepository.findById(id).get();
    }
    public User updateUser (int id, String name, String surname) {
        userRepository.findById(id).get().setName(name);
        userRepository.findById(id).get().setSurname(surname);
        return userRepository.save(userRepository.findById(id).get());
    }
    public void deleteUser (int id) {
        userRepository.delete(userRepository.findById(id).get());
    }
}
