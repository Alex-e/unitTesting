package undefined.bonus02;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SolutionTest {
    Solution.Thread1 thread1 = new Solution.Thread1();
    Solution solution = new Solution();

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testTest() throws Exception {
        Thread a =  solution.threads.get(0);
        a.start();
    }


}