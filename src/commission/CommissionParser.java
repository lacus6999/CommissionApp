package src.commission;

import java.util.List;

public interface CommissionParser {

    CommissionToXMLParsingObject parse(List<CommissionRawData> data);

}
