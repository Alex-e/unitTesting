package sitelogic;

import sitelogic.TestClasses.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 * Тест имитирует запуск тестового механизма на основании принятого HashMap с информацией
 * о классе для тестирования, и параметрах тестирования
 */
public class AlgorithmTask2TestRunner {
    public static void main(String[] args) {
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        map.put("file", new String[] { "ioTesting.Task.AlgorithmTask2" });

        map.put("systemOut", new String [] {"true"});
        map.put("methodName", new String [] {"main"});
        map.put("methodArgsClass", new String [] {"[Ljava.lang.String;"} );
        //TODO write args values
        map.put("methodArgs", new String[] {});
        map.put("repeat", new String [] {"1"});
        map.put("order", new String [] {"true"});
        map.put("refOut", new String [] {"Арбуз", "3", "Боб", "2", "Вишня", "1", "0", "Яблоко"});

        // second \n is simulating empty new line
        map.put("systemIn", new String [] {"Вишня\n1\nБоб\n3\nЯблоко\n2\n0\nАрбуз\n\n" });

        // ЗАПУСК ТЕСТИРОВНИЯ
        HashMap<String, String> result = TestRunner.testMethod(map);

//        some variants of the messages
        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
        System.out.println("===============");
        for (Map.Entry<String, String> pair : result.entrySet()) {
            if (pair.getKey().equalsIgnoreCase("false")) {
                System.out.println("Task not complete!");
                System.out.print(pair.getValue());
            } else {
                System.out.println("Task complete!");
            }
        }
//
    }
}
