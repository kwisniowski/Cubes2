
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Table table;
    private int secondRoundPoints;
    private boolean riskResult;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Round round;

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    private int  roundCounter =0;
    private List<Cube> tableCubes = new ArrayList<>();
    Label currentPlayerName = new Label("");
    Label player1ScoreView = new Label();
    Label player2ScoreView = new Label();
    Label player1NameView = new Label();
    Label player2NameView = new Label();
    Label bonusInfo = new Label();
    Button goButton = new Button("Go!");
    Button nextRound = new Button("End Round");
    Button throwRest = new Button("Take a risk!");
    String gameMode;

    public Label getPlayer1ScoreView() {
        return player1ScoreView;
    }

    public Label getPlayer2ScoreView() {
        return player2ScoreView;
    }

    public void setPlayer1ScoreView(Label player1ScoreView) {
        this.player1ScoreView = player1ScoreView;
    }

    public boolean isRiskResult() {
        return riskResult;
    }

    public void setPlayer2ScoreView(Label player2ScoreView) {
        this.player2ScoreView = player2ScoreView;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Game(Table table, Player player1, Player player2) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerScore(0);
        player2.setPlayerScore(0);
        currentPlayer=player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Table getTable() {
        return table;
    }

    public void prepareControls() {

        player1.getPlayerLabel().setFont(new Font("Arial", 24));
        player1NameView.setFont(new Font("Arial", 24));
        player1NameView.setText(player1.getName());
        player2.getPlayerLabel().setFont(new Font("Arial", 24));
        player2NameView.setFont(new Font("Arial", 24));
        player2NameView.setText(player2.getName());
        bonusInfo.setFont(new Font("Arial",24));
        table.getTopLeftPanel().getChildren().clear();
        table.getTopLeftPanel().setAlignment(Pos.CENTER);
        table.getTopLeftPanel().getChildren().add(player1NameView);
        table.getBottomLeftPanel().setAlignment(Pos.CENTER);
        table.getBottomLeftPanel().getChildren().clear();
        table.getBottomLeftPanel().getChildren().add(player2NameView);

        player1.getPlayerLabel().setText(String.valueOf(player1.getPlayerScore()));
        player2.getPlayerLabel().setText(String.valueOf(player2.getPlayerScore()));
        table.getTopRightPanel().getChildren().clear();
        table.getTopRightPanel().getChildren().add(player1.getPlayerLabel());
        table.getBottomRightPanel().getChildren().clear();
        table.getBottomRightPanel().getChildren().add(player2.getPlayerLabel());

        bonusInfo.setAlignment(Pos.CENTER);
        table.getCenterLeftPanel().setAlignment(Pos.CENTER);
        table.getCenterLeftPanel().getChildren().add(bonusInfo);

        table.getCenterRightPanel().getChildren().clear();
        table.getCenterRightPanel().getChildren().add(goButton);
        table.getCenterRightPanel().setAlignment(Pos.CENTER);
        table.getCenterRightPanel().getChildren().add(currentPlayerName);

        table.getCenterPanel().getChildren().clear();
        table.getBottomCenterPanel().getChildren().clear();
        table.getTopCenterPanel().getChildren().clear();
        table.getCenterPanel().setAlignment(Pos.CENTER);
        table.getCenterPanel().setHgap(30);
        table.getCenterPanel().setPrefWrapLength(700);

        goButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextRound();
            }
        });

        nextRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondRoundPoints=round.getScore();
                bonusInfo.setText("");
                throwRest.setDisable(false);
                if (!isRiskResult()){
                    currentPlayer.updateScore(round.getScore());
                }
                currentPlayer.setPlayersCubes(round.getPlayerCubes());
                if (currentPlayer.getPlayersCubes().size()==5) {
                    round.updateScore(50);
                }
                riskResult =false;
                nextRound();
            }
        });

        throwRest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throwRest.setDisable(true);
                int cubesCount = 5 - round.getPlayerCubes().size();
                round.getPlayerCubes().clear();
                round.getTableCubes().clear();
                round.buttonsList.clear();
                round.buttonsBar.setVisible(false);
                round.cubeThrow(cubesCount);
                round.centerPanelDraw();
                long counter = round.getTableCubes().stream()
                        .map(cube -> cube.getActualScore())
                        .filter(c -> (c == 1) || (c == 5))
                        .count();
                if (counter != 0) {
                    for (Cube cube : round.getTableCubes()) {
                        if ((cube.getActualScore() == 5)||(cube.getActualScore()==1)) {
                            round.getPlayerCubes().add(cube);
                            round.setScore(round.getScore()+cube.validateCubeScore());
                            round.getScoreView().setText(String.valueOf(round.getScore()));
                        }
                    }
                }
                else {
                    bonusInfo.setTextFill(Color.RED);
                    bonusInfo.setText("Ups!");
                    riskResult = true;
                }
                if ((int) counter==cubesCount) {
                    round.updateScore(50);
                }
            }
        });

    }

    public void switchUser () {
        if (currentPlayer.equals(player1)) {
            currentPlayer=player2;
        }
        else if (currentPlayer.equals(player2)) {
            currentPlayer=player1;
        }
    }

    public void nextRound() {
        switchUser();
        getTable().getTopCenterPanel().getChildren().clear();
        getTable().getBottomCenterPanel().getChildren().clear();
        round = new Round(currentPlayer, Game.this);
        round.playRound();
        goButton.setVisible(false);
        getTable().getCenterRightPanel().getChildren().add(nextRound);
        getTable().getCenterRightPanel().getChildren().add(throwRest);
    }
}

