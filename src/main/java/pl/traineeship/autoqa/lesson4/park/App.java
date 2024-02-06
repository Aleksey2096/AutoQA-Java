package pl.traineeship.autoqa.lesson4.park;

/*
7. Создать класс Park с внутренним классом, с помощью объектов которого
можно хранить информацию об аттракционах, времени их работы и стоимости.
 */
public class App {
    public static void main(String[] args) {
        Park park = new Park();

        park.addAttraction("Carousel", "9am - 6pm", 10.0);
        park.addAttraction("Boating Lake", "10am - 8pm", 15.0);
        park.addAttraction("Zoo", "8am - 5pm", 20.0);

        park.printAttractions();
        /*
            Carousel - Opening Hours: 9am - 6pm, Cost: $10.0
            Boating Lake - Opening Hours: 10am - 8pm, Cost: $15.0
            Zoo - Opening Hours: 8am - 5pm, Cost: $20.0
         */
    }
}
