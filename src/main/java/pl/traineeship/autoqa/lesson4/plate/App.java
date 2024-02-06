package pl.traineeship.autoqa.lesson4.plate;

/*
5. Расширить задачу про котов и тарелки с едой, выполнив следующие пункты:
● Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
(например, в миске 10 еды, а кот пытается покушать 15-20).
● Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны).
Если коту удалось покушать (хватило еды), сытость = true.
● Считаем, что если коту мало еды в тарелке, то он её просто не трогает,
то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
● Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки
и потом вывести информацию о сытости котов в консоль.
● Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
 */
public class App {
    public static void main(String[] args) {
        Cat[] catArr = {
                new Cat("Duffy", 15),
                new Cat("Millie", 10),
                new Cat("Ellie", 8),
                new Cat("Gigi", 17),
                new Cat("Beverly", 14),
                new Cat("Sally", 16),
                new Cat("Roxie", 13),
                new Cat("Piper", 19),
                new Cat("Maisy", 22),
                new Cat("Fluffy", 12),
        };

        Plate plate = new Plate(100);

        for (Cat cat : catArr) {
            cat.eat(plate);
        }

        for (Cat cat : catArr) {
            System.out.println(cat);
        }
        /*
            Cat{name='Duffy', bellyful=true}
            Cat{name='Millie', bellyful=true}
            Cat{name='Ellie', bellyful=true}
            Cat{name='Gigi', bellyful=true}
            Cat{name='Beverly', bellyful=true}
            Cat{name='Sally', bellyful=true}
            Cat{name='Roxie', bellyful=true}
            Cat{name='Piper', bellyful=false}
            Cat{name='Maisy', bellyful=false}
            Cat{name='Fluffy', bellyful=false}
         */

        System.out.println(plate);  // Plate{food=7}

        plate.addFood(30);

        System.out.println(plate);  // Plate{food=37}
    }
}
