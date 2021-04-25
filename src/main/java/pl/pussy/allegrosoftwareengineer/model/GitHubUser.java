package pl.pussy.allegrosoftwareengineer.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitHubUser {

    private String login;
    private int repos;
    public GitHubUser(String login) {
        this.login = login;
    }

    public GitHubUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("repos")
    public int getRepos() {
        return repos;
    }
    @JsonProperty("public_repos")
    public void setRepos(int repos) {
        this.repos = repos;
    }
}
