package pl.pussy.allegrosoftwareengineer.model.dto;


public class UserStarsDto {
    private String username;
    private int stars;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
