package src.calculator;

import src.commission.CommissionType;

public class CommissionCalculator {

    public static double findCommissionForItem(CommissionType commissionType, double rawValue) {
        double commissionWithoutBonus = rawValue * 0.01;
        switch (commissionType) {
            case A: {
                if (rawValue < Million.of(10)) return commissionWithoutBonus;
                else if (rawValue < Million.of(20)) return commissionWithoutBonus + 25000;
                else return commissionWithoutBonus + 40000;
            }
            case B: {
                if (rawValue < Million.of(8)) return commissionWithoutBonus + 0;
                else if (rawValue < Million.of(16)) return commissionWithoutBonus + 30000;
                else return commissionWithoutBonus + 50000;
            }
            case C: {
                if (rawValue < Million.of(5)) return commissionWithoutBonus;
                else if (rawValue < Million.of(10)) return commissionWithoutBonus + 20000;
                else return commissionWithoutBonus + 40000;
            }
        }
        throw new IllegalStateException("Invalid CommissionType");
    }
}
