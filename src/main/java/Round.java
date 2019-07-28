import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Round {
    private Player player;
    private List<Cube> playerCubes;
    private List<Cube> tableCubes;
    private int score;
    private Game game;
    private boolean nextPossible;
    boolean isBonus=false;
    Label currentPlayer = new Label ("Current player:");
    Label currentPlayerView = new Label();

    Button cube1Button = new Button("Pick!");
    Button cube2Button = new Button("Pick!");
    Button cube3Button = new Button("Pick!");
    Button cube4Button = new Button("Pick!");
    Button cube5Button = new Button("Pick!");

    List<Button> buttonsList = new ArrayList<>();
    FlowPane cubesBar = new FlowPane(Orientation.HORIZONTAL);
    FlowPane buttonsBar = new FlowPane(Orientation.HORIZONTAL);
    FlowPane cubesAndButtonsBar = new FlowPane(Orientation.VERTICAL);
    Label scoreView = new Label ("");

    public List<Cube> getPlayerCubes() {
        return playerCubes;
    }

    public List<Cube> getTableCubes() {
        return tableCubes;
    }

    public Round(Player player, Game game) {
        nextPossible=false;
        this.player = player;
        this.game = game;
        score = 0;
        playerCubes = new ArrayList<>();
        tableCubes = new ArrayList<>();
        buttonsList.add(cube1Button);
        buttonsList.add(cube2Button);
        buttonsList.add(cube3Button);
        buttonsList.add(cube4Button);
        buttonsList.add(cube5Button);
        cubesBar.setAlignment(Pos.CENTER);
        cubesBar.setHgap(30);
        cubesBar.setPrefWrapLength(700);
        buttonsBar.setAlignment(Pos.CENTER);
        buttonsBar.setHgap(90);
        cubesAndButtonsBar.getChildren().add(cubesBar);
        cubesAndButtonsBar.getChildren().add(buttonsBar);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Label getScoreView() {
        return scoreView;
    }

    public void cubeThrow(int cubesCount) {
        tableCubes.clear();
        for (int i=0; i<cubesCount; i++) {
            Cube cube = new Cube();
            cube.cubeThrow();
            tableCubes.add(cube);
        }
    }

    public void centerPanelDraw() {
        game.getTable().getCenterPanel().getChildren().clear();
        for (Cube cube : tableCubes) {
            cubesBar.getChildren().add(cube.getActualView());
        }
        for (Button button : buttonsList) {
            buttonsBar.getChildren().add(button);
        }
        game.getTable().getCenterPanel().getChildren().add(cubesAndButtonsBar);
    }

    public boolean throwCheckForEmpty() {
        boolean result = true;
        for (Cube cube:tableCubes) {
            if ((cube.getActualScore()==1)||(cube.getActualScore()==5)) {
                result= false;
            }
        }
        if (result) game.throwRest.setDisable(true);
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean throwCheckforBonus() {

        if (countSameCubes(1)>=3) {
            score+=(countSameCubes(1)-2)*100;
            bonusControl(1);
        }
        if (countSameCubes(2)>=3) {
            score += (countSameCubes(2) - 2) * 20;
            bonusControl(2);
        }
        if (countSameCubes(3)>=3) {
            score+=(countSameCubes(3)-2)*30;
            bonusControl(3);
        }
        if (countSameCubes(4)>=3) {
            score+=(countSameCubes(4)-2)*40;
            bonusControl(4);
        }
        if (countSameCubes(5)>=3) {
            score+=(countSameCubes(5)-2)*50;
            bonusControl(5);
        }
        if (countSameCubes(6)>=3) {
            score+=(countSameCubes(6)-2)*60;
            bonusControl(6);
        }
        return isBonus;
    }

    public void bonusControl (int pointer) {
        scoreView.setText(String.valueOf(score));
        game.bonusInfo.setText("Bonus!  ");
        disableBonusCubes(pointer);
        isBonus = true;
    }

    public void disableBonusCubes (int pointer) {
            for (int i=0; i<tableCubes.size();i++) {
                if (tableCubes.get(i).getActualScore()==pointer) {
                    buttonsList.get(i).setDisable(true);
                    playerCubes.add(tableCubes.get(i));
                    drawPlayerCubes(game.getCurrentPlayer());
                }
            }
    }

    public int countSameCubes(int number) {
        long bonus = IntStream.range(0,tableCubes.size())
                .filter(c->tableCubes.get(c).getActualScore()==number)
                .count();
        return (int) bonus;
    }

    public void validateButtons() {
        for (int i=0;i<tableCubes.size(); i++) {
            if ((tableCubes.get(i).getActualScore()!=1)&&(tableCubes.get(i).getActualScore()!=5)) {
                buttonsList.get(i).setDisable(true);
            }
            else buttonsList.get(i).setDisable(false);
        }
    }

    public void playRound () {
        if ((game.getTable().getGameMode().equals("Rounds"))&&((game.getRoundCounter()/2+1 > game.getTable().getRoundsToEnd()))) {
            Alert infoAlert = new Alert(Alert.AlertType.CONFIRMATION);
            infoAlert.setTitle("Cubes 2.0");
            int gameResult = game.getPlayer1().getPlayerScore()-game.getPlayer2().getPlayerScore();

            if (gameResult==0) {
                infoAlert.setContentText("It's a tie!\n" + game.getPlayer1().getName()+": "+game.getPlayer1().getPlayerScore()+
                        " points\n"+game.getPlayer2().getName()+": "+game.getPlayer2().getPlayerScore()+" points"+"\nDo you want to play again?");
            }
            else {
                String winner = null;
                if (gameResult > 0) {
                    winner = game.getPlayer1().getName();
                }
                else {
                    winner = game.getPlayer2().getName();
                }

                    infoAlert.setContentText("Congrtatulations!\n" + winner + " wins the game!\n\n" + game.getPlayer1().getName() + ": " + game.getPlayer1().getPlayerScore() +
                            " points\n" + game.getPlayer2().getName() + ": " + game.getPlayer2().getPlayerScore() + " points" + "\nDo you want to play again?\n");
                }

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

        score=0;
        game.throwRest.setDisable(false);
        currentPlayerView.setText(player.getName());
        scoreView.setFont(new Font("Arial",24));
        scoreView.setText(String.valueOf(getScore()));
        game.getTable().getCenterRightPanel().getChildren().clear();
        game.getTable().getCenterRightPanel().getChildren().add(new Label(game.getTable().getGameMode()));
        game.getTable().getCenterRightPanel().getChildren().add(new Label((game.getTable().getGameMode().equals("Points")? String.valueOf(game.getTable().getPointsToWin()+"\n\n") :
                String.valueOf(game.getTable().getRoundsToEnd()+"\n\n"))));
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayer);
        currentPlayerView.setFont(new Font("Arial",20));
        currentPlayerView.setTextFill(Color.BLUE);
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayerView);
        game.getTable().getCenterRightPanel().getChildren().add(new Label("\n"));
        game.getTable().getCenterRightPanel().getChildren().add(new Label("Score:"));
        game.getTable().getCenterRightPanel().getChildren().add(scoreView);
        game.getTable().getCenterRightPanel().getChildren().add(new Label("\nRound number: \n"));
        game.roundCounterLabel.setFont(new Font("Arial",24));
        game.roundCounterLabel.setText(String.valueOf(game.getRoundCounter()/2+1)+"\n");
        game.getTable().getCenterRightPanel().getChildren().add(game.roundCounterLabel);
        game.getTable().getCenterRightPanel().getChildren().add(new Label("\n"));
        cubeThrow(5);
        validateButtons();
        boolean bonus = throwCheckforBonus();
        if (throwCheckForEmpty()&&(!bonus)) {
            player.updateScore(-50);
            game.bonusInfo.setTextFill(Color.RED);
            game.bonusInfo.setText("UPS!  -50");
        }
        if (throwCheckForEmpty()&&(bonus)) {
            player.updateScore(-50);
            game.throwRest.setDisable(false);
        }
        if (!tableCubes.isEmpty()) {
            centerPanelDraw();
        }

        cube1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(0);
                cube1Button.setDisable(true);
                isBonusInOneThrow();
            }
        });
        cube2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(1);
                cube2Button.setDisable(true);
                isBonusInOneThrow();
            }
        });
        cube3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(2);
                cube3Button.setDisable(true);
                isBonusInOneThrow();
            }
        });
        cube4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(3);
                cube4Button.setDisable(true);
                isBonusInOneThrow();
            }
        });
        cube5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(4);
                cube5Button.setDisable(true);
                isBonusInOneThrow();
            }
        });
    }

    public void buttonHandler(int cnt) {
        score+=tableCubes.get(cnt).validateCubeScore();
        playerCubes.add(tableCubes.get(cnt));
        scoreView.setFont(new Font("Arial",24));
        scoreView.setText(String.valueOf(getScore()));
        drawPlayerCubes(player);
    }

    public void drawPlayerCubes (Player player) {
        if (player.equals(game.getPlayer1())) {
            game.getTable().getTopCenterPanel().getChildren().clear();
            for (Cube cube : playerCubes) {
                game.getTable().getTopCenterPanel().getChildren().add(cube.getActualView());
            }
        }
        else if (player.equals(game.getPlayer2())) {
            game.getTable().getBottomCenterPanel().getChildren().clear();
            for (Cube cube : playerCubes) {
                game.getTable().getBottomCenterPanel().getChildren().add(cube.getActualView());
            }
        }
    }

    public void updateScore (int update) {
        score+=update;
        scoreView.setText(String.valueOf(score));
    }

    public void isBonusInOneThrow() {
        if (playerCubes.size()==5) {
            updateScore(50);
            game.bonusInfo.setTextFill(Color.GREEN);
            game.bonusInfo.setText(" YES! + 50\n For 5 cubes ");
        }
    }

}
