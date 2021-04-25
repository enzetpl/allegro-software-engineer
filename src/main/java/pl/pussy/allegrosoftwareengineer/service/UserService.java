package pl.pussy.allegrosoftwareengineer.service;

import org.springframework.data.domain.Page;
import pl.pussy.allegrosoftwareengineer.model.GitHubUser;
import pl.pussy.allegrosoftwareengineer.model.GithubRepository;
import pl.pussy.allegrosoftwareengineer.model.dto.UserStarsDto;

public interface UserService {
    GitHubUser getUser(String username);
    Page<GithubRepository> getRepositoriesPage(String username, int pageNumber);
    UserStarsDto getStars(String username);
}
