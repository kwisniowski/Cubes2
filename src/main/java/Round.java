import java.util.ArrayList;
import java.util.List;

public class Round {
    private int actualPlayerNumber;
    private int getActualPlayerRoundScore;
    private List<Cube> player1Cubes;
    private List<Cube> tableCubes;

    public List<Cube> getTableCubes() {
        return tableCubes;
    }

    public List<Cube> getPlayer1Cubes() {
        return player1Cubes;
    }

    public void setPlayer1Cubes(List<Cube> player1Cubes) {
        this.player1Cubes = player1Cubes;
    }

    public int getActualPlayerNumber() {
        return actualPlayerNumber;
    }

    public void setActualPlayerNumber(int actualPlayerNumber) {
        this.actualPlayerNumber = actualPlayerNumber;
    }

    public int getGetActualPlayerRoundScore() {
        return getActualPlayerRoundScore;
    }

    public void setGetActualPlayerRoundScore(int getActualPlayerRoundScore) {
        this.getActualPlayerRoundScore = getActualPlayerRoundScore;
    }

    public Round(int actualPlayerNumber) {
        this.actualPlayerNumber = actualPlayerNumber;
        tableCubes = new ArrayList<>();
        player1Cubes = new ArrayList<>();
    }

    public void firstThrow() {
        for (int i=0; i<5; i++) {
            Cube cube = new Cube();
            cube.cubeThrow();
            tableCubes.add(cube);
        }
    }

    public int playRound() {
        firstThrow();

        return getActualPlayerRoundScore;
    }
}
