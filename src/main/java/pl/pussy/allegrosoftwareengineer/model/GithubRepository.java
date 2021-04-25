package pl.pussy.allegrosoftwareengineer.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GithubRepository {

    private String name;

    private int stars;

    @JsonProperty("stargazers_count")
    public void setStars(int stars) {
        this.stars = stars;
    }
    @JsonProperty("stars")
    public int getStars() {
        return stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
