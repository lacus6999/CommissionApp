package src.io;

import src.commission.CommissionType;
import src.commission.dto.CommissionRawData;
import src.logger.Logger;

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

    public List<CommissionRawData> readAndParseToRawData(File resource) throws IOException {
        List<CommissionRawData> commissions = new ArrayList<>();
        Scanner scanner = new Scanner(resource, StandardCharsets.ISO_8859_1);
        Logger.getLogger().DEBUG("Parsing commissions to raw commission data.");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split("\\|");
            CommissionType commissionType = CommissionType.valueOf(data[0]);
            String businessAssociate = data[1];
            double amount = Double.parseDouble(data[2]);
            CommissionRawData commissionRawData = new CommissionRawData(commissionType, businessAssociate, amount);
            commissions.add(commissionRawData);
        }
        scanner.close();
        Logger.getLogger().INFO("Parsing successful. Read " + commissions.size() + " lines of commissions.");
        return commissions;
    }

}
