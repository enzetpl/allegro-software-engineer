package pl.pussy.allegrosoftwareengineer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.pussy.allegrosoftwareengineer.model.GitHubUser;
import pl.pussy.allegrosoftwareengineer.model.GithubRepository;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class GitHubApiClient {

    private static final int PAGE_SIZE = 100;

    public List<GithubRepository> getPageRepos(String username, int pageNumber) {
        RestTemplate restTemplate = new RestTemplate();
        GithubRepository[] repositories = restTemplate
                .getForObject(String.format("https://api.github.com/users/%s/repos?page=%s&per_page=100", username, pageNumber),
                        GithubRepository[].class);
        if (repositories != null) {
            return Arrays.asList(repositories);
        }
        return Collections.emptyList();

    }
    public GitHubUser getUser(String username) {
        RestTemplate restTemplate = new RestTemplate();

        GitHubUser user = restTemplate
                .getForObject(String.format("https://api.github.com/users/%s", username), GitHubUser.class);

        return user;
    }

    public List<GithubRepository> getAllRepositories(GitHubUser user){
        RestTemplate restTemplate = new RestTemplate();
        ExecutorService executorService = Executors.newCachedThreadPool();
        String login = user.getLogin();
        int numberOfRepositories = user.getRepos();
        int pages = numberOfRepositories / PAGE_SIZE + 1;
        List<Future<GithubRepository[]>> futures = new ArrayList<>();

        for (int i = 0; i < pages; i++) {
            final int currentPage = i+1;
            futures.add(executorService.submit(()-> restTemplate
                            .getForObject(String.format("https://api.github.com/users/%s/repos?page=%d&per_page=%d",
                                    login, currentPage, PAGE_SIZE), GithubRepository[].class)));
        }
        List<GithubRepository> repositories = executeGetRequests(futures);
        return repositories;
    }


    private List<GithubRepository> executeGetRequests(List<Future<GithubRepository[]>> futures) {
        List<GithubRepository> repositories = new ArrayList<>();

        for (Future<GithubRepository[]> future:
                futures) {
            repositories.addAll(Arrays.asList(executeGetRequest(future)));
        }
        return repositories;
    }

    private GithubRepository[] executeGetRequest(Future<GithubRepository[]> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new GithubRepository[0];
    }

}
