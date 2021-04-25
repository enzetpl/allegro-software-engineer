package pl.pussy.allegrosoftwareengineer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pussy.allegrosoftwareengineer.model.dto.UserStarsDto;
import pl.pussy.allegrosoftwareengineer.service.UserService;
import pl.pussy.allegrosoftwareengineer.service.UserServiceImpl;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class StarsController {

    private final UserService userServiceImpl;

    public StarsController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users/{username}/stars")
    public ResponseEntity<UserStarsDto> getStarsForUser(@PathVariable String username) {

        return ResponseEntity.ok(userServiceImpl.getStars(username));
    }
}
