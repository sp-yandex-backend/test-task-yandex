package sp.yandex.ru.solution;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskTest {

    private Task task;

    @BeforeMethod
    void setUp() {
        task = new Task();
    }
    
    @DataProvider(name = "dpPositiveData")
    public Object[][] getValidDataSet() {
        return new Object[][]{
            {1, false, false, 1.6, 400},
            {2, false, true, 1.4, 700},
            {10, true, true, 1.2, 840},
            {30, true, false, 0.5, 500},
        };

    }

    @Test(dataProvider = "dpPositiveData")
    public void testCountingDeliveryPriceWithValidData(int distance, boolean isLargeDimensions, boolean isFragility,
                                                       double workload, int expectedPrice) {

        int actualPrice = task.countCostDelivery(distance, isLargeDimensions, isFragility, workload);

        Assert.assertEquals(actualPrice, expectedPrice);
    }

    @DataProvider(name = "dpNegativeData")
    public Object[][] getInValidDataSet() {
        return new Object[][]{
            {31, false, true, 1.6},
            {-1, false, false, 1.4},
            {2, true, true, -0.1},
        };

    }

    @Test(dataProvider = "dpNegativeData", expectedExceptions = {IllegalArgumentException.class})
    public void testCountingDeliveryPriceWithInValidData(int distance, boolean isLargeDimensions, boolean isFragility,
                                                         double workload) {

        task.countCostDelivery(distance, isLargeDimensions, isFragility, workload);
    }

}