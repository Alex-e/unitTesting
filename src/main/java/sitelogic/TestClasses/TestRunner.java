package sitelogic.TestClasses;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 * class for parsing reflection
 */
public class TestRunner {
    // TODO add Timeout!!


    public static HashMap<String, String> testMethod(Map<Param, String[]> parameters) {
        HashMap<String, String> result = new HashMap<>();

        //NAME OF THE METHOD TO INVOKE
        String[] methodName = parameters.get(Param.methodName);

        if (methodName != null) {
            // invoke method and take console output
            ByteArrayOutputStream outputResult = InvokeMethod.invoke(parameters);

            // CHECK OUTPUT
            String[] checkOutM = parameters.get(Param.checkSysOut);
            boolean checkOut;
            if (checkOutM == null) {
                checkOut = false;
            } else {
                checkOut = Boolean.valueOf(checkOutM[0]);
            }

            if (checkOut && outputResult != null) {
                // send output result
                String[] checked = CheckConsoleOutput.check(parameters, outputResult);
                result.put(checked[0], checked[1]);
            }
        //TODO      if (checkFile) {}
        }

        // CHECK CLASSES USED
        String[] classesToCheck = parameters.get(Param.usedClass);

        if (classesToCheck != null) {
            String[] checked = CheckUsedClasses.checkForClasses(parameters);
            result.put(checked[0], checked[1]);
        }

        // CHECK REFLECTION
        String[] reflectionList = parameters.get(Param.reflect);

        if (reflectionList != null) {
            String[] checked = CheckReflection.checkRef(parameters);
            result.put(checked[0], checked[1]);
        }

        return result;

    }

}

