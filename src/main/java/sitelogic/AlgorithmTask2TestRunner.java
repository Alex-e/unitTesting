package sitelogic;

import sitelogic.TestClasses.Param;
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
        Map<Param, String[]> map = new HashMap<>();
        map.put(Param.file, new String[] { "ioTesting.Task.AlgorithmTask2" });

        map.put(Param.methodName, new String [] {"main"});
        map.put(Param.methodArgsClass, new String [] {"[Ljava.lang.String;"} );
        //TODO write args values
        map.put(Param.methodArgs, new String[] {});

        map.put(Param.checkSysOut, new String [] {"true"});
        map.put(Param.repeatOut, new String [] {"1"});
        map.put(Param.orderOut, new String [] {"true"});
        map.put(Param.referenceOut, new String [] {"Арбуз", "3", "Боб", "2", "Вишня", "1", "0", "Яблоко"});

        // second \n is simulating empty new line
        map.put(Param.systemIn, new String [] {"Вишня\n1\nБоб\n3\nЯблоко\n2\n0\nАрбуз\n\n" });

        // ЗАПУСК ТЕСТИРОВНИЯ
        HashMap<String, String> result = TestRunner.testMethod(map);

//        some variants of the messages
        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}
