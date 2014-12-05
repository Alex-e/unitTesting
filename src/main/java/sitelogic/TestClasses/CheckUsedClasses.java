package sitelogic.TestClasses;

import javassist.ClassPool;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ieromenko Alexandr on 28.11.2014.
 */
public class CheckUsedClasses {

    public static String [] checkForClasses(String fileName, String[] classesToCheck) {
        ClassPool cp = ClassPool.getDefault();
        Collection<String> classes = null;
        try {
            classes = cp.get(fileName).getRefClasses();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> resultList = new ArrayList<>();
        for (String s : classes) {
            for (String checkThis : classesToCheck) {
                if (s.equalsIgnoreCase(checkThis)) {
                    resultList.add(checkThis);
                }
            }
        }

        String[] match = resultList.toArray(new String [classes.size()]);
        boolean compare = compareArrays(classesToCheck, match);

        String message;
        if (compare) {
            message = "Every class is used";
        } else {
            message = "fail";
        }

        return new String [] {"CheckUsedClasses", message };
    }

    private static boolean compareArrays(String[] classesToCheck, String[] resultList) {
        int N = classesToCheck.length;
        int matches = 0;
        for (String s: classesToCheck) {
            for (String t : resultList) {
                if (s.equalsIgnoreCase(t)) matches++;
            }
        }
            return matches == N;
    }
}
