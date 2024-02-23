package src.commission.parser;

import src.commission.CommissionType;
import src.commission.dto.BusinessAssociates;
import src.commission.dto.CommissionRawData;
import src.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static src.calculator.CommissionCalculator.findCommissionForItem;
import static src.logger.Logger.getLogger;

public class CommissionParserByType implements CommissionParser {

    private static final CommissionParserByType instance = new CommissionParserByType();

    private CommissionParserByType() {
    }

    public static CommissionParserByType getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        BusinessAssociates businessAssociates = new BusinessAssociates();
        Logger logger = getLogger();
        logger.DEBUG("Started parsing to by-type structure.");
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
        logger.INFO("Successfully parsed to usable structure: ");
        logger.INFO(businessAssociates.toString());
        return businessAssociates;
    }

}
