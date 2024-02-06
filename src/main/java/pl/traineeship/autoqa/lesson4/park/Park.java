package pl.traineeship.autoqa.lesson4.park;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private List<Attraction> attractions = new ArrayList<>();

    public void addAttraction(String name, String openingHours, double cost) {
        attractions.add(new Attraction(name, openingHours, cost));
    }

    public void printAttractions() {
        for (Attraction attraction : attractions) {
            System.out.println(attraction);
        }
    }

    private class Attraction {
        private String name;
        private String openingHours;
        private double cost;

        public Attraction(String name, String openingHours, double cost) {
            this.name = name;
            this.openingHours = openingHours;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return name + " - Opening Hours: " + openingHours + ", Cost: $" + cost;
        }
    }
}
