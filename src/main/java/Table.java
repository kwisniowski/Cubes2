
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.*;
import java.nio.Buffer;

public class Table extends Application {
    private String gameMode="Points";

    public String getGameMode() {
        return gameMode;
    }

    public int getRoundsToEnd() {
        return roundsToEnd;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    private int roundsToEnd=20;
    private int pointsToWin=300;
    private VBox root = new VBox();
    private GridPane gridPane = new GridPane();
    private HBox topCenterPanel = new HBox();
    private FlowPane centerPanel = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane bottomCenterPanel = new FlowPane(Orientation.VERTICAL);

    private FlowPane topLeftPanel = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane centerLeftPanel = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane bottomLeftPanel = new FlowPane(Orientation.HORIZONTAL);

    private FlowPane topRightPanel = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane centerRightPanel = new FlowPane(Orientation.VERTICAL);
    private FlowPane bottomRightPanel = new FlowPane(Orientation.HORIZONTAL);

    private Button startButton = new Button("START");
    private Label welcomeLabel = new Label("Cubes ver. 1   LET'S PLAY");
    private TextField player1NameTextField = new TextField(" Player 1");
    private TextField player2NameTextField = new TextField(" Player 2");

    Label customSettingsLabel1 = new Label ("      Game mode: ");
    Label customSettingsLabel2 = new Label();

    public GridPane getGridPane() {
        return gridPane;
    }

    public HBox getTopCenterPanel() {
        return topCenterPanel;
    }

    public FlowPane getCenterPanel() {
        return centerPanel;
    }

    public FlowPane getBottomCenterPanel() {
        return bottomCenterPanel;
    }

    public FlowPane getTopLeftPanel() {
        return topLeftPanel;
    }

    public FlowPane getCenterLeftPanel() {
        return centerLeftPanel;
    }

    public FlowPane getBottomLeftPanel() {
        return bottomLeftPanel;
    }

    public FlowPane getTopRightPanel() {
        return topRightPanel;
    }

    public FlowPane getCenterRightPanel() {
        return centerRightPanel;
    }

    public FlowPane getBottomRightPanel() {
        return bottomRightPanel;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }

    public TextField getPlayer1NameTextField() {
        return player1NameTextField;
    }

    public TextField getPlayer2NameTextField() {
        return player2NameTextField;
    }

    public static void main(String[] args) {
        launch(args);
    }

    Stage myStage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        myStage = primaryStage;
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints column0 = new ColumnConstraints();
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column0.setPercentWidth(15);
        column1.setPercentWidth(70);
        column2.setPercentWidth(15);
        gridPane.getColumnConstraints().addAll(column0, column1, column2);

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row0.setPercentHeight(20);
        row1.setPercentHeight(60);
        row2.setPercentHeight(20);
        gridPane.getRowConstraints().addAll(row0, row1, row2);

        centerPanel.setAlignment(Pos.CENTER);
        centerPanel.setHgap(30);
        centerPanel.setPrefWrapLength(700);
        for (int i = 1; i < 6; i++) {
            Cube tempCube = new Cube();
            tempCube.cubeThrow();
            centerPanel.getChildren().add(tempCube.getActualView());
        }

        topCenterPanel.setAlignment(Pos.CENTER);
        topCenterPanel.getChildren().add(welcomeLabel);
        topCenterPanel.getChildren().add(customSettingsLabel1);
        topCenterPanel.getChildren().add(customSettingsLabel2);
        customSettingsLabel2.setText(gameMode.toUpperCase() + "( " + (gameMode.equals("Ronds") ? roundsToEnd : pointsToWin) + " )");

        topRightPanel.setAlignment(Pos.CENTER);
        topRightPanel.getChildren().add(startButton);

        bottomCenterPanel.setAlignment(Pos.CENTER);
        bottomCenterPanel.getChildren().add(player1NameTextField);
        bottomCenterPanel.getChildren().add(player2NameTextField);

        topRightPanel.setAlignment(Pos.CENTER);
        bottomRightPanel.setAlignment(Pos.CENTER);

        GridPane.setConstraints(topCenterPanel, 1, 0);
        GridPane.setConstraints(bottomCenterPanel, 1, 2);
        GridPane.setConstraints(centerPanel, 1, 1);
        GridPane.setConstraints(topRightPanel, 2, 0);
        GridPane.setConstraints(centerRightPanel, 2, 1);
        GridPane.setConstraints(bottomRightPanel, 2, 2);
        GridPane.setConstraints(bottomLeftPanel, 0, 2);
        GridPane.setConstraints(centerLeftPanel, 0, 1);
        GridPane.setConstraints(topLeftPanel, 0, 0);
        gridPane.getChildren().addAll(topCenterPanel, bottomCenterPanel, centerPanel, topRightPanel, bottomRightPanel,
                centerRightPanel, bottomLeftPanel, centerLeftPanel, topLeftPanel);

        Menu menuGame = new Menu("Game");
        Menu menuSettings = new Menu("Settings");
        Menu menuAbout = new Menu("About");
        MenuItem menuItemNewGame = new MenuItem("New game");
        MenuItem menuItemEndGame = new MenuItem("End game");
        MenuItem menuItemAbout = new MenuItem("Show game info");
        MenuItem menuItemSettings = new MenuItem("ShowSettings");
        MenuItem menuRules = new MenuItem("Show game rules");
        menuGame.getItems().add(menuItemNewGame);
        menuGame.getItems().add(menuItemEndGame);
        menuGame.getItems().add(menuRules);
        menuSettings.getItems().add(menuItemSettings);
        menuAbout.getItems().add(menuItemAbout);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuGame);
        menuBar.getMenus().add(menuSettings);
        menuBar.getMenus().add(menuAbout);

        VBox vbox = new VBox(menuBar);
        vbox.setPrefWidth(1024);

        root.getChildren().add(vbox);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 1024, 768, Color.GREY);
        primaryStage.setTitle("Cubes 2.0");
        primaryStage.setResizable(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuSettings.setDisable(true);
                startGame();
            }
        });

        getPlayer1NameTextField().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getPlayer1NameTextField().clear();
            }
        });

        getPlayer2NameTextField().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getPlayer2NameTextField().clear();
            }
        });

        menuItemEndGame.setOnAction(actionEvent -> Platform.exit());
        menuItemNewGame.setOnAction(actionEvent -> {
            Table table = new Table();
        });
        menuItemAbout.setOnAction(actionEvent -> {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Cubes 2.0");
            infoAlert.setContentText("This is a Java FX project\nKacper Wiśniowski\nKurs Kodilla 2019");
            infoAlert.show();
        });
        menuItemSettings.setOnAction(actionEvent -> drawSettingsWindow());
        menuRules.setOnAction(actionEvent -> {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Cubes 2.0 Rules");
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File("resources/rules.txt")));
                while (in.readLine() != null) {
                    sb.append(in.readLine() + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            infoAlert.setContentText(sb.toString());
            infoAlert.show();
        });

        menuItemNewGame.setOnAction(actionEvent -> resetGame());
    }

    public void resetGame () {
        Table tbl = new Table();
        try {
            myStage.close();
            tbl.start(myStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startGame() {
        Player player1 = new Player(getPlayer1NameTextField().getText());
        Player player2 = new Player(getPlayer2NameTextField().getText());
        Game game = new Game(Table.this, player1, player2);
        player1.game = game;
        player2.game = game;

        game.prepareControls();
    }


    public void drawSettingsWindow() {
        FlowPane settingsPane = new FlowPane(Orientation.VERTICAL);
        settingsPane.setPrefHeight(270);
        settingsPane.setPrefWidth(140);
        RadioButton pointsGame = new RadioButton("Points");
        pointsGame.setUserData(new String("Points"));
        RadioButton roundGame = new RadioButton("Rounds");
        roundGame.setUserData(new String("Rounds"));
        HBox buttonsPane = new HBox();
        Button okSettingsButton = new Button("Save");
        Button cancelSettingsButton = new Button ("Cancel");
        buttonsPane.getChildren().addAll(okSettingsButton,cancelSettingsButton);
        ToggleGroup settingsRadioButtons = new ToggleGroup();
        FlowPane roundsQuantityPane = new FlowPane(Orientation.HORIZONTAL);
        FlowPane pointsToWinPane = new FlowPane(Orientation.HORIZONTAL);
        Label pointsLabel = new Label ("Points to win:  ");
        Label roundsLabel = new Label( "Rounds to play: ");
        TextField pointsField = new TextField("300");
        TextField roundsField = new TextField("20");
        roundsQuantityPane.getChildren().add(roundsLabel);
        roundsQuantityPane.getChildren().add(roundsField);
        pointsToWinPane.getChildren().add(pointsLabel);
        pointsToWinPane.getChildren().add(pointsField);

        pointsGame.setToggleGroup(settingsRadioButtons);
        roundGame.setToggleGroup(settingsRadioButtons);
        settingsPane.getChildren().add((new Label(" \nChoose your game type: \n ")));
        settingsPane.getChildren().add(pointsGame);
        settingsPane.getChildren().add(pointsToWinPane);
        settingsPane.getChildren().add((new Label(" \n ")));
        settingsPane.getChildren().add(roundGame);
        settingsPane.getChildren().add(roundsQuantityPane);
        settingsPane.getChildren().add((new Label(" \n ")));

        pointsGame.setSelected(true);
        roundsQuantityPane.setDisable(true);
        settingsPane.getChildren().add(buttonsPane);
        Scene settingsScene = new Scene (settingsPane);
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Game Settings");
        settingsStage.setScene(settingsScene);
        settingsStage.show();

        roundGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (roundGame.isSelected()) {
                    roundsQuantityPane.setDisable(false);
                    pointsToWinPane.setDisable(true);
                }
            }
        });

        pointsGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (pointsGame.isSelected()){
                    roundsQuantityPane.setDisable(true);
                    pointsToWinPane.setDisable(false);
                }
            }
        });

        cancelSettingsButton.setOnAction(event -> settingsStage.close());

        okSettingsButton.setOnAction(event -> {
            gameMode = settingsRadioButtons.getSelectedToggle().getUserData().toString();
            if ((roundGame.isSelected()) && (!roundsField.getText().isEmpty())) {
                roundsToEnd = Integer.parseInt(roundsField.getText());
            }
            if ((pointsGame.isSelected()) && (!pointsField.getText().isEmpty())) {
                pointsToWin = Integer.parseInt(pointsField.getText());
            }
            customSettingsLabel2.setText(gameMode.toUpperCase()+ " ( "+ (gameMode.equals("Rounds")? roundsToEnd : pointsToWin)+ " )");

            settingsStage.close();
        });
    }

    public StringBuilder readFile (String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String textLine = bufferedReader.readLine();
            do {
                textLine = bufferedReader.readLine();
                sb.append(textLine);
            } while(textLine != null);
            bufferedReader.close();
        }
        catch (Exception e) {
            Alert infoAlert = new Alert(Alert.AlertType.ERROR);
            infoAlert.setTitle("Cubes 2.0 Error");
            infoAlert.setContentText("Rules file not found");
        }
        return sb;
    }
}
