package src.io;

import src.commission.CommissionRawData;
import src.commission.CommissionType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommissionReader {

    private static final CommissionReader instance = new CommissionReader();
    private CommissionReader() {
    }
    public static CommissionReader getInstance() {
        return instance;
    }

    public List<CommissionRawData> parseCommissions(File resource) throws IOException {
        List<CommissionRawData> commissions = new ArrayList<>();
        Scanner scanner = new Scanner(resource, StandardCharsets.ISO_8859_1);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split("\\|");
            CommissionType commissionType = CommissionType.valueOf(data[0]);
            String businessAssociate = data[1];
            double amount = Double.parseDouble(data[2]);
            CommissionRawData commissionRawData = new CommissionRawData(commissionType, businessAssociate, amount);
            commissions.add(commissionRawData);
        }
        return commissions;
    }

}
