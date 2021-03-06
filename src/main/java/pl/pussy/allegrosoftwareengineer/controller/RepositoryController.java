package pl.pussy.allegrosoftwareengineer.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pussy.allegrosoftwareengineer.model.GithubRepository;
import pl.pussy.allegrosoftwareengineer.service.UserService;
import pl.pussy.allegrosoftwareengineer.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class RepositoryController {

    private final UserService userServiceImpl;

    public RepositoryController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users/{username}/repos")
    public ResponseEntity<Page<GithubRepository>> getReposForUser(
            @PathVariable String username,
            @RequestParam(required = false) Integer page) {
        if(page == null)
            page = 1;
        return ResponseEntity.ok(userServiceImpl.getRepositoriesPage(username, page));
    }

}
