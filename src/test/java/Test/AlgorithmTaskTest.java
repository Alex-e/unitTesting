package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ioTesting.Task.AlgorithmTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/*
Тест задачи по алгоритмам level07.lesson04.task01
проверяется:
- ввод с клавиатуры
- вывод на консоль
- работа каждого метода
- Если метод выполняется дольше 30 секунд - тест завален
 */
public class AlgorithmTaskTest extends Assert {
    //simulate console input
    InputStream streamSave;
    int[] array = new int[20];
    String data;
    InputStream is;

    //catch console output
    PrintStream ps;  // save ref to sout
    ByteArrayOutputStream out;  // create  byte array for info
    PrintStream printStream;

    String result; // take result of console output



    @Before
    public void setUp() throws Exception {
        // input data
        for (int i = 0; i < 20; i++) {
            array[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(array[i]).append("\n");
        }

        data = sb.toString();
        is = new ByteArrayInputStream(data.getBytes());
        streamSave = System.in;
        System.setIn(is);

        //output data
        ps = System.out;  // save ref to sout

        out = new ByteArrayOutputStream();  // create  byte array for info
        printStream = new PrintStream(out);

        System.setOut(printStream);
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(streamSave);
        System.setOut(ps);
    }

    @Test(timeout = 30000)
    public void testMain() throws Exception {
        AlgorithmTask.main(null);
        result = out.toString().trim();
        assertEquals(array[array.length - 1], Integer.parseInt(result));
    }


    @Test(timeout = 30000)
    public void testInitializeArray() throws Exception {
        assertArrayEquals(array, AlgorithmTask.initializeArray());

    }

    @Test(timeout = 30000)
    public void testMax() throws Exception {
        assertEquals(array[array.length - 1], AlgorithmTask.max(array));

    }
}