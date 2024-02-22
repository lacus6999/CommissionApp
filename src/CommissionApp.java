package src;

import src.commission.CommissionParserSummed;
import src.commission.CommissionRawData;
import src.commission.CommissionToXMLParsingObject;
import src.io.CommissionIO;

import java.io.IOException;
import java.util.List;

public class CommissionApp {

    public static void main(String[] args) throws IOException {
        new CommissionApp().init();
    }

    public void init() throws IOException {
        CommissionIO commissionIO = CommissionIO.getInstance();
        List<CommissionRawData> commissions = commissionIO.readCommissions("./src/resources/commission_resource.txt");
        CommissionToXMLParsingObject normalizedBusinessAssociates = CommissionParserSummed.getInstance().parse(commissions);
        commissionIO.saveCommissions(normalizedBusinessAssociates);
    }

}
