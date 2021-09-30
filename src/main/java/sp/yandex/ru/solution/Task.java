package sp.yandex.ru.solution;

import java.util.Arrays;
import java.util.List;

public class Task {
    private static final int MIN_PRICE = 400;
    private static final int MAX_DISTANCE_OF_FRAGILITY_CARGO = 30;
    private static final int FRAGILITY_PRICE = 300;
    private static final int LESS_TWO_DISTANCE_PRICE = 50;
    private static final int LESS_TEN_DISTANCE_PRICE = 100;
    private static final int LESS_THIRTY_DISTANCE_PRICE = 200;
    private static final int MORE_THIRTY_DISTANCE_PRICE = 300;
    private static final List<Double> LOAD_COEFFICIENTS = Arrays.asList(1.6, 1.4, 1.2);


    public int countCostDelivery(int distance, boolean isLargeDimensions, boolean isFragility, double workload) {

        verifyRequirements(distance, isFragility);

        int total_price = 0;

        total_price += countDistancePrice(distance);

        total_price += countDimensionsPrice(isLargeDimensions);

        total_price += countFragilityPrice(isFragility);

        total_price *= countWorkloadPrice(workload);

        return countFinalPrice(total_price);
    }

    private void verifyRequirements(int distance, boolean fragility) {
        if (fragility && distance > MAX_DISTANCE_OF_FRAGILITY_CARGO) {
            throw new IllegalArgumentException(
                String.format("Fragile cargo cannot be shipped further than %s km", MAX_DISTANCE_OF_FRAGILITY_CARGO));
        }
    }

    private int countDistancePrice(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance should be positive");
        }

        if (distance < 2) {
            return LESS_TWO_DISTANCE_PRICE;
        } else if (distance < 10) {
            return LESS_TEN_DISTANCE_PRICE;
        } else if (distance < 30) {
            return LESS_THIRTY_DISTANCE_PRICE;
        } else {
            return MORE_THIRTY_DISTANCE_PRICE;
        }
    }

    private int countDimensionsPrice(boolean isLargeDimensions) {
        return isLargeDimensions ? 200 : 100;
    }

    private int countFragilityPrice(boolean isFragility) {
        return isFragility ? FRAGILITY_PRICE : 0;
    }

    private double countWorkloadPrice(double workload) {
        if (workload < 0) {
            throw new IllegalArgumentException("Workload coefficient should be positive");
        }

        return LOAD_COEFFICIENTS.contains(workload) ? workload : 1.0;
    }

    private int countFinalPrice(int total_price) {
        return Math.max(total_price, MIN_PRICE);
    }

}
