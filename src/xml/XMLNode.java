package src.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class XMLNode {

    public XMLNode(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Node key must be a valid string");
        }
        this.key = key;
        children = new ArrayList<>();
    }

    private final List<XMLNode> children;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void addChild(XMLNode child) {
        children.add(child);
    }

    public XMLNode findChildNode(String key) {
        return children.stream().filter(xmlNode -> xmlNode.getKey().equals(key)).findFirst().orElse(null);
    }

    public String getOpeningTag() {
        return "<" + key + ">";
    }

    public String getClosingTag() {
        return "</" + key + ">";
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String beautifyNode(int indentAmount) {
        if (children.isEmpty()) {
            return value;
        } else {
            StringJoiner sj = new StringJoiner("\n");
            sj.add(getOpeningTag());
            for (XMLNode child : children) {
                sj.add(indentNode(child, indentAmount));
            }
            sj.add(getClosingTag());
            return sj.toString();
        }
    }

    private String indentNode(XMLNode node, int indentAmount) {
        String nodeString = node.beautifyNode(indentAmount);
        return nodeString.lines().map(s -> "\t" + s).collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return beautifyNode(0);
    }
}
