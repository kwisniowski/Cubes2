import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.image.ImageView;

public class Table extends Application {
    GridPane gridPane = new GridPane();
    FlowPane player1Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane player2Panel = new FlowPane(Orientation.HORIZONTAL);
    FlowPane mainCubesPanel = new FlowPane(Orientation.HORIZONTAL);
    ImageView test = new ImageView(new Image("file:resources/kostka1.jpg"));
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
        row0.setPercentHeight(15);
        row1.setPercentHeight(70);
        row2.setPercentHeight(15);
        gridPane.getRowConstraints().addAll(row0,row1,row2);

        for (int i=1;i<6;i++) {
            Cube tempCube = new Cube();
            tempCube.cubeThrow();
            mainCubesPanel.getChildren().add(tempCube.getActualView());
        }
        mainCubesPanel.setAlignment(Pos.CENTER);
        mainCubesPanel.setHgap(30);
        GridPane.setConstraints(player1Panel,1,0);
        GridPane.setConstraints(player2Panel,1,2);
        GridPane.setConstraints(mainCubesPanel, 1,1);
        gridPane.getChildren().addAll(player1Panel,player2Panel,mainCubesPanel);

        Scene scene = new Scene(gridPane, 1024, 768, Color.GREY);

        primaryStage.setTitle("Cubes");

        primaryStage.setResizable(true);
        primaryStage.initStyle(StageStyle.DECORATED);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
