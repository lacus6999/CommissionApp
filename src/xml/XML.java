package src.xml;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Map;

public class XML {

    private final StringBuilder xmlContent = new StringBuilder();

    private static final String OBJECT_PARSING_ERROR = "<Error>Unable to parse object</Error>";

    public XML(Charset charset) {
        xmlContent.append("<").append("?xml version=\"1.0\" encoding=\"").append(charset).append("\"?").append(">\n");
    }

    public <T> String parseObjectToXML(T object, Class<?> stoppingType) {
        try {
            Class<?> oClass = object.getClass();
            //First node is the name of the class
            XMLNode baseNode = new XMLNode(oClass.getName().replace(oClass.getPackageName() + ".", ""));
            XMLNode node = extractNodes(baseNode, object, stoppingType);
            return xmlContent.append(node).toString();
        } catch (ReflectiveOperationException e) {
            return OBJECT_PARSING_ERROR;
        }
    }

    private <T> XMLNode extractNodes(XMLNode node, T object, Class<?> stoppingType) throws ReflectiveOperationException {
        if(object.getClass().equals(stoppingType)) {
            XMLNode childNode = new XMLNode(object.getClass().getName());
            node.addChild(childNode);
            childNode.setValue(object.toString());
            return childNode;
        }
        Class<?> oClass = object.getClass();
        for (Field field : oClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null) {
                    if (value instanceof Map) {
                        Map<?, ?> map = (Map<?, ?>) value;
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            XMLNode fieldNode = new XMLNode(entry.getKey().toString());
                            node.addChild(fieldNode);
                            extractNodes(fieldNode, entry.getValue(), stoppingType);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new ReflectiveOperationException("Unable to access object fields");
            }
        }
        return node;
    }

}
