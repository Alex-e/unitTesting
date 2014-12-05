package ioTesting.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Задача по алгоритмам
Задача: Пользователь вводит с клавиатуры список слов (и чисел). Слова вывести в возрастающем порядке, числа - в убывающем.
Пример ввода:
Вишня
1
Боб
3
Яблоко
2
0
Арбуз
Пример вывода:
Арбуз
3
Боб
2
Вишня
1
0
Яблоко
*/

public class AlgorithmTask2
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<String>();
        while (true)
        {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        String[] array = list.toArray(new String[list.size()]);
        sort(array);

        for (String x : array)
        {
            System.out.println(x);
        }
    }

    public static void sort(String[] array)
    {
        ArrayList<Integer> ints = new ArrayList<Integer>();
        ArrayList<Integer> strings = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {   // looking for ints
            if (isNumber(array[i])) {
                ints.add(i);
//                System.out.println(i + " is int");
            }
        }
        for (int j = 0; j < array.length; j++) { // looking for strings
            if (!isNumber(array[j])) {
                strings.add(j);
//                System.out.println(j + " is string");
            }
        }
        String max;
        for (int i = 0; i < ints.size() ; i++) {
            for (int j = 1; j < ints.size(); j++) {
                    int second = Integer.parseInt(array[ints.get(j)]);
                    int first = Integer.parseInt(array[ints.get(j - 1)]);
//                    if (isGreaterThen(array[ints.get(j)],array[ints.get(j - 1)])) {
                        if (second > first) {
                        max = array[ints.get(j - 1)];
                        array[ints.get(j - 1)] =  array[ints.get(j)];
                        array[ints.get(j)] = max;
                        j--;
//                            System.out.println("something wrong");
                    }

            }
        }
        String min;
        for (int i = 0; i < strings.size() ; i++) {
            for (int j = 1; j < strings.size(); j++) {
                if (isGreaterThen(array[strings.get(j - 1)],array[strings.get(j)])) {
                    min = array[strings.get(j)];
                    array[strings.get(j)] =  array[strings.get(j - 1)];
                    array[strings.get(j - 1)] = min;
                    j--;
                }

            }
        }
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThen(String a, String b)
    {
        return a.compareTo(b) > 0;
    }


    //строка - это на самом деле число?
    public static boolean isNumber(String s)
    {
        if (s.length() == 0) return false;

        for (char c : s.toCharArray())
        {
            if (!Character.isDigit(c) && c != '-') return false;
        }
        return true;
    }
}
