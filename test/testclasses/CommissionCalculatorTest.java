package test.testclasses;


import main.calculator.Million;
import main.commission.CommissionType;
import test.helper.Test;

import static main.calculator.CommissionCalculator.calculateOnePercent;
import static main.calculator.CommissionCalculator.findCommissionForItem;
import static test.helper.Assertions.assertEquals;
import static test.helper.Assertions.assertNotEquals;

public class CommissionCalculatorTest {

    @Test
    public void MillionCalculatorTest() {
        assertEquals(0D, Million.of(0D));
        assertEquals(1000000D, Million.of(1));
        assertEquals(3500000D, Million.of(3.5));
        assertNotEquals(0, Million.of(3.5));
    }

    @Test
    public void calculateOnePercentTest() {
        assertEquals(1D, calculateOnePercent(100));
        assertEquals(0.01, calculateOnePercent(1D));
        assertEquals(10000D, calculateOnePercent(Million.of(1)));
    }
    @Test
    public void testCommissionCalculator_typeA() {
        assertEquals(0D, findCommissionForItem(CommissionType.A, 0D));
        assertEquals(0D, findCommissionForItem(CommissionType.A, 550.0));
        assertEquals(0D, findCommissionForItem(CommissionType.A, 9999999D));
        assertEquals(25000.0, findCommissionForItem(CommissionType.A, Million.of(10)));
        assertEquals(25000.0, findCommissionForItem(CommissionType.A, Million.of(15)));
        assertEquals(25000.0, findCommissionForItem(CommissionType.A, 19999999));
        assertEquals(40000.0, findCommissionForItem(CommissionType.A, Million.of(20)));
        assertEquals(40000.0, findCommissionForItem(CommissionType.A, Million.of(200)));
    }
    @Test
    public void testCommissionCalculator_typeB() {
        assertEquals(0D, findCommissionForItem(CommissionType.B, 0D));
        assertEquals(0D, findCommissionForItem(CommissionType.B, 550.0));
        assertEquals(0D, findCommissionForItem(CommissionType.B, 7999999D));
        assertEquals(30000.0, findCommissionForItem(CommissionType.B, Million.of(8)));
        assertEquals(30000.0, findCommissionForItem(CommissionType.B, Million.of(15)));
        assertEquals(30000.0, findCommissionForItem(CommissionType.B, 15999999));
        assertEquals(50000.0, findCommissionForItem(CommissionType.B, Million.of(16)));
        assertEquals(50000.0, findCommissionForItem(CommissionType.B, Million.of(200)));
    }
    @Test
    public void testCommissionCalculator_typeC() {
        assertEquals(0D, findCommissionForItem(CommissionType.C, 0D));
        assertEquals(0D, findCommissionForItem(CommissionType.C, 550.0));
        assertEquals(0D, findCommissionForItem(CommissionType.C, 4999999D));
        assertEquals(20000.0, findCommissionForItem(CommissionType.C, Million.of(5)));
        assertEquals(20000.0, findCommissionForItem(CommissionType.C, Million.of(8)));
        assertEquals(20000.0, findCommissionForItem(CommissionType.C, 9999999D));
        assertEquals(40000.0, findCommissionForItem(CommissionType.C, Million.of(10)));
        assertEquals(40000.0, findCommissionForItem(CommissionType.C, Million.of(200)));
    }

}