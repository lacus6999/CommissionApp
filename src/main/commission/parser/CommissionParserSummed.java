package main.commission.parser;

import main.commission.CommissionType;
import main.commission.dto.BusinessAssociatesSummed;
import main.commission.dto.CommissionRawData;
import main.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.calculator.CommissionCalculator.calculateOnePercent;
import static main.calculator.CommissionCalculator.findCommissionForItem;
import static main.logger.Logger.getLogger;

public class CommissionParserSummed implements CommissionParser {

    private static final CommissionParserSummed instance = new CommissionParserSummed();

    private CommissionParserSummed() {
    }

    public static CommissionParserSummed getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        Map<String, Map<CommissionType, Double>> summedItemValue = new HashMap<>();
        Logger logger = getLogger();
        logger.DEBUG("Started parsing to summed structure.");
        for (CommissionRawData commissionRawData : commissionRawDataList) {
            String businessAssociateName = commissionRawData.getBusinessAssociate();
            if (summedItemValue.containsKey(businessAssociateName)) {
                Map<CommissionType, Double> commissionMap = summedItemValue.get(businessAssociateName);
                commissionMap.put(commissionRawData.getCommissionType(), commissionMap.getOrDefault(commissionRawData.getCommissionType(), 0D) + commissionRawData.getAmount());
            } else {
                Map<CommissionType, Double> commissionMap = new HashMap<>();
                commissionMap.put(commissionRawData.getCommissionType(), commissionRawData.getAmount());
                summedItemValue.put(businessAssociateName, commissionMap);
            }
        }
        BusinessAssociatesSummed businessAssociates = new BusinessAssociatesSummed();
        for (String associateName : summedItemValue.keySet()) {
            businessAssociates.addBusinessAssociate(associateName,
                    summedItemValue.get(associateName).entrySet().stream()
                            .map(entry -> calculateOnePercent(entry.getValue()) + findCommissionForItem(entry.getKey(), entry.getValue()))
                            .reduce(0d, Double::sum)
            );
        }
        logger.INFO("Successfully parsed to usable structure: ");
        logger.INFO(businessAssociates.toString());
        return businessAssociates;
    }

}
