package pl.traineeship.autoqa.lesson4.animal;

/*
1. Создать классы Собака и Кот с наследованием от класса Животное.
2. Все животные могут бежать и плыть. В качестве параметра каждому методу передается
длина препятствия. Результатом выполнения действия будет печать в консоль.
(Например, dogBobby.run(150); -> 'Бобик пробежал 150 м.');
3. У каждого животного есть ограничения на действия
(бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
4. * Добавить подсчет созданных котов, собак и животных.
 */
public class App {
    public static void main(String[] args) {
        Animal catJane = new Cat("Jane");
        Animal catJill = new Cat("Jill");
        Animal dogBob = new Dog("Bob");
        Animal dogRex = new Dog("Rex");

        catJane.run(100);   // Jane ran 100 meters
        catJill.run(250);   // Jill can't run more than 200 meters
        catJill.swim(5);    // Jill can't swim

        dogBob.run(150);    // Bob ran 150 meters
        dogBob.run(550);    // Bob can't run more than 500 meters
        dogRex.swim(7);     // Rex swam 7 meters
        dogRex.swim(70);    // Rex can't swim more than 10 meters

        System.out.println(Cat.getCount());     // 2
        System.out.println(Dog.getCount());     // 2
        System.out.println(Animal.getCount());  // 4
    }
}
