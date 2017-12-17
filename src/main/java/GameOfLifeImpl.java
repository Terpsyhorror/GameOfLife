import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by daryatretakova on 16.12.17.
 */
public class GameOfLifeImpl implements GameOfLife {
    @Override
    public List<String> play(String inputFile) {
        Scanner scanner = getScanner(inputFile);
        int n = scanner.nextInt();
        int nGen = scanner.nextInt();
        scanner.nextLine();
        int[][] field = readField(scanner, n);
        for (int i = 0; i < nGen; i++) {
            field = generateNext(field, n);
        }
        List<String> output = generateOutput(field, n);
        return output;
    }

    protected int[][] readField(Scanner scanner, int n) {

        int[][] field = new int[n + 2][n + 2];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < n; j++) {
                field[i + 1][j + 1] = Character.getNumericValue(line.charAt(j));
            }
        }

        fillThor(field, n);
        return field;

    }

    protected Scanner getScanner(String inputFile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            return null;
        }
        Scanner scanner = new Scanner(fileInputStream);
        return scanner;
    }

    protected void fillThor(int[][] field, int n) {
        for (int i = 1; i <= n; i++) {
            field[0][i] = field[n][i];
            field[n+1][i] = field[1][i];
            field[i][0] = field[i][n];
            field[i][n+1] = field[i][1];
        }

        field[0][0] = field[n][n];
        field[n + 1][0] = field[1][n];
        field[0][n + 1] = field[n][1];
        field[n + 1][n + 1] = field[1][1];
    }

    protected int[][] sumNbs(int[][] field, int n) {
        int[][] nbs = new int[n + 2][n + 2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for(int di = -1; di <= 1; di++) {
                    for (int dj = -1; dj <= 1; dj++) {
                        if (di != 0 || dj != 0) {
                            nbs[i][j] += field[i + di][j + dj];
                        }
                    }
                }
            }
        }
        return nbs;
    }

    protected int[][] generateNext(int[][] field, int n) {
        int[][] nbs = sumNbs(field, n);
        int[][] next = nbs;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nbs[i][j] == 3 || (nbs[i][j] == 2 && field[i][j] == 1)) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = 0;
                }

            }
        }
        fillThor(next, n);
        return next;

    }

    protected List<String> generateOutput(int[][] field, int n) {
        List<String> output = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 1; j <= n; j++) {
                stringBuilder.append(field[i][j]);
            }
            output.add(stringBuilder.toString());
        }
        return output;
    }
}
