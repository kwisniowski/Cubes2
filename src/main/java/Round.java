import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Round {
    private Player player;
    private List<Cube> playerCubes;
    private List<Cube> tableCubes;
    private int score;
    private Game game;
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

    public Round(Player player, Game game) {
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

    public int getScore() {
        return score;
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

    public void throwCheckForEmpty() {
        boolean result = true;
        for (Cube cube:tableCubes) {
            int test = cube.getActualScore();
            if ((cube.getActualScore()==1)||(cube.getActualScore()==5)) {
                result= false;
            }
        }
        if (result) {
            player.setPlayerScore(player.getPlayerScore()-50);
            game.getPlayer1ScoreView().setText(String.valueOf(game.getPlayer1().getPlayerScore()));
            game.getPlayer2ScoreView().setText(String.valueOf(game.getPlayer2().getPlayerScore()));
            System.out.println("Test");
        }
    }

    public void throwCheckforBonus() {
        if (bonusForSameCubes(1)>=3) {
            score+=(bonusForSameCubes(1)-2)*100;
            disableBonusCubes(1);
        }
        if (bonusForSameCubes(2)>=3) {
            score+=(bonusForSameCubes(2)-2)*20;
            disableBonusCubes(2);
        }
        if (bonusForSameCubes(3)>=3) {
            score+=(bonusForSameCubes(3)-2)*30;
            disableBonusCubes(3);
        }
        if (bonusForSameCubes(4)>=3) {
            score+=(bonusForSameCubes(4)-2)*40;
            disableBonusCubes(4);
        }
        if (bonusForSameCubes(5)>=3) {
            score+=(bonusForSameCubes(5)-2)*50;
            disableBonusCubes(5);
        }
        if (bonusForSameCubes(6)>=3) {
            score+=(bonusForSameCubes(6)-2)*60;
            disableBonusCubes(6);
        }
    }

    public void disableBonusCubes (int pointer) {
            for (int i=0; i<tableCubes.size();i++) {
                if (tableCubes.get(i).getActualScore()==pointer) buttonsList.get(i).setDisable(true);
            }
    }

    public int bonusForSameCubes (int number) {
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
        score=0;
        currentPlayerView.setText(player.getName());
        scoreView.setText(String.valueOf(getScore()));
        game.getTable().getCenterRightPanel().getChildren().clear();
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayer);
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayerView);
        game.getTable().getCenterRightPanel().getChildren().add(scoreView);
        cubeThrow(5);
        throwCheckForEmpty();
        validateButtons();
        throwCheckforBonus();
        centerPanelDraw();

        cube1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(0);
                cube1Button.setDisable(true);
            }
        });
        cube2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(1);
                cube2Button.setDisable(true);
            }
        });
        cube3Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(2);
                cube3Button.setDisable(true);
            }
        });
        cube4Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(3);
                cube4Button.setDisable(true);
            }
        });
        cube5Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonHandler(4);
                cube5Button.setDisable(true);
            }
        });
    }

    public void buttonHandler(int cnt) {
        playerCubes.add(tableCubes.get(cnt));
        score+=tableCubes.get(cnt).validateCubeScore();
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

}
