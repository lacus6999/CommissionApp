package main.calculator;

import main.commission.CommissionType;
import main.logger.Logger;

public class CommissionCalculator {

    public static double findCommissionForItem(CommissionType commissionType, double rawValue) {
        switch (commissionType) {
            case A: {
                if (rawValue < Million.of(10)) return 0;
                else if (rawValue < Million.of(20)) return 25000;
                else return 40000;
            }
            case B: {
                if (rawValue < Million.of(8)) return 0;
                else if (rawValue < Million.of(16)) return 30000;
                else return 50000;
            }
            case C: {
                if (rawValue < Million.of(5)) return 0;
                else if (rawValue < Million.of(10)) return 20000;
                else return 40000;
            }
        }
        Logger.getLogger().ERROR("Reached unknown commissionType");
        throw new IllegalStateException("Invalid CommissionType");
    }

    public static double calculateOnePercent(double value) {
        return value * 0.01;
    }

}
