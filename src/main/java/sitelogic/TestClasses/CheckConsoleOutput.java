package sitelogic.TestClasses;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Created by Ieromenko Alexandr on 30.11.2014.
 */

public class CheckConsoleOutput {

    /**
     * @param stream               result of invoking the method
     * @param refOutput            array of output strings to compare with
     * @param outputInOrder        is output must be in order?
     * @param timesRepeatOutput    how many times must be values repeated
     */
    public static String[] check(ByteArrayOutputStream stream, String[] refOutput,
                                 boolean outputInOrder, int timesRepeatOutput) {
        String s = stream.toString();
        String[] realOutput = s.split("\\r?\\n");
        // TODO TEST
//        try {
//            for (int i = 0; i < realOutput.length; i++) {
//                System.out.print(realOutput[i] + " : ");
//                System.out.print(refOutput[i] + "\r\n");
//            }
//        } catch (IndexOutOfBoundsException e) {
//            System.err.println("\nINDEX OUT OF BONDS. JAMES BONDS.");
//        }

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
