package pl.traineeship.autoqa.lesson4.animal;

public abstract class Animal {
    private static int count = 0;
    private final String name;

    public Animal(final String name) {
        this.name = name;
        ++count;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public String getName() {
        return name;
    }

    public static int getCount() {
        return count;
    }
}
