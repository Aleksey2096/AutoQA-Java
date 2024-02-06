package pl.traineeship.autoqa.lesson4.plate;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (n <= food) {
            food -= n;
            return true;
        }
        return false;
    }

    public void addFood(int n) {
        food += n;
    }

    @Override
    public String toString() {
        return "Plate{" + "food=" + food + '}';
    }
}

