package sitelogic;

import sitelogic.TestClasses.Param;
import sitelogic.TestClasses.TestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 */
public class AlgorithmTaskTestRunner {
    public static void main(String[] args) {
        HashMap<Param, String[]> map = new HashMap<>();
        map.put(Param.file, new String[] { "ioTesting.Task.AlgorithmTask" });

        map.put(Param.methodName, new String [] {"main"});
        map.put(Param.methodArgsClass, new String [] {"[Ljava.lang.String;"} );

        map.put(Param.checkSysOut, new String []{"true"});
        map.put(Param.referenceOut, new String [] {"200"});
        map.put(Param.repeatOut, new String []{"1"});
        map.put(Param.orderOut, new String []{"false"});
        map.put(Param.systemIn, new String [] {"1\n200\n3\n4\n5\n65\n7\n8\n9\n10\n11\n12\n13\n14\n15\n106\n" +
                "17\n18\n19\n20\n" });

        // RUN TEST
        HashMap<String, String> result = TestRunner.testMethod(map);

        for (Map.Entry<String, String> pair : result.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}
