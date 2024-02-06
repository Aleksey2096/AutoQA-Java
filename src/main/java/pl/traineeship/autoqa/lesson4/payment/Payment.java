package pl.traineeship.autoqa.lesson4.payment;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(String name, double price) {
        productList.add(new Product(name, price));
    }

    public void printProducts() {
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product{" + "name='" + name + '\'' + ", price=" + price + '}';
        }
    }
}