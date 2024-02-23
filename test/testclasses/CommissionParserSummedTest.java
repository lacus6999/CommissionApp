package test.testclasses;

import main.calculator.Million;
import main.commission.CommissionType;
import main.commission.dto.BusinessAssociatesSummed;
import main.commission.dto.CommissionRawData;
import main.commission.parser.CommissionParserSummed;
import main.commission.parser.CommissionToXMLParsingObject;
import test.helper.Test;

import java.util.ArrayList;
import java.util.List;

import static test.helper.Assertions.assertEquals;

public class CommissionParserSummedTest {

    CommissionParserSummed commissionParserSummed = CommissionParserSummed.getInstance();

    @Test
    public void testParserSummed_typeA() {
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

        BusinessAssociatesSummed result = (BusinessAssociatesSummed) commissionParserSummed.parse(commissionRawData);
        assertEquals(50000D, result.findCommissionByAssociateName(businessAssociate1));
        assertEquals(Million.of(12) * 0.01 + 25000D, result.findCommissionByAssociateName(businessAssociate2));
        assertEquals(Million.of(24) * 0.01 + 40000D, result.findCommissionByAssociateName(businessAssociate3));
    }

    @Test
    public void testParserSummed_typeB() {
        List<CommissionRawData> commissionRawData = new ArrayList<>();
        String businessAssociate1 = "Business Associate 1";
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate1, Million.of(1)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate1, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate1, Million.of(2)));

        String businessAssociate2 = "Business Associate 2";
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate2, Million.of(8)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate2, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate2, Million.of(2)));

        String businessAssociate3 = "Business Associate 3";
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate3, Million.of(20)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate3, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.B, businessAssociate3, Million.of(2)));

        BusinessAssociatesSummed result = (BusinessAssociatesSummed) commissionParserSummed.parse(commissionRawData);
        assertEquals(50000D, result.findCommissionByAssociateName(businessAssociate1));
        assertEquals(Million.of(12) * 0.01 + 30000D, result.findCommissionByAssociateName(businessAssociate2));
        assertEquals(Million.of(24) * 0.01 + 50000D, result.findCommissionByAssociateName(businessAssociate3));
    }

    @Test
    public void testParserSummed_typeC() {
        List<CommissionRawData> commissionRawData = new ArrayList<>();
        String businessAssociate1 = "Business Associate 1";
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate1, Million.of(1)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate1, Million.of(1)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate1, Million.of(2)));

        String businessAssociate2 = "Business Associate 2";
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate2, Million.of(7)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate2, Million.of(0)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate2, Million.of(2)));

        String businessAssociate3 = "Business Associate 3";
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate3, Million.of(20)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate3, Million.of(2)));
        commissionRawData.add(new CommissionRawData(CommissionType.C, businessAssociate3, Million.of(2)));

        BusinessAssociatesSummed result = (BusinessAssociatesSummed) commissionParserSummed.parse(commissionRawData);
        assertEquals(40000D, result.findCommissionByAssociateName(businessAssociate1));
        assertEquals(Million.of(9) * 0.01 + 20000D, result.findCommissionByAssociateName(businessAssociate2));
        assertEquals(Million.of(24) * 0.01 + 40000D, result.findCommissionByAssociateName(businessAssociate3));
    }


}
