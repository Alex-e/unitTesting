package sitelogic;

import sitelogic.TestClasses.Param;
import sitelogic.TestClasses.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 */
public class HashSetTaskTestRunner {
    public static void main(String[] args) {
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        map.put(String.valueOf(Param.file), new String[]{"undefined.HashSetTask"});
        map.put(String.valueOf(Param.checkSysOut), new String[]{"true"});
        map.put(String.valueOf(Param.methodName) , new String[]{"main"});
        map.put(String.valueOf(Param.methodArgsClass), new String[]{"[Ljava.lang.String;"});

        // каждое значение это новая строка!!!
        // если значения не должны идти с новой строки они записываются одной строкой!
        map.put(String.valueOf(Param.referenceOut), new String[]{"арбуз", "банан", "вишня", "груша", "дыня",
                "ежевика", "жень-шень", "земляника", "ирис", "картофель"});
        map.put(String.valueOf(Param.repeatOut) , new String[]{"1"});
        map.put(String.valueOf(Param.orderOut), new String[]{"false"});

        map.put(String.valueOf(Param.usedClass), new String[]{"java.util.HashSet", "java.util.Iterator"});

        HashMap<String, String> result = TestRunner.testMethod(map);

        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
//        System.out.println("===============");
//        for (Map.Entry<String, String> pair : result.entrySet()) {
//            if (pair.getKey().equalsIgnoreCase("fail")) {
//                System.out.println("Task not complete!");
//                System.out.print(pair.getValue());
//            } else {
//                System.out.println("Task complete!");
//
//            }
//        }

    }
}
