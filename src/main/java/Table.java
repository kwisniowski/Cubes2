
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class Table extends Application {
    GridPane gridPane = new GridPane();
    FlowPane player1Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player2Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane mainCubesPanel = new FlowPane(Orientation.HORIZONTAL);
    Button startButton = new Button("START");
    Label welcomeLabel = new Label("Cubes ver. 1   LET'S PLAY");
    FlowPane playersNames = new FlowPane(Orientation.VERTICAL);
    TextField player1Name = new TextField(" Player 1:");
    TextField player2Name = new TextField(" Player 2:");

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

        for (int i=1;i<6;i++) {
            Cube tempCube = new Cube();
            tempCube.cubeThrow();
            mainCubesPanel.getChildren().add(tempCube.getActualView());
        }

        mainCubesPanel.setAlignment(Pos.CENTER);
        mainCubesPanel.setHgap(30);
        mainCubesPanel.setPrefWrapLength(700);
        welcomeLabel.setAlignment(Pos.CENTER);
        startButton.setAlignment(Pos.BASELINE_CENTER);
        playersNames.setAlignment(Pos.CENTER);
        playersNames.getChildren().add(player1Name);
        playersNames.getChildren().add(player2Name);

        GridPane.setConstraints(player1Panel,1,0);
        GridPane.setConstraints(player2Panel,1,2);
        GridPane.setConstraints(mainCubesPanel, 1,1);
        GridPane.setConstraints(welcomeLabel,1,0);
        GridPane.setConstraints(startButton,2,0);
        GridPane.setConstraints(playersNames,1,2);
        gridPane.getChildren().addAll(player1Panel,player2Panel,mainCubesPanel,welcomeLabel,startButton,playersNames);

        Scene scene = new Scene(gridPane, 1024, 768, Color.GREY);

        primaryStage.setTitle("Cubes");

        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game game = new Game(player1Name.getText(),player2Name.getText());
                primaryStage.setScene(game.rebuildScene());
                game.roundStart();
            }
        });

       player1Name.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               player1Name.clear();
           }
       });

        player2Name.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player2Name.clear();
            }
        });
    }
}
