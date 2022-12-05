package parsers;

import devices.Device;
import devices.PortTypes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParser {
    public static ArrayList<Device> parse(String fileName) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Device> devices = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));
        NodeList nodeList = document.getElementsByTagName("Device");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NodeList childNodes = node.getChildNodes();
            Device device = new Device();
            device.setId(node.getAttributes().getNamedItem("id").getNodeValue());
            for (int j = 0; j < childNodes.getLength(); j++) {
                Node cNode = childNodes.item(j);
                if (cNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (cNode.getNodeName().equals("Types")) {
                        NodeList types = cNode.getChildNodes();
                        for (int k = 0; k < types.getLength(); k++) {
                            Node type = types.item(k);
                            if (type.getNodeType() == Node.ELEMENT_NODE) {
                                switch (type.getNodeName()) {
                                    case "Peripheral" ->
                                            device.getTypes().setPeripheral(Boolean.parseBoolean(type.getTextContent()));
                                    case "EnergyConsumption" ->
                                            device.getTypes().setEnergyConsumption(Integer.parseInt(type.getTextContent()));
                                    case "Cooler" ->
                                            device.getTypes().setCooler(Boolean.parseBoolean(type.getTextContent()));
                                    case "ComponentGroup" -> device.getTypes().setComponentGroup(type.getTextContent());
                                    case "Port" -> device.getTypes().setPort(PortTypes.valueOf(type.getTextContent()));
                                }
                            }
                        }
                    } else {
                        switch (cNode.getNodeName()) {
                            case "Name" -> device.setName(cNode.getTextContent());
                            case "Origin" -> device.setOrigin(cNode.getTextContent());
                            case "Price" -> device.setPrice(Integer.parseInt(cNode.getTextContent()));
                            case "Critical" -> device.setCritical(Boolean.parseBoolean(cNode.getTextContent()));
                        }
                    }
                }
            }
            devices.add(device);
        }
        return devices;
    }
}
