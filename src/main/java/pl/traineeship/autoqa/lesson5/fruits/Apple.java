package pl.traineeship.autoqa.lesson5.fruits;

public class Apple extends Fruit implements FruitImplMarker {
    private static final float APPLE_WEIGHT = 1.0f;

    @Override
    public float getWeight() {
        return APPLE_WEIGHT;
    }
}
