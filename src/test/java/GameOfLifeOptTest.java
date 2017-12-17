import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mintas on 12/10/2017.
 */
public class GameOfLifeOptTest extends GameOfLifeTest {

    @Before
    public void before() {
        gameOfLife = new GameOfLifeImplOpt();
    }

    @Test
    public void testGame() throws Exception {
        super.testGame();
    }

    @Test
    public void testGame100() throws Exception {
        super.testGame100();
    }

    @Test
    public void testGame1000() throws Exception {
        super.testGame1000();
    }

    @Test
    public void testGame10000() throws Exception {
        super.testGame10000();
    }
}
