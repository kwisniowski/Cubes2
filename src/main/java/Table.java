
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Table extends Application {
    private GridPane root = new GridPane();
    private GridPane gridPane = new GridPane();
    private FlowPane topCenterPanel = new FlowPane(Orientation.HORIZONTAL);
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

    public GridPane getGridPane() {
        return gridPane;
    }

    public FlowPane getTopCenterPanel() {
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

    @Override
    public void start(Stage primaryStage) throws Exception {


        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints column0 = new ColumnConstraints();
        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        column0.setPercentWidth(15);
        column1.setPercentWidth(70);
        column2.setPercentWidth(15);
        gridPane.getColumnConstraints().addAll(column0,column1,column2);

        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row0.setPercentHeight(20);
        row1.setPercentHeight(60);
        row2.setPercentHeight(20);
        gridPane.getRowConstraints().addAll(row0,row1,row2);

        centerPanel.setAlignment(Pos.CENTER);
        centerPanel.setHgap(30);
        centerPanel.setPrefWrapLength(700);
        for (int i=1;i<6;i++) {
            Cube tempCube = new Cube();
            tempCube.cubeThrow();
            centerPanel.getChildren().add(tempCube.getActualView());
        }

        topCenterPanel.setAlignment(Pos.CENTER);
        topCenterPanel.getChildren().add(welcomeLabel);

        topRightPanel.setAlignment(Pos.CENTER);
        topRightPanel.getChildren().add(startButton);

        bottomCenterPanel.setAlignment(Pos.CENTER);
        bottomCenterPanel.getChildren().add(player1NameTextField);
        bottomCenterPanel.getChildren().add(player2NameTextField);

        topRightPanel.setAlignment(Pos.CENTER);
        bottomRightPanel.setAlignment(Pos.CENTER);

        GridPane.setConstraints(topCenterPanel,1,0);
        GridPane.setConstraints(bottomCenterPanel,1,2);
        GridPane.setConstraints(centerPanel, 1,1);
        GridPane.setConstraints(topRightPanel,2,0);
        GridPane.setConstraints(centerRightPanel,2,1);
        GridPane.setConstraints(bottomRightPanel,2,2);
        GridPane.setConstraints(bottomLeftPanel,0,2);
        GridPane.setConstraints(centerLeftPanel,0,1);
        GridPane.setConstraints(topLeftPanel,0,0);
        gridPane.getChildren().addAll(topCenterPanel,bottomCenterPanel, centerPanel,topRightPanel, bottomRightPanel,
                centerRightPanel,bottomLeftPanel,centerLeftPanel,topLeftPanel);

        Menu menuGame = new Menu("Game");
        Menu menuSettings = new Menu("Settings");
        Menu menuAbout = new Menu("About");
        MenuItem menuItemNewGame = new MenuItem("New game");
        MenuItem menuItemEndGame = new MenuItem("End game");
        MenuItem menuItemAbout = new MenuItem("Show game info");
        MenuItem menuItemSettings = new MenuItem("ShowSettings");
        menuGame.getItems().add(menuItemNewGame);
        menuGame.getItems().add(menuItemEndGame);
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
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player1 = new Player(getPlayer1NameTextField().getText());
                Player player2 = new Player(getPlayer2NameTextField().getText());
                Game game = new Game(Table.this, player1, player2);
                game.prepareControls();
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
    }
}
