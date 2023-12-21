package ru.camera_store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.camera_store.models.User;
import ru.camera_store.services.UserService;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/usersEco")
    public void post(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/users")
    public @ResponseBody
    List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping("/users/getIdByLogin/{login}")
    public Long getIdByLong(@PathVariable String login) {
        return userService.getIdByLogin(login);
    }

    @GetMapping("/users/{id}")
    public @ResponseBody User get(@PathVariable long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteUser(id);
    }
}
