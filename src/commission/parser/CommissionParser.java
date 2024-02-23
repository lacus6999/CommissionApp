package src.commission.parser;

import src.commission.dto.CommissionRawData;

import java.util.List;

public interface CommissionParser {

    CommissionToXMLParsingObject parse(List<CommissionRawData> data);

}
