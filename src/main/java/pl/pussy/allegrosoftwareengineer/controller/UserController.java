package pl.pussy.allegrosoftwareengineer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pussy.allegrosoftwareengineer.model.GitHubUser;
import pl.pussy.allegrosoftwareengineer.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/users/{username}")
    public ResponseEntity<GitHubUser> getRepositoriesForUser(
            @PathVariable String username) {
        return  ResponseEntity.ok(userServiceImpl.getUser(username));
    }
}
