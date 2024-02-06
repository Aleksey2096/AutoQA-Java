package pl.traineeship.autoqa.lesson4.plate;

public class Cat {
    private String name;
    private int appetite;
    private boolean bellyful = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
        bellyful = p.decreaseFood(appetite);
    }

    @Override
    public String toString() {
        return "Cat{" + "name='" + name + '\'' + ", bellyful=" + bellyful + '}';
    }
}

