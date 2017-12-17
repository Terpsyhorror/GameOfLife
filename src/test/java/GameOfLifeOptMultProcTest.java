import org.junit.Before;
import org.junit.Test;

/**
 * Created by Mintas on 12/10/2017.
 */
public class GameOfLifeOptMultProcTest extends GameOfLifeOptTest {

    @Before
    public void before() {
        gameOfLife = new GameOfLifeImplOptMultProc(2);
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
