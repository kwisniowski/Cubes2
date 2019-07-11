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

public class Round {
    private int actualPlayerNumber;
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

    public List<Cube> cubeThrow(int cubesCount) {
        tableCubes.clear();
        for (int i=0; i<cubesCount; i++) {
            Cube cube = new Cube();
            cube.cubeThrow();
            tableCubes.add(cube);
        }
        return tableCubes;
    }

    public void playRound () {
        game.getTable().getCenterRightPanel().getChildren().clear();
        currentPlayerView.setText(player.getName());
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayer);
        game.getTable().getCenterRightPanel().getChildren().add(currentPlayerView);
        cubeThrow(5);
        game.getTable().getCenterPanel().getChildren().clear();
        for (Cube cube : tableCubes) {
            cubesBar.getChildren().add(cube.getActualView());
        }
        for (Button button : buttonsList) {
            buttonsBar.getChildren().add(button);
        }
        game.getTable().getCenterPanel().getChildren().add(cubesAndButtonsBar);

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
        drawPlayerCubes(player);
     /*
        if (player.equals(game.getPlayer1())) {
            drawPlayerCubes(game.getPlayer1());
        } else if (player.equals(game.getPlayer1())) {
            drawPlayerCubes(game.getPlayer2());
        } */
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
