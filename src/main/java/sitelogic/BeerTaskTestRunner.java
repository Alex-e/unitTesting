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
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        map.put(String.valueOf(Param.file), new String[]{"reflectionTesting.Task.BeerTask"});
        map.put(String.valueOf(Param.reflect), new String [] {});
//        map.put(String.valueOf(Param.usedClass), new String[]{""});
        map.put(String.valueOf(Param.reflectMethodName), new String [] {
           "public default protected static final void main(String[]) throws Exception"
        });



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
