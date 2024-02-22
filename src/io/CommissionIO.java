package src.io;

import src.commission.CommissionRawData;
import src.commission.CommissionToXMLParsingObject;
import src.xml.XML;

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
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        File resource = new File(path);
        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource doesn't exist");
        }
        return CommissionReader.getInstance().parseCommissions(resource);
    }

    public void saveCommissions(CommissionToXMLParsingObject businessAssociates) throws IOException {
        File outputFile = new File("BusinessAssociates.xml");
        XML xml = new XML(StandardCharsets.ISO_8859_1);
        String xmlString = xml.parseObjectToXML(businessAssociates, Double.class);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.append(xmlString);
        }
    }
}
