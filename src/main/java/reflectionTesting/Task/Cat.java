package reflectionTesting.Task;

/* Реализовать метод fight
Реализовать метод boolean fight(Cat anotherCat):
реализовать механизм драки котов в зависимости от их веса, возраста и силы.
Зависимость придумать самому. Метод должен определять, выиграли ли мы (this) бой или нет,
т.е. возвращать true, если выиграли и false - если нет.
Должно выполняться условие:
если cat1.fight(cat2) = true , то cat2.fight(cat1) = false
*/

public class Cat
{
    public String name;
    public int weight;
    public int age;
    public int strength;

    public boolean fight (Cat anotherCat)
    {
        //Напишите тут ваш код
        int score = 0;
        boolean won = false;
        if(this.strength > anotherCat.strength)
            score++;
        if (this.age > anotherCat.age)
            score++;
        if (this.weight > anotherCat.weight)
            score++;

        return score > 2;
    }
}
