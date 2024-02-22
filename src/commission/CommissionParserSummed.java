package src.commission;

import java.util.List;

import static src.calculator.CommissionCalculator.findCommissionForItem;

public class CommissionParserSummed implements CommissionParser {

    private static final CommissionParserSummed instance = new CommissionParserSummed();

    private CommissionParserSummed() {
    }

    public static CommissionParserSummed getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        BusinessAssociatesSummed businessAssociates = new BusinessAssociatesSummed();
        for (CommissionRawData commissionRawData : commissionRawDataList) {
            String businessAssociateName = commissionRawData.getBusinessAssociate();
            double commissionForItem = findCommissionForItem(commissionRawData.getCommissionType(), commissionRawData.getAmount());
            businessAssociates.addCommissionToAssociate(businessAssociateName, commissionForItem);
        }
        return businessAssociates;
    }

}
