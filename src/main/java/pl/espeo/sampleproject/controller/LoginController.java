package pl.espeo.sampleproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.espeo.sampleproject.config.LoginCredentials;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {

    }

    @GetMapping("/secured")
    public String secured() {
        return "Access granted";
    }
}
