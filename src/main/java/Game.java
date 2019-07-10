
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
    private String player1Name;
    private String player2Name;
    private int player1Score;
    private int player2Score;
    private int roundNumber;
    private boolean isGameFinished;
    private List<Cube> tableCubes = new ArrayList<>();
    private List<Cube> player1Cubes = new ArrayList<>();
    private List<Cube> player2Cubes = new ArrayList();
    int counter = 0;

    private Scene gameScene;

    public Game(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        player1Score = player2Score = 0;
    }

    GridPane gameGridPane = new GridPane();

    FlowPane tableCubesAndButtonsPanel = new FlowPane(Orientation.VERTICAL);

    FlowPane player1Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player1ScorePanel = new FlowPane(Orientation.VERTICAL);
    Label player1ScoreView = new Label();

    FlowPane player2Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player2ScorePanel = new FlowPane(Orientation.VERTICAL);
    Label player2ScoreView = new Label();

    FlowPane tableCubesPanel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane cubesButtons = new FlowPane(Orientation.HORIZONTAL);

    FlowPane switchPlaerPane = new FlowPane(Orientation.VERTICAL);
    Button cube1Button = new Button("Pick!");
    Button cube2Button = new Button("Pick!");
    Button cube3Button = new Button("Pick!");
    Button cube4Button = new Button("Pick!");
    Button cube5Button = new Button("Pick!");
    Button cubesThrow = new Button(" Throw Cubes");

    ToggleGroup playerSwitch = new ToggleGroup();
    RadioButton player1 = new RadioButton("Player1");
    RadioButton player2 = new RadioButton("Player2");

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

        player1.setToggleGroup(playerSwitch);
        player1.setUserData("Player1");
        player1.setSelected(true);
        player2.setToggleGroup(playerSwitch);
        player2.setUserData("Player2");
        switchPlaerPane.getChildren().add(cubesThrow);
        switchPlaerPane.setAlignment(Pos.CENTER);
        switchPlaerPane.getChildren().add(player1);
        switchPlaerPane.getChildren().add(player2);

        tableCubesPanel.setAlignment(Pos.CENTER);
        tableCubesPanel.setHgap(30);
        tableCubesPanel.setPrefWrapLength(700);
        cubesButtons.setHgap(90);
        cubesButtons.setAlignment(Pos.CENTER);
        cubesButtons.getChildren().add(cube1Button);
        cubesButtons.getChildren().add(cube2Button);
        cubesButtons.getChildren().add(cube3Button);
        cubesButtons.getChildren().add(cube4Button);
        cubesButtons.getChildren().add(cube5Button);

        cubesThrow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                roundStart();
            }
        });

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

        tableCubesAndButtonsPanel.setAlignment(Pos.CENTER);
        tableCubesAndButtonsPanel.getChildren().add(tableCubesPanel);
        tableCubesAndButtonsPanel.getChildren().add(cubesButtons);

        player1ScorePanel.setAlignment(Pos.CENTER);
        player1ScorePanel.getChildren().add(new Label("Score:"));

        player1ScoreView.setFont(new Font("Arial",24));
        player1ScoreView.setText(String.valueOf(player1Score));
        player1ScorePanel.getChildren().add(player1ScoreView);

        player2ScorePanel.setAlignment(Pos.CENTER);
        player2ScorePanel.getChildren().add(new Label("Score:"));

        player2ScoreView.setFont(new Font("Arial",24));
        player2ScoreView.setText(String.valueOf(player2Score));
        player2ScorePanel.getChildren().add(player2ScoreView);

        player1NameView.setFont(new Font("Arial",24));
        player2NameView.setFont(new Font("Arial",24));

        GridPane.setConstraints(player1Panel,1,0);
        GridPane.setConstraints(player2Panel,1,2);
        GridPane.setConstraints(player1NameView,0,0);
        GridPane.setConstraints(player2NameView,0,2);
        GridPane.setConstraints(player1ScorePanel,2,0);
        GridPane.setConstraints(player2ScorePanel,2,2);
        GridPane.setConstraints(tableCubesAndButtonsPanel,1,1);
        GridPane.setConstraints(switchPlaerPane,2,1);

        gridPane.getChildren().addAll(player1NameView,player2NameView,player1ScorePanel,player2ScorePanel,
                tableCubesAndButtonsPanel,player1Panel,player2Panel,switchPlaerPane);

        gameScene = new Scene(gridPane,1024,768,Color.GREY);
        return gameScene;
    }
    public void buttonHandler(int cnt) {
        if (playerSwitch.getSelectedToggle().getUserData().toString()=="Player1") {
            player1Score+=tableCubes.get(cnt).getActualScore();
            player1ScoreView.setText(String.valueOf(player1Score));
        } else if (playerSwitch.getSelectedToggle().getUserData().toString()=="Player2") {
            player2Score+=tableCubes.get(cnt).getActualScore();
            player2ScoreView.setText(String.valueOf(player2Score));
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
        for(Cube cube: player1Cubes) {
            player1Panel.getChildren().add(cube.getActualView());
        }
    }

    public void drawPlayer2Cubes () {
        for(Cube cube: player2Cubes) {
            player2Panel.getChildren().add(cube.getActualView());
        }
    }



}
