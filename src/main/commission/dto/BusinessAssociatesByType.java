package main.commission.dto;


import main.commission.CommissionType;
import main.commission.parser.CommissionToXMLParsingObject;

import java.util.HashMap;
import java.util.Map;

public class BusinessAssociatesByType implements CommissionToXMLParsingObject {
    public BusinessAssociatesByType() {
        businessAssociates = new HashMap<>();
    }

    private final Map<String, Commissions> businessAssociates;

    public Commissions findCommissionByAssociateName(String associateName) {
        return businessAssociates.get(associateName);
    }

    public void addBusinessAssociate(String associateName, Commissions commissions) {
        businessAssociates.put(associateName, commissions);
    }

    public Map<String, Commissions> getBusinessAssociates() {
        return businessAssociates;
    }

    public static class Commissions {
        public Commissions() {
        }

        private Map<CommissionType, Double> commissions;

        public Map<CommissionType, Double> getCommissions() {
            return commissions;
        }

        public void setCommissions(Map<CommissionType, Double> commissions) {
            this.commissions = commissions;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String associateName : businessAssociates.keySet()) {
            stringBuilder.append("Business Associate: ").append(associateName).append("\nCommissions:");
            for (Map.Entry<CommissionType, Double> entry : businessAssociates.get(associateName).getCommissions().entrySet()) {
                stringBuilder.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
            }
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
