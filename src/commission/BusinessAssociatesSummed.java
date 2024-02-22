package src.commission;

import java.util.HashMap;
import java.util.Map;

public class BusinessAssociatesSummed implements CommissionToXMLParsingObject {
    public BusinessAssociatesSummed() {
        businessAssociates = new HashMap<>();
    }

    private final Map<String, Double> businessAssociates;

    public Double findCommissionByAssociateName(String associateName) {
        return businessAssociates.get(associateName);
    }

    public void addCommissionToAssociate(String associateName, double commissionAmount) {
        businessAssociates.put(associateName, businessAssociates.getOrDefault(associateName, 0.0) + commissionAmount);
    }
    public void addBusinessAssociate(String associateName, Double commission) {
        businessAssociates.put(associateName, commission);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String associateName : businessAssociates.keySet()) {
            stringBuilder.append("Business Associate: ").append(associateName).append("\nCommission:");
            stringBuilder.append(businessAssociates.get(associateName));
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
