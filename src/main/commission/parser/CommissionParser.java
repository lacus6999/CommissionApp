package main.commission.parser;

import main.commission.dto.CommissionRawData;

import java.util.List;

public interface CommissionParser {

    CommissionToXMLParsingObject parse(List<CommissionRawData> data);

}
