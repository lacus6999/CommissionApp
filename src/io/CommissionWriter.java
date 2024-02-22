package src.io;

import src.commission.CommissionType;
import src.xml.XML;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CommissionWriter {
    private static final CommissionWriter instance = new CommissionWriter();
    private CommissionWriter() {
    }
    public static CommissionWriter getInstance() {
        return instance;
    }
    public void writeCommissionTable(File commissionTable) {

    }

}
