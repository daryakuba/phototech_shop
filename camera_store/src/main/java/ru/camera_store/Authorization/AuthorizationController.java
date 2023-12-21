package ru.camera_store.Authorization;

import java.util.Objects;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.camera_store.services.UserService;


class UserCheckPassword {
    public String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}


@RestController
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{login}")
    public String isRightUser(@PathVariable String login) {
        try {
            if (userService.getUser(login) != null) {
                return "OK";
            }
        } catch (NoResultException e){
            return "ERROR";
        }
        return "ERROR";
    }

    @PostMapping("/user/password/{login}")
    public String passwordIsTrue(@PathVariable String login, @RequestBody UserCheckPassword password) {
        if ((userService.getUser(login) != null) && (Objects.equals(userService.getUser(login).getPassword(), password.getPassword()))) {
            return "OK";
        } else return "ERROR";
    }

    @GetMapping("/user/admin/check/{login}")
    public String getStatusAdmin(@PathVariable String login) {
        if (Objects.equals(userService.getUser(login).getStatus(), "Admin")) {
            return "ADMIN";
        } else return "NOT ADMIN";
    }
}

