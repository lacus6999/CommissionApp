package main.xml;

import main.logger.Logger;

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
            // First node is the name of the class
            XMLNode baseNode = new XMLNode(oClass.getName().replace(oClass.getPackageName() + ".", ""));

            XMLNode node = extractNodes(baseNode, object, stoppingType);
            return xmlContent.append(node).toString();
        } catch (ReflectiveOperationException e) {
            Logger.getLogger().ERROR("Unable to create a valid XML representation");
            return OBJECT_PARSING_ERROR;
        }
    }

    private <T> XMLNode extractNodes(XMLNode node, T object, Class<?> stoppingType) throws ReflectiveOperationException {
        // Stop whenever we reach a type of {stoppingType}
        if (object.getClass().equals(stoppingType)) {
            XMLNode childNode = new XMLNode(object.getClass().getName());
            node.addChild(childNode);
            childNode.setValue(object.toString());
            return childNode;
        }
        // Iterating through fields
        Class<?> oClass = object.getClass();
        for (Field field : oClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value != null) {
                    if (value instanceof Map) {
                        // In case of map create a parentNode and append the map's value to children
                        Map<?, ?> map = (Map<?, ?>) value;
                        for (Map.Entry<?, ?> entry : map.entrySet()) {
                            XMLNode parentNode = new XMLNode(entry.getKey().toString());
                            node.addChild(parentNode);
                            extractNodes(parentNode, entry.getValue(), stoppingType);
                        }
                    } else {
                        // Otherwise create a node
                        XMLNode childNode = new XMLNode(field + " child");
                        childNode.setValue(value.toString());
                        XMLNode parentNode = new XMLNode(field.getName());
                        parentNode.addChild(childNode);
                        node.addChild(parentNode);
                    }
                }
            } catch (IllegalAccessException e) {
                Logger.getLogger().ERROR("Unable to generate XML structure from given object of type: " + object.getClass());
                throw new ReflectiveOperationException("Unable to access object fields");
            }
        }
        return node;
    }

}
