package sitelogic.TestClasses;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Ieromenko Alexandr on 28.11.2014.
 */
public class InvokeMethod {

    /**
     * This method can invoke any One(1) method with any quantity of args of any type
     * and compare console output with given array of strings
     *
     * @param fileName              filename
     * @param outputMethod          - name of the method to invoke
     * @param outputMethodArgsClass - array of arguments classes
     * @param consoleInput          - console input
     */
    public static ByteArrayOutputStream invoke(String fileName, String outputMethod, String[] outputMethodArgsClass,
                                               String[] consoleInput) {

        // CATCH CONSOLE OUTPUT
        PrintStream saveOut = System.out;  // save ref to sout
        ByteArrayOutputStream out = new ByteArrayOutputStream();  // create  byte array for info
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        //  SIMULATE CONSOLE INPUT
        InputStream streamSave = System.in;
        if (consoleInput != null) {
            InputStream is = new ByteArrayInputStream(consoleInput[0].getBytes());
            System.setIn(is);
        }

        Class[] argsTypes = null;
        Object[] args = null;

        // if method has arguments
        if (outputMethodArgsClass != null) {

            int numOfArgs = outputMethodArgsClass.length;
            argsTypes = new Class[numOfArgs];
            args = new Object[numOfArgs];

            for (int i = 0; i < numOfArgs; i++) {
                try {
                    Class<?> clazz = Class.forName(outputMethodArgsClass[i]); // arg.class

                    if (clazz.getComponentType() != null) {
                        Object mockArray = Array.newInstance(clazz.getComponentType(), 0);//new String[]
                        argsTypes[i] = mockArray.getClass();
                        args[i] = mockArray;
                    } else {
                        argsTypes[i] = clazz;
                        // TODO Instantiation error for primitive wrappers
                        args[i] = clazz.newInstance();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // invoke method
        try {
            Class<?> testClass = Class.forName(fileName);// HashSetTask.class
            // TODO for construction classes that don`t have no args constructors
//          Constructor<?> cons = testClass.getConstructor(new Class[]{});
            Object object = testClass.newInstance(); // instance of HashSetTask for method invocation
            Method method = testClass.getMethod(outputMethod, argsTypes); // gain main
            //TODO what if method return something?
            Object result = method.invoke(object, args);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        String resultOut; // take result of console output // TEST
        resultOut = out.toString(); // TEST

        System.setOut(saveOut);
        System.setIn(streamSave);
//        System.out.println("RESULT OF Main:\n" + resultOut); // TEST
        return out;
    }


}
