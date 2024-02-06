package pl.traineeship.autoqa.lesson4.payment;

/*
6. Создать класс Payment с внутренним классом,
с помощью объектов которого можно сформировать покупку из нескольких товаров.
 */
public class App {
    public static void main(String[] args) {
        Payment payment = new Payment();

        payment.addProduct("Product 1", 250.5);
        payment.addProduct("Product 2", 36.78);
        payment.addProduct("Product 3", 400);

        payment.printProducts();
        /*
            Product{name='Product 1', price=250.5}
            Product{name='Product 2', price=36.78}
            Product{name='Product 3', price=400.0}
         */
    }
}
