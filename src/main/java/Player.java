import javafx.scene.control.Label;

import java.util.List;

public class Player {
    private String name;
    private List<Cube> playersCubes;
    private int playerScore;
    private Label playerLabel = new Label();

    public Label getPlayerLabel() {
        return playerLabel;
    }

    public String getName() {
        return name;
    }

    public List<Cube> getPlayersCubes() {
        return playersCubes;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayersCubes(List<Cube> playersCubes) {
        this.playersCubes = playersCubes;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    public void updateScore (int score) {
        playerScore+=score;
        playerLabel.setText(String.valueOf(playerScore));
    }
}
