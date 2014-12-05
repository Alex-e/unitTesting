package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ioTesting.Task.TagParser;

import java.io.*;

/*
Тест задачи на поиск тэгов 19/10/bonus3
тестируются:
- Ввод имени файла из консоли
- Чтение файла с диска
- Вывод результата в консоль
- Сравниваются эталонный результат вычитаный из файла на сервере и результат который дает программа.

Все адреса файлов захардкожены!
Возможны проблемы с символами переноса строки!
 */
public class TagParserTest extends Assert {
    //simulate console input
    InputStream saveIn;
    String data;
    InputStream is;

    //catch console output
    PrintStream saveOut;  // save ref to sout
    ByteArrayOutputStream out;  // create  byte array for info
    PrintStream printStream;

    String result; // take result of console output

    String sample = "";

    @Before
    public void setUp() throws Exception {
        //input data
        data = "\\1.html"; // real file on server
        /*
        Содержимое файла -
        <span : HERE I GO! > SOME text</span> Info about Leela <span xml:lang="en"
        lang="en"><b><span>Turanga Leela</span></b></span><span>another text</span>
         */
        is = new ByteArrayInputStream(data.getBytes());
        saveIn = System.in;
        System.setIn(is);

        //test data
        BufferedReader reader = new BufferedReader(
                                   new InputStreamReader(
                                      new FileInputStream("\\tagParserTestResult.txt"), "UTF-8"));
        /*
        Содержимое файла -
        <span : HERE I GO! > SOME text</span>
        <span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
        <span>Turanga Leela</span>
        <span>another text</span>
         */
        while (reader.ready()) {
            sample += reader.readLine();
            sample += "\r\n";
        }
        reader.close();

        //output data
        saveOut = System.out;  // save ref to sout

        out = new ByteArrayOutputStream();  // create  byte array for info
        printStream = new PrintStream(out);

        System.setOut(printStream);

    }

    @After
    public void tearDown() throws Exception {
        System.setIn(saveIn);
        System.setOut(saveOut);
    }

    @Test
    public void testMain() throws Exception {
        TagParser.main(new String[]{"span"});
        result = out.toString();
        System.err.println(result); // test info
        assertEquals("Strings are different", sample, result);
    }
}