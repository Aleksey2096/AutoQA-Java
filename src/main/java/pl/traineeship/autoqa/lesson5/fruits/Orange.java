package pl.traineeship.autoqa.lesson5.fruits;

public class Orange extends Fruit implements FruitImplMarker {
    private static final float ORANGE_WEIGHT = 1.5f;

    @Override
    public float getWeight() {
        return ORANGE_WEIGHT;
    }
}
