package pl.traineeship.autoqa.lesson4.animal;

public class Dog extends Animal {
    private static final int RUN_LIMIT = 500;
    private static final int SWIM_LIMIT = 10;
    private static int count = 0;

    public Dog(final String name) {
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
        if (distance <= SWIM_LIMIT) {
            System.out.println(getName() + " swam " + distance + " meters");
        } else {
            System.out.println(getName() + " can't swim more than " + SWIM_LIMIT + " meters");
        }
    }

    public static int getCount() {
        return count;
    }
}
