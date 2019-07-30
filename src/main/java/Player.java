import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.List;

public class Player {
    private String playersName;
    private List<Cube> playersCubes;
    private int playerScore;
    private Label playerLabel = new Label();
    private Game game;

    public Player(String name) {
        this.playersName = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Label getPlayerLabel() {
        return playerLabel;
    }

    public String getName() {
        return playersName;
    }

    public List<Cube> getPlayersCubes() {
        return playersCubes;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayersCubes(List<Cube> playersCubes) {
        this.playersCubes = playersCubes;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + playersName + '\'' +
                '}';
    }

    public void updateScore (int score) {
        playerScore+=score;
        playerLabel.setText(String.valueOf(playerScore));
        checkForPointsVictory();
    }

    public void checkForPointsVictory() {
        if (game.getTable().getGameMode().equals("Points")) {
            if (playerScore >= game.getTable().getPointsToWin()) {
                showGameSummary();
            }
        }
    }

    public void showGameSummary() {
        Alert infoAlert = new Alert(Alert.AlertType.CONFIRMATION);
        infoAlert.setTitle("Cubes 2.0");
        infoAlert.setContentText("Congrtatulations!\n"+playersName+" wins the game!\n"+game.getPlayer1().getName()+": "+ game.getPlayer1().getPlayerScore()+
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
