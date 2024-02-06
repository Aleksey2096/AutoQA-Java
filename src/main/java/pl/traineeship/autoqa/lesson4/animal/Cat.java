package pl.traineeship.autoqa.lesson4.animal;

public class Cat extends Animal {
    private static final int RUN_LIMIT = 200;
    private static int count = 0;

    public Cat(final String name) {
        super(name);
        ++count;
    }

    @Override
    public void run(int distance) {
        if (distance <= RUN_LIMIT) {
            System.out.println(getName() + " ran " + distance + " meters");
        } else {
            System.out.println(getName() + " can't run more than " + RUN_LIMIT + " meters");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " can't swim");
    }

    public static int getCount() {
        return count;
    }
}
