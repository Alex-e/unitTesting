package sitelogic.TestClasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Ieromenko Alexandr on 02.12.2014.
 */
public class CheckReflection {

    public static String[] checkRef(HashMap<String, String[]> params) {

        // 1 FILE NAME OF THE CLASS
        String fileName = params.get(String.valueOf(Param.file))[0];

        String[] reflectMethodName = params.get(String.valueOf(Param.reflectMethodName));
        // get declared constructors
        // get declared classes
        // get declared fields
        // get declared methods
        // get declaring class ?
        // get enclosing class ?
        // get interfaces
        // get superclass
        // TODO complete this!!
        try {
            Class<?> clazz = Class.forName(fileName);

            Constructor<?>[] constr = clazz.getDeclaredConstructors();
            System.out.println("constructors: " + Arrays.toString(constr));
            System.out.println(Arrays.toString(constr[0].getParameterTypes()));

            Class[] clazzez = clazz.getDeclaredClasses();
            System.out.println("DECLARED CLASSES: " + Arrays.toString(clazzez));

            Field[] fields = clazz.getDeclaredFields();
            System.out.println("Fields: " + Arrays.toString(fields));

            Method[] methodz = clazz.getDeclaredMethods();
            System.out.println("Methods: " + Arrays.toString(methodz));
            System.out.println("me==" + methodz[0].getName());

            Class encl =  clazz.getEnclosingClass();
            System.out.println("Enclosing classes: " + encl);

            Class<?>[] interfaceez = clazz.getInterfaces();
            System.out.println("Interfasez: " + Arrays.toString(interfaceez));

            Class supercl = clazz.getSuperclass();
            System.out.println("Superclass: " + Arrays.toString(interfaceez));



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new String[] {"", ""};
    }
}
