import java.util.ArrayList;
import java.util.List;

/**
 * Created by daryatretakova on 16.12.17.
 */
public class GameOfLifeImplOptMultProc extends GameOfLifeImplOpt {
    protected int nThreads;
    protected int[][] field;
    protected int[][] nextfield;
//    protected volatile int[][] nextfield;

    public GameOfLifeImplOptMultProc(int nThreads) {
        this.nThreads = nThreads;
    }

    public class MyRunnable implements Runnable {
        private int fLine;
        private int lLine;
        private int n;

        public MyRunnable(int fLine, int lLine, int n) {
            this.fLine = fLine;
            this.lLine = lLine;
            this.n = n;
        }

        @Override
        public void run() {
            generateNextPart(fLine, lLine, n);
        }
    }

    @Override
    protected int[][] generateNext(int[][] tfield, int n) {
        field = tfield;
        nextfield = new int[n + 2][n + 2];
        List<Thread> threads = new ArrayList<>();
        int fLine = 1;
        int lLine = (n + nThreads-1)/nThreads;
        for (int i = 0; i < nThreads; i++) {
            if (lLine > n) {
                lLine = n;
            }
            threads.add(new Thread(new MyRunnable(fLine, lLine, n)));
            fLine = lLine + 1;
            lLine = fLine + (n + nThreads-1)/nThreads - 1;
        }

        for (int i = 0; i < nThreads; i++){
            threads.get(i).start();
        }

        for (int i = 0; i < nThreads; i++){
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        fillThor(nextfield, n);
        return nextfield;
    }

    protected void generateNextPart(int fLine, int lLine, int n) {
        sumNbsPart(fLine, lLine, n);
        for (int i = fLine; i <= lLine; i++) {
            for (int j = 1; j <= n; j++) {
                if (nextfield[i][j] == 3 || (nextfield[i][j] == 2 && field[i][j] == 1)) {
                    nextfield[i][j] = 1;
                } else {
                    nextfield[i][j] = 0;
                }
            }
        }

        // hack for happens-before
        nextfield = nextfield;
    }

    protected void sumNbsPart(int fLine, int lLine, int n) {
        for (int i = fLine; i <= lLine; i++) {
            for (int j = 1; j <= n; j++) {
                nextfield[i][j] = field[i][j - 1] + field[i][j + 1];
                nextfield[i][j] += field[i - 1][j - 1] + field[i - 1][j] + field[i - 1][j + 1];
                nextfield[i][j] += field[i + 1][j - 1] + field[i + 1][j] + field[i + 1][j + 1];
            }
        }
    }
}
