package pl.traineeship.autoqa.lesson5.fruits;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit & FruitImplMarker> {

    private static final double ERROR_LIMIT = 0.0001;

    private List<T> fruits = new ArrayList<>();

    public double getWeight() {
        if (fruits == null || fruits.isEmpty()) {
            return 0;
        }
        return fruits.get(0).getWeight() * fruits.size();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public void pourIntoBox(Box<T> box) {
        for (T fruit : fruits) {
            box.add(fruit);
        }
        fruits.clear();
    }

    public boolean compare(Box<? extends Fruit> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < ERROR_LIMIT;
    }

    public int getNumberOfFruits() {
        return fruits.size();
    }
}
