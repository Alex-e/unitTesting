package sitelogic.TestClasses;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by Ieromenko Alexandr on 30.11.2014.
 */

public class CheckConsoleOutput {

    /**
     * @param params
     * @param outputResult
     *
     */
    public static String[] check(HashMap<String, String[]> params, ByteArrayOutputStream outputResult) {

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
        String[] refOutput = params.get(String.valueOf(Param.referenceOut));


        String s = outputResult.toString();
        String[] realOutput = s.split("\\r?\\n");
        boolean success;

        if (!outputInOrder || timesRepeatOutput > 1) {
            success = complexMatcher(realOutput, refOutput, timesRepeatOutput);
        } else {
            success = simpleMatcher(realOutput, refOutput);
        }

        return new String[]{"CheckConsoleOutput", "" + success};
    }

    /**
     * @param realOutput
     * @param refOutput
     * @return
     */
    private static boolean simpleMatcher(String[] realOutput, String[] refOutput) {
        for (int i = 0; i < refOutput.length; i++) {
            if (!refOutput[i].equals(realOutput[i])) return false;
        }
        return true;
    }

    /**
     * this matcher is stable against duplicates in refOutput array.
     * If there is any duplicates it will properly count them and then use in compare.
     *
     * @param realOutput
     * @param refOutput
     * @param timesRepeatOutput
     * @return everything is ok?
     */
    private static boolean complexMatcher(String[] realOutput, String[] refOutput, int timesRepeatOutput) {
        HashMap<String, Integer> values = new HashMap<>();
        //check for duplicates
        for (String s : refOutput) {
            if (values.containsKey(s)) continue;
            int counter = 0;
            for (String t : refOutput) {
                if (t.equals(s)) {
                    counter++;
                    values.put(t, counter);
                }
            }
        }

        int counter = 0;
        for (String s : refOutput) {
            int c = values.get(s) * timesRepeatOutput;
            if (c == 0) continue;
            for (String t : realOutput) {
                if (s.equals(t)) {
                    counter++;
                    if (c > 0) {
                        c--;
                        values.put(t, c);
                    }
                }
            }
        }
        return counter == refOutput.length * timesRepeatOutput;
    }


}
