package undefined;

/* Задача №1 на преобразование целых типов
Расставь где нужно оператор приведения типа:
byte a = 1234;
int b = a;
byte c = a * a;
int d = a / c;
*/

public class Casting
{

    public static void main(String[] args)
    {
        byte a = (byte) 1234;
        int b = a;
        byte c = (byte)( a * a);
        int d = a / c;
    }
}
