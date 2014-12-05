package reflectionTesting.Test;

import org.junit.After;
import org.junit.Test;
import reflectionTesting.Task.InnerClass;

public class InnerClassTest {
    Class[] a = InnerClass.class.getDeclaredClasses(); // show inner classes
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMain() throws Exception {
        System.out.println(a[0].getSimpleName());
    }
}