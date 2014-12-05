package ioTesting.Task;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TagParser {
    public static void main(String[] args) {

        //  initialization
        Scanner in = new Scanner(System.in);
        String fileName = in.next();
        in.close();
        String openTag = "<" + args[0];
        String closeTag = "</" + args[0] + ">";
        String result = "";
        List<Tag> tagList = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))
        ){
            //  create string to work with
            while (reader.ready()) {
                result += reader.readLine();
            }
                //   search for all tags
            int openIndex = -1;
            int closeIndex = -1;

            while (true) {
                openIndex = result.indexOf(openTag, openIndex + 1); // search for all tags
                closeIndex = result.indexOf(closeTag, closeIndex + 1);
                if (openIndex == -1 || closeIndex == -1)
                    break;
                tagList.add(new Tag(openIndex, closeIndex));  // add tags to list
            }

            //  moving pairs of number
            if (tagList.size() > 1) {
                for (int i = 0; i < tagList.size(); i++) {
                    for (int j = i + 1; j < tagList.size(); j++)
//                    if i+1  higher than i
                        if (tagList.get(i).closeIndex > tagList.get(j).openIndex) {
//                        exchange
                            int temp = tagList.get(i).closeIndex;
                            int temp2 = tagList.get(j).closeIndex;
                            tagList.get(i).setCloseIndex(temp2);
                            tagList.get(j).setCloseIndex(temp);
                        }

                }
                //  OUTPUT
                for (Tag t : tagList){
                    System.out.println(result.substring(t.openIndex, t.closeIndex + args[0].length() + 3));
                }
            } else {
                System.out.println(result.substring(tagList.get(0).openIndex, tagList.get(0).closeIndex + args[0].length() + 3));
            }

        } catch (IOException e) {
            /*NOP*/
        }


    }
/*
Class Tag representing pair of indices of start tag and end tag
 */
    public static class Tag {
        private int openIndex;
        private int closeIndex;

        public int getOpenIndex() {
            return openIndex;
        }

        public void setOpenIndex(int openIndex) {
            this.openIndex = openIndex;
        }

        public int getCloseIndex() {
            return closeIndex;
        }

        public void setCloseIndex(int closeIndex) {
            this.closeIndex = closeIndex;
        }

        public Tag(int openIndex, int closeIndex) {

            this.openIndex = openIndex;
            this.closeIndex = closeIndex;
        }
    }
}
//          ALGORITHM
//  0 4   2 13  5 14  7 15  9 16   11  17 //
//  0 13  2 4   5 14  7 15  9 16   11  17 // 4  > 2
//  0 14  2 4   5 13  7 15  9 16   11  17 // 13 > 5
//  0 15  2 4   5 13  7 14  9 16   11  17 // 14 > 7
//  0 16  2 4   5 13  7 14  9 15   11  17 // 15 > 9
//  0 17  2 4   5 13  7 14  9 15   11  16 // 16 > 11
//  0 17  2 4   5 14  7 13  9 15   11  16 // 13 > 7
//  0 17  2 4   5 15  7 13  9 14   11  16 // 14 > 9
//  0 17  2 4   5 16  7 13  9 14   11  15 // 15 > 11
//  0 17  2 4   5 16  7 14  9 13   11  15 // 13 > 9
//  0 17  2 4   5 16  7 15  9 13   11  14 // 14 > 11
//  0 17  2 4   5 16  7 15  9 14   11  13 // 13 > 11

//            ( 1 ( 2 ) ( 3 ( 4 ( 5  (  6  )  )  )  )  )
//             0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17