import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by daryatretakova on 16.12.17.
 */
public class GameOfLifeImplOpt extends GameOfLifeImpl {

    @Override
    protected int[][] sumNbs(int[][] field, int n) {
        int[][] nbs = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nbs[i][j] = field[i][j - 1] + field[i][j + 1];
                nbs[i][j] += field[i - 1][j - 1] + field[i - 1][j] + field[i - 1][j + 1];
                nbs[i][j] += field[i + 1][j - 1] + field[i + 1][j] + field[i + 1][j + 1];
            }
        }
        return nbs;
    }

}
