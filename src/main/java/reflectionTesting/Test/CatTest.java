package reflectionTesting.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reflectionTesting.Task.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
Тест задачи level05.lesson05.reflectionTesting
проверяются:
- Все поля, их модификаторы, имена, класс
- Реализация метода указанного в задаче
 имя, модификатор, принимаемые аргументы, тип возвращаемого объекта.
- Проверяется выполнение условия если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
- Если метод выполняется дольше 10 секунд - тест завален
 */
public class CatTest extends Assert {
    Field name = getField("name");
    Field age = getField("age");
    Field weight = getField("weight");
    Field strength = getField("strength");
    Cat a = new Cat();
    Cat b = new Cat();

    @Before
    public void setUp() throws Exception {
        // initializing cats
        a.name = "Vaska";
        a.age = 5;
        a.weight = 7;
        a.strength = 8;
        b.name = "Murka";
        b.age = 3;
        b.weight = 4;
        b.strength = 5;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(timeout = 10000)
    public void fightTest(){
        boolean test = a.fight(b);
        if (test) {
            assertFalse(b.fight(a));
        } else {
            assertTrue(b.fight(a));
        }
    }

    @Test
    public void testTest() throws Exception {
        // test fight method parameters
        Method fightMethod = getFightMethod();
        assertEquals(Modifier.PUBLIC, fightMethod.getModifiers());
        assertEquals(boolean.class, fightMethod.getReturnType());
        assertEquals(Cat.class, getParameter());  // method arguments

        // test fields
        assertNotNull(name);
        assertEquals(String.class, name.getType());
        assertEquals(Modifier.PUBLIC, name.getModifiers()); // private access

        assertNotNull(age);
        assertEquals(int.class, age.getType());
        assertEquals(Modifier.PUBLIC, age.getModifiers());

        assertNotNull(weight);
        assertEquals(int.class, weight.getType());
        assertEquals(Modifier.PUBLIC, weight.getModifiers());

        assertNotNull(strength);
        assertEquals(int.class, strength.getType());
        assertEquals(Modifier.PUBLIC, strength.getModifiers());


    }


    private Field getField(String name) {
        Field[] fields = Cat.class.getDeclaredFields();
        for (Field field: fields) {
            if (field.getName().equalsIgnoreCase(name)) return field;
        }
        return null;
    }

    private Method getFightMethod() {
        Method[] methods = Cat.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equalsIgnoreCase("fight")) return method;
        }

        return null;
    }

    private Class getParameter() {
        Class[] params = getFightMethod().getParameterTypes();
        for (Class param : params) {
            if (param.getSimpleName().equalsIgnoreCase("cat")) return param;
        }
        return null;
    }


}