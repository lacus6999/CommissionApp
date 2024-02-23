package src;

import src.commission.dto.CommissionRawData;
import src.commission.parser.CommissionParserSummed;
import src.commission.parser.CommissionToXMLParsingObject;
import src.io.CommissionIO;
import src.logger.Logger;

import java.io.IOException;
import java.util.List;

public class CommissionApp {

    public static void main(String[] args) throws IOException {
        new CommissionApp().init();
    }

    public void init() throws IOException {
        Logger.getLogger().INFO("Application started");
        CommissionIO commissionIO = CommissionIO.getInstance();
        List<CommissionRawData> commissions = commissionIO.readCommissions("./src/resources/commission_resource.txt");
        CommissionToXMLParsingObject normalizedBusinessAssociates = CommissionParserSummed.getInstance().parse(commissions);
        commissionIO.saveCommissions(normalizedBusinessAssociates);
        Logger.getLogger().INFO("Application finished");
    }

}
