package pl.pussy.allegrosoftwareengineer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.pussy.allegrosoftwareengineer.model.GitHubUser;
import pl.pussy.allegrosoftwareengineer.model.GithubRepository;
import pl.pussy.allegrosoftwareengineer.model.dto.UserStarsDto;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final int PAGE_SIZE = 100;
    private final GitHubApiClient gitHubApiClient;

    public UserServiceImpl(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public GitHubUser getUser(String username) {
        GitHubUser user = gitHubApiClient.getUser(username);
        return user;
    }
    public Page<GithubRepository> getRepositoriesPage(String username, int pageNumber) {
        List<GithubRepository> repos = gitHubApiClient.getPageRepos(username, pageNumber);
        GitHubUser user = getUser(username);
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        return new PageImpl<>(repos, pageable, user.getRepos());
    }


    public UserStarsDto getStars(String username) {

        GitHubUser user = getUser(username);
        List<GithubRepository> repositories = gitHubApiClient.getAllRepositories(user);

        UserStarsDto userStarsDto = new UserStarsDto();
        userStarsDto.setUsername(username);
        userStarsDto.setStars(getSumStars(repositories));

        return userStarsDto;
    }

    private int getSumStars(List<GithubRepository> repositories) {
        return repositories.stream()
                .mapToInt(GithubRepository::getStars)
                .sum();
    }

}
