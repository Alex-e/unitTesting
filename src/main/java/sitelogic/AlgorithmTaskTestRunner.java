package sitelogic;

import sitelogic.TestClasses.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 */
public class AlgorithmTaskTestRunner {
    public static void main(String[] args) {
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        map.put("file", new String[] { "ioTesting.Task.AlgorithmTask" });

        map.put("systemOut", new String []{"true"});
        map.put("methodName", new String[] {"main"});
        map.put("methodArgsClass", new String [] {"[Ljava.lang.String;"} );
        //TODO write args values
        map.put("methodArgs", new String[] {});
        map.put("refOut", new String [] {"200"});
        map.put("repeat", new String []{"1"});
        map.put("order", new String []{"false"});
        map.put("systemIn", new String [] {"1\n200\n3\n4\n5\n65\n7\n8\n9\n10\n11\n12\n13\n14\n15\n106\n" +
                "17\n18\n19\n20\n" });

//        map.put("Class", new String []{"java.util.HashSet", "java.util.Iterator"});

        HashMap<String, String> result = TestRunner.testMethod(map);
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

    }
}
