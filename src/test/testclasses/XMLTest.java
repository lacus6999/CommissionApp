package test.testclasses;

import main.calculator.Million;
import main.commission.CommissionType;
import main.commission.dto.BusinessAssociatesSummed;
import main.commission.dto.CommissionRawData;
import main.commission.parser.CommissionParserSummed;
import main.xml.XML;
import test.helper.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static test.helper.Assertions.assertEquals;

public class XMLTest {


    private XML xml = new XML(StandardCharsets.ISO_8859_1);

    @Test
    public void parseSimpleObject() {
        SimpleClass simpleClass = new SimpleClass();
        String expectedXML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<SimpleClass>\n" +
                "\t<name>\n" +
                "\t\ttestName\n" +
                "\t</name>\n" +
                "</SimpleClass>";
        assertEquals(expectedXML, xml.parseObjectToXML(simpleClass, String.class));
    }

    @Test
    public void parseBusinessAssociateSummed() {
        List<CommissionRawData> commissionRawData = new ArrayList<>();
        String businessAssociate1 = "Business Associate 1";
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate1, Million.of(1)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate1, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate1, Million.of(2)));

        String businessAssociate2 = "Business Associate 2";
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate2, Million.of(8)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate2, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate2, Million.of(2)));

        String businessAssociate3 = "Business Associate 3";
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate3, Million.of(20)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate3, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.A, businessAssociate3, Million.of(2)));

        CommissionParserSummed commissionParserSummed = CommissionParserSummed.getInstance();
        BusinessAssociatesSummed businessAssociatesSummed = (BusinessAssociatesSummed) commissionParserSummed.parse(commissionRawData);
        String result = xml.parseObjectToXML(businessAssociatesSummed, Double.class);
        String expectedXML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
                "<BusinessAssociatesSummed>\n" +
                "\t<Business Associate 1>\n" +
                "\t\t50000.0\n" +
                "\t</Business Associate 1>\n" +
                "\t<Business Associate 3>\n" +
                "\t\t280000.0\n" +
                "\t</Business Associate 3>\n" +
                "\t<Business Associate 2>\n" +
                "\t\t145000.0\n" +
                "\t</Business Associate 2>\n" +
                "</BusinessAssociatesSummed>";
        assertEquals(expectedXML, result);
    }

}
