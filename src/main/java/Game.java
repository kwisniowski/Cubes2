
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Table table;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean isGameFinished;
    private List<Cube> tableCubes = new ArrayList<>();
    Label currentPlayerName = new Label("");
    Label player1ScoreView = new Label();
    Label player2ScoreView = new Label();
    Label player1NameView = new Label();
    Label player2NameView = new Label();
    Button startRound = new Button("Start Round");

    public Game(Table table, Player player1, Player player2) {
        this.table = table;
        this.player1 = player1;
        this.player2 = player2;
        player1.setPlayerScore(0);
        player2.setPlayerScore(0);
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

        player1ScoreView.setFont(new Font("Arial", 24));
        player1NameView.setFont(new Font("Arial", 24));
        player1NameView.setText(player1.getName());
        player2ScoreView.setFont(new Font("Arial", 24));
        player2NameView.setFont(new Font("Arial", 24));
        player2NameView.setText(player2.getName());
        table.getTopLeftPanel().getChildren().clear();
        table.getTopLeftPanel().setAlignment(Pos.CENTER);
        table.getTopLeftPanel().getChildren().add(player1NameView);
        table.getBottomLeftPanel().setAlignment(Pos.CENTER);
        table.getBottomLeftPanel().getChildren().clear();
        table.getBottomLeftPanel().getChildren().add(player2NameView);

        player1ScoreView.setText(String.valueOf(player1.getPlayerScore()));
        player2ScoreView.setText(String.valueOf(player2.getPlayerScore()));
        table.getTopRightPanel().getChildren().clear();
        table.getTopRightPanel().getChildren().add(player1ScoreView);
        table.getBottomRightPanel().getChildren().clear();
        table.getBottomRightPanel().getChildren().add(player2ScoreView);

        table.getCenterRightPanel().getChildren().clear();
        table.getCenterRightPanel().getChildren().add(startRound);
        table.getCenterRightPanel().setAlignment(Pos.CENTER);
        table.getCenterRightPanel().getChildren().add(currentPlayerName);

        table.getCenterPanel().getChildren().clear();
        table.getBottomCenterPanel().getChildren().clear();
        table.getTopCenterPanel().getChildren().clear();
        table.getCenterPanel().setAlignment(Pos.CENTER);
        table.getCenterPanel().setHgap(30);
        table.getCenterPanel().setPrefWrapLength(700);

        startRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentPlayer = player2;
                Round round = new Round(currentPlayer, Game.this);
                round.playRound();
            }
        });
    }
}
/*
    {
        @Override
        public void handle (ActionEvent event){
        roundStart();
    }
    });

}

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

    public void buttonHandler(int cnt) {
        if (playerSwitch.getSelectedToggle().getUserData().toString().equals("Player1")) {
            player1Score+=tableCubes.get(cnt).getActualScore();
            player1ScoreView.setText(String.valueOf(player1Score));
            player1Cubes.add(tableCubes.get(cnt));
            drawPlayer1Cubes();
        } else if (playerSwitch.getSelectedToggle().getUserData().toString().equals("Player2")) {
            player2Score+=tableCubes.get(cnt).getActualScore();
            player2ScoreView.setText(String.valueOf(player2Score));
            player2Cubes.add(tableCubes.get(cnt));
          //   drawPlayer2Cubes();
        }
    }

    public void roundStart() {
        Round round = new Round(1);
        round.firstThrow();
        tableCubesPanel.getChildren().clear();
        tableCubes.clear();
        for(Cube cube: round.getTableCubes()) {
            tableCubes.add(cube);
        }
        drawTableCubes();
        cube1Button.setDisable(false);
        cube2Button.setDisable(false);
        cube3Button.setDisable(false);
        cube4Button.setDisable(false);
        cube5Button.setDisable(false);
    }

    public void drawTableCubes () {
        tableCubesPanel.getChildren().clear();
        for(Cube cube: tableCubes) {
            tableCubesPanel.getChildren().add(cube.getActualView());
        }
    }

    public void drawPlayer1Cubes () {
        player1Panel.getChildren().clear();
        for(Cube cube: player1Cubes) {
            player1Panel.getChildren().add(cube.getActualView());
            System.out.println("Test");
        }
    }

    public void drawPlayer2Cubes () {
        for(Cube cube: player2Cubes) {
            player2Panel.getChildren().add(cube.getActualView());
        }
    }

}   */
