package sitelogic;

import sitelogic.TestClasses.Param;
import sitelogic.TestClasses.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 */
public class BeerTaskTestRunner {
    public static void main(String[] args) {
        Map<Param, String[]> map = new HashMap<>();
        map.put(Param.file, new String[]{"reflectionTesting.Task.BeerTask"});
        map.put(Param.reflect, new String [] {});
//        map.put(Param.usedClass, new String[]{""});
        map.put(Param.reflectMethodName, new String [] {
           "public default protected static void main(String[]) throws Exception"
        });


        // Run
        HashMap<String, String> result = TestRunner.testMethod(map);

        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}
