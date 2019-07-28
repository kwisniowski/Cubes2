import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.List;

public class Player {
    private String name;
    private List<Cube> playersCubes;
    private int playerScore;
    private Label playerLabel = new Label();
    Game game;

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
        checkForVictory();
    }

    public void checkForVictory() {
        if (game.getTable().getGameMode().equals("Points")) {
            if (playerScore >= game.getTable().getPointsToWin()) {
                showGameSummary(name);
            }

            if (game.getTable().getGameMode().equals("Rounds")) {
                if (game.getRoundCounter() >= game.getTable().getRoundsToEnd() * 2) {
                    String winner = game.getPlayer1().getPlayerScore() > game.getPlayer2().getPlayerScore() ? game.getPlayer1().getName() : game.getPlayer2().getName();
                    showGameSummary(winner);
                }
            }
        }
    }

    public void showGameSummary(String winner) {
        Alert infoAlert = new Alert(Alert.AlertType.CONFIRMATION);
        infoAlert.setTitle("Cubes 2.0");
        infoAlert.setContentText("Congrtatulations!\n"+winner+" wins the game!\n"+game.getPlayer1().getName()+": "+ game.getPlayer1().getPlayerScore()+
                " points\n"+game.getPlayer2().getName()+": "+game.getPlayer2().getPlayerScore()+" points"+"\nDo you want to play again?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No, exit", ButtonBar.ButtonData.NO);
        infoAlert.getButtonTypes().setAll(okButton, noButton);
        infoAlert.showAndWait().ifPresent(type-> {
            if (type == okButton)
            {
                game.getTable().resetGame();
            }
            if (type == noButton)
            {
                Platform.exit();
            }
        });
    }
}
