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
        Map<Param, String[]> map = new HashMap<>();
        map.put(Param.file, new String[]{"undefinedTask.HashSetTask"});
        map.put(Param.checkSysOut, new String[]{"true"});
        map.put(Param.methodName, new String[]{"main"});
        map.put(Param.methodArgsClass, new String[]{"[Ljava.lang.String;"});

        // каждое значение это новая строка!!!
        // если значения не должны идти с новой строки они записываются одной строкой!
        map.put(Param.referenceOut, new String[]{"арбуз", "банан", "вишня", "груша", "дыня",
                "ежевика", "жень-шень", "земляника", "ирис", "картофель"});
        map.put(Param.repeatOut, new String[]{"1"});
        map.put(Param.orderOut, new String[]{"false"});

        map.put(Param.usedClass, new String[]{"java.util.HashSet", "java.util.Iterator"});

        //Run
        HashMap<String, String> result = TestRunner.testMethod(map);

        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}
