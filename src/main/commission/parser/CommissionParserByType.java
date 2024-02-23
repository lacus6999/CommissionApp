package main.commission.parser;

import main.commission.CommissionType;
import main.commission.dto.BusinessAssociatesByType;
import main.commission.dto.CommissionRawData;
import main.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.calculator.CommissionCalculator.calculateOnePercent;
import static main.calculator.CommissionCalculator.findCommissionForItem;
import static main.logger.Logger.getLogger;

public class CommissionParserByType implements CommissionParser {

    private static final CommissionParserByType instance = new CommissionParserByType();

    private CommissionParserByType() {
    }

    public static CommissionParserByType getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        BusinessAssociatesByType businessAssociatesByType = new BusinessAssociatesByType();
        Logger logger = getLogger();
        logger.DEBUG("Started parsing to by-type structure.");
        // Filling up {businessAssociates} object with data
        for (CommissionRawData commissionRawData : commissionRawDataList) {
            String businessAssociateName = commissionRawData.getBusinessAssociate();
            BusinessAssociatesByType.Commissions commissions = businessAssociatesByType.findCommissionByAssociateName(businessAssociateName);
            if (commissions != null) {
                Map<CommissionType, Double> commission = commissions.getCommissions();
                double commissionForItem = commissionRawData.getAmount();
                commission.put(commissionRawData.getCommissionType(), commission.getOrDefault(commissionRawData.getCommissionType(), 0D) + commissionForItem);
            } else {
                commissions = new BusinessAssociatesByType.Commissions();
                Map<CommissionType, Double> commission = new HashMap<>();
                commission.put(commissionRawData.getCommissionType(), commissionRawData.getAmount());
                commissions.setCommissions(commission);
                businessAssociatesByType.addBusinessAssociate(businessAssociateName, commissions);
            }
        }
        // Recalculation {businessAssociates} object by its value
        Map<String, BusinessAssociatesByType.Commissions> businessAssociatesMap = businessAssociatesByType.getBusinessAssociates();
        for (String associateName : businessAssociatesMap.keySet()) {
            Map<CommissionType, Double> commissions = businessAssociatesMap.get(associateName).getCommissions();
            for (CommissionType commissionType : commissions.keySet()) {
                Double commission = commissions.get(commissionType);
                commissions.put(commissionType, calculateOnePercent(commission) + findCommissionForItem(commissionType, commission));
            }
        }
        logger.INFO("Successfully parsed to usable structure: ");
        logger.INFO(businessAssociatesByType.toString());
        return businessAssociatesByType;
    }

}
