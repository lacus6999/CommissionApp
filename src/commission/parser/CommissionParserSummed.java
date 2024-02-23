package src.commission.parser;

import src.commission.dto.BusinessAssociatesSummed;
import src.commission.dto.CommissionRawData;
import src.logger.Logger;

import java.util.List;

import static src.calculator.CommissionCalculator.findCommissionForItem;
import static src.logger.Logger.getLogger;

public class CommissionParserSummed implements CommissionParser {

    private static final CommissionParserSummed instance = new CommissionParserSummed();

    private CommissionParserSummed() {
    }

    public static CommissionParserSummed getInstance() {
        return instance;
    }

    public CommissionToXMLParsingObject parse(List<CommissionRawData> commissionRawDataList) {
        BusinessAssociatesSummed businessAssociates = new BusinessAssociatesSummed();
        Logger logger = getLogger();
        logger.DEBUG("Started parsing to summed structure.");
        for (CommissionRawData commissionRawData : commissionRawDataList) {
            String businessAssociateName = commissionRawData.getBusinessAssociate();
            double commissionForItem = findCommissionForItem(commissionRawData.getCommissionType(), commissionRawData.getAmount());
            businessAssociates.addCommissionToAssociate(businessAssociateName, commissionForItem);
        }
        logger.INFO("Successfully parsed to usable structure: ");
        logger.INFO(businessAssociates.toString());
        return businessAssociates;
    }

}
