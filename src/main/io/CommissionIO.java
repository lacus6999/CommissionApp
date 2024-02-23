package main.io;

import main.commission.dto.CommissionRawData;
import main.commission.parser.CommissionToXMLParsingObject;
import main.logger.Logger;
import main.xml.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CommissionIO {

    private static final CommissionIO instance = new CommissionIO();

    private CommissionIO() {
    }

    public static CommissionIO getInstance() {
        return instance;
    }

    public List<CommissionRawData> readCommissions(String path) throws IOException {
        if (path == null || path.isEmpty()) {
            Logger.getLogger().ERROR("Reading commissions failed. Invalid path.");
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        File resource = new File(path);
        if (!resource.exists()) {
            Logger.getLogger().ERROR("Reading commissions failed. File does not exist.");
            throw new IllegalArgumentException("Resource doesn't exist");
        }
        return CommissionReader.getInstance().readAndParseToRawData(resource);
    }

    public void saveCommissions(CommissionToXMLParsingObject businessAssociates) throws IOException {
        XML xml = new XML(StandardCharsets.ISO_8859_1);
        Logger.getLogger().INFO("Creating XML object.");
        String xmlString = xml.parseObjectToXML(businessAssociates, Double.class);
        Logger.getLogger().INFO("Saving XML file.");
        try (FileWriter fileWriter = new FileWriter("BusinessAssociates.xml")) {
            fileWriter.append(xmlString);
        }
    }
}
