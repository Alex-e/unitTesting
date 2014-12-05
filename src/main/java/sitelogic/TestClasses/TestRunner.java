package sitelogic.TestClasses;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by Ieromenko Alexandr on 23.11.2014.
 * class for parsing reflection
 */
public class TestRunner {
    // TODO add Timeout!!


    public static HashMap<String, String> testMethod(HashMap<String, String[]> params) {
        HashMap<String, String> result = new HashMap<>();

        // 1 FILE NAME OF THE CLASS
        String fileName = params.get(String.valueOf(Param.file))[0];

        //2 CHECK OUTPUT
        String[] checkOutM = params.get(String.valueOf(Param.checkSysOut));
        boolean checkOut;
        if (checkOutM == null) {
            checkOut = false;
        } else {
            checkOut = Boolean.valueOf(checkOutM[0]);
        }

        //3 ORDER OF STRINGS OF THE OUTPUT
        String[] outputInOrderM = params.get(String.valueOf(Param.orderOut));
        boolean outputInOrder;
        if (outputInOrderM == null) {
            outputInOrder = false;
        } else {
            outputInOrder = Boolean.valueOf(outputInOrderM[0]);
        }

        //4 REPEAT OUTPUT N TIMES
        String[] timesRepeatOutputM = params.get(String.valueOf(Param.repeatOut));
        int timesRepeatOutput;
        if (timesRepeatOutputM == null) {
            timesRepeatOutput = 1;
        } else {
            timesRepeatOutput = Integer.parseInt(timesRepeatOutputM[0]);
        }
        //5 OUTPUT TO COMPARE WITH
        String[] output = params.get(String.valueOf(Param.referenceOut));

        //7 NAME OF THE METHOD TO INVOKE
        String[] methodName = params.get(String.valueOf(Param.methodName));

        //8 CLASS OF THE ARGUMENTS
        String[] outputMethodArgsClass = params.get(String.valueOf(Param.methodArgsClass));

        //9 ARGUMENTS
        String[] args = params.get(String.valueOf(Param.methodArgs));
        //TODO fill this

        //10 SYSTEM.IN
        String[] consoleInput = params.get(String.valueOf(Param.systemIn));

        if (methodName != null) {
            // invoke method and take console output
            ByteArrayOutputStream outputResult = InvokeMethod.invoke(fileName, methodName[0],
                    outputMethodArgsClass, consoleInput);

            if (checkOut) {
                // send output result
                String[] checked = CheckConsoleOutput.check(outputResult, output,
                        outputInOrder, timesRepeatOutput);
                result.put(checked[0], checked[1]);
            }
        //TODO      if (checkFile) {}
        }

        //11 CHECK CLASSES USED
        String[] classesToCheck = params.get(String.valueOf(Param.usedClass));

        if (classesToCheck != null) {
            String[] checked = CheckUsedClasses.checkForClasses(fileName, classesToCheck);
            result.put(checked[0], checked[1]);
        }

        //12 CHECK REFLECTION
        String[] reflectionList = params.get(String.valueOf(Param.reflect));

        String[] reflectMethodName = params.get(String.valueOf(Param.reflectMethodName));

        if (reflectionList != null) {
            String[] checked = CheckReflection.checkRef(
                    fileName, reflectionList, reflectMethodName);
            result.put(checked[0], checked[1]);
        }

        return result;

    }

}

