import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.text.html.ImageView;

public class Game {
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private int roundNumber;
    private boolean isGameFinished;
    private Scene gameScene;

    public Game(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        player1Score = player2Score = 0;
    }

    GridPane gameGridPane = new GridPane();
    FlowPane mainCubesPanel = new FlowPane(Orientation.HORIZONTAL);

    FlowPane player1Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player1ScorePanel = new FlowPane(Orientation.VERTICAL);
    Label player1ScoreView = new Label();

    FlowPane player2Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player2ScorePanel = new FlowPane(Orientation.VERTICAL);
    Label player2ScoreView = new Label();
    Button takeButton = new Button ("Take");
    int sumScore;

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public Scene rebuildScene() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.BOTTOM_CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(15);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(70);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(15);
        gridPane.getColumnConstraints().addAll(column0,column1,column2);

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row0.setPercentHeight(20);
        row1.setPercentHeight(60);
        row2.setPercentHeight(20);
        gridPane.getRowConstraints().addAll(row0,row1,row2);

        Label player1NameView = new Label("    "+player1Name);
        Label player2NameView = new Label("    "+player2Name);

        player1Panel.setAlignment(Pos.CENTER);
        player1Panel.setHgap(30);
        player2Panel.setAlignment(Pos.CENTER);
        player2Panel.setHgap(30);
        mainCubesPanel.setAlignment(Pos.CENTER);
        mainCubesPanel.setHgap(30);

        player1ScorePanel.setAlignment(Pos.CENTER);
        player1ScorePanel.getChildren().add(new Label("Score:"));

        player1ScoreView.setFont(new Font("Arial",24));
        player1ScorePanel.getChildren().add(player1ScoreView);

        player2ScorePanel.setAlignment(Pos.CENTER);
        player2ScorePanel.getChildren().add(new Label("Score:"));

        player2ScoreView.setFont(new Font("Arial",24));
        player2ScorePanel.getChildren().add(player2ScoreView);

        player1NameView.setFont(new Font("Arial",24));
        player2NameView.setFont(new Font("Arial",24));

        mainCubesPanel.setAlignment(Pos.CENTER);
        mainCubesPanel.setHgap(30);

        GridPane.setConstraints(player1Panel,1,0);
        GridPane.setConstraints(player2Panel,1,2);
        GridPane.setConstraints(player1NameView,0,0);
        GridPane.setConstraints(player2NameView,0,2);
        GridPane.setConstraints(player1ScorePanel,2,0);
        GridPane.setConstraints(player2ScorePanel,2,2);
        GridPane.setConstraints(mainCubesPanel,1,1);
        GridPane.setConstraints(takeButton,2,1);
        gridPane.getChildren().addAll(player1NameView,player2NameView,player1ScorePanel,player2ScorePanel,mainCubesPanel,
                takeButton);

        takeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player1Score+=sumScore;
                player1ScoreView.setText(String.valueOf(player1Score));
                Round round = new Round(2);
                round.firstThrow();
                mainCubesPanel.getChildren().clear();
                sumScore=0;
                for (Cube cube: round.getTableCubes()) {
                    mainCubesPanel.getChildren().add(cube.getActualView());
                    sumScore+=cube.getActualScore();
                }
            }
        });

        gameScene = new Scene(gridPane,1024,768,Color.GREY);
        return gameScene;
    }

    public void roundStart() {
        Round round = new Round(1);
        round.firstThrow();
        for(Cube cube: round.getTableCubes()) {
            mainCubesPanel.getChildren().add(cube.getActualView());
            this.sumScore+=cube.getActualScore();
        };
    }



}
