package main;


import main.commission.dto.CommissionRawData;
import main.commission.parser.CommissionParserSummed;
import main.commission.parser.CommissionToXMLParsingObject;
import main.io.CommissionIO;
import main.logger.Logger;

import java.io.IOException;
import java.util.List;

public class CommissionApp {

    public static void main(String[] args) throws IOException {
        new CommissionApp().init(args[0]);
    }

    public void init(String resourceName) throws IOException {
        Logger.getLogger().INFO("Application started");
        CommissionIO commissionIO = CommissionIO.getInstance();
        List<CommissionRawData> commissions = commissionIO.readCommissions("./src/resources/" + resourceName);
        CommissionToXMLParsingObject normalizedBusinessAssociates = CommissionParserSummed.getInstance().parse(commissions);
        commissionIO.saveCommissions(normalizedBusinessAssociates);
        Logger.getLogger().INFO("Application finished");
    }

}
