import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Cube {
    private int actualScore;
    private Image actualImage;
    private Map <Integer,Image> cubesMap;
    private Image score1 = new Image("file:resources/kostka1.jpg");
    private Image score2 = new Image("file:resources/kostka2.jpg");
    private Image score3 = new Image("file:resources/kostka3.jpg");
    private Image score4 = new Image("file:resources/kostka4.jpg");
    private Image score5 = new Image("file:resources/kostka5.jpg");
    private Image score6 = new Image("file:resources/kostka6.jpg");
    Random random = new Random();


    public Cube () {
        cubesMap = new HashMap<>();
        cubesMap.put(1,score1);
        cubesMap.put(2,score2);
        cubesMap.put(3,score3);
        cubesMap.put(4,score4);
        cubesMap.put(5,score5);
        cubesMap.put(6,score6);
    }

    public int getActualScore() {
        return actualScore;
    }

    public ImageView getActualView() {

        return new ImageView(actualImage);
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }

    public void cubeThrow () {
        actualScore = random.nextInt(5)+1;
        actualImage = cubesMap.get(actualScore);
    }
}

