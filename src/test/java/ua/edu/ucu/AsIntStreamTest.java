package ua.edu.ucu;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;

import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private static AsIntStream empty;
    private static AsIntStream oneElement;
    private static AsIntStream standart;

    @BeforeClass
    public static void setUp() {
        empty = (AsIntStream) AsIntStream.of(new int[0]);
        oneElement = (AsIntStream) AsIntStream.of(new int[]{1});
        standart = (AsIntStream) AsIntStream.of(new int[]{0, 1, -2, 3, 1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOperationException() {
        Double resultOne = empty.average();
        Integer resultTwo = empty.max();
        Integer resultThree = empty.min();
        Integer resultTwFour = empty.sum();
    }

    @Test
    public void testOperation() {
        Double expResultAverageOneElement = 1.0;
        Double actualResultAverageOneElement = oneElement.average();
        boolean testOne = (actualResultAverageOneElement.equals(expResultAverageOneElement));
        Double expResultAverageStandart = 0.6;
        Double actualResultAverageStandart = standart.average();
        boolean testTwo = (actualResultAverageStandart.equals(expResultAverageStandart));
        Integer expResultMaxOneElement = 1;
        Integer actualResultMaxOneElement = oneElement.max();
        boolean testThree = (actualResultMaxOneElement.equals(expResultMaxOneElement));
        Integer expResultMaxStandart = 3;
        Integer actualResultMaxStandart = standart.max();
        boolean testFour = (actualResultMaxStandart.equals(expResultMaxStandart));
        Integer expResultMinOneElement = 1;
        Integer actualResultMinOneElement = oneElement.min();
        boolean testFive = (actualResultMinOneElement.equals(expResultMinOneElement));
        Integer expResultMinStandart = -2;
        Integer actualResultMinStandart = standart.min();
        boolean testSix = (actualResultMinStandart.equals(expResultMinStandart));
        Integer expResultSumOneElement = 1;
        Integer actualResultSumOneElement = oneElement.sum();
        boolean testSeven = (actualResultSumOneElement.equals(expResultSumOneElement));
        Integer expResultSumStandart = 3;
        Integer actualResultSumStandart = standart.sum();
        boolean testEight = (actualResultSumStandart.equals(expResultSumStandart));
    }

    @Test
    public void testLength() {
        long expResultEmpty = 0;
        long actualResultEmpty = empty.count();
        assertEquals(expResultEmpty, actualResultEmpty);
        long expResultOneElement = 1;
        long actualResultOneElement = oneElement.count();
        assertEquals(expResultOneElement, actualResultOneElement);
        long expResultStandart = 5;
        long actualResultStandart = standart.count();
        assertEquals(expResultStandart, actualResultStandart);
    }
}
