package src.commission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.calculator.CommissionCalculator.findCommissionForItem;

public class CommissionParserByType implements CommissionParser {

    private static final CommissionParserByType instance = new CommissionParserByType();

    private CommissionParserByType() {
    }

    public static CommissionParserByType getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        BusinessAssociates businessAssociates = new BusinessAssociates();
        for (CommissionRawData commissionRawData : commissionRawDataList) {
            String businessAssociateName = commissionRawData.getBusinessAssociate();
            BusinessAssociates.Commissions commissions = businessAssociates.findCommissionByAssociateName(businessAssociateName);
            if (commissions != null) {
                Map<CommissionType, Double> commission = commissions.getCommissions();
                double commissionForItem = findCommissionForItem(commissionRawData.getCommissionType(), commissionRawData.getAmount());
                commission.put(commissionRawData.getCommissionType(), commission.getOrDefault(commissionRawData.getCommissionType(), 0D) + commissionForItem);
            } else {
                commissions = new BusinessAssociates.Commissions();
                Map<CommissionType, Double> commission = new HashMap<>();
                commission.put(commissionRawData.getCommissionType(), findCommissionForItem(commissionRawData.getCommissionType(), commissionRawData.getAmount()));
                commissions.setCommissions(commission);
                businessAssociates.addBusinessAssociate(businessAssociateName, commissions);
            }
        }
        return businessAssociates;
    }

}
