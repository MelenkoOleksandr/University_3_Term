package utils;

import devices.Device;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XMLCreator {
    public static void createXML(String xmlPath, ArrayList<Device> devices) throws ParserConfigurationException, TransformerException {
        Document doc = null;
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();

        doc = db.newDocument();
        Element root = doc.createElement("Devices");

        for (Device device : devices) {
            Element deviceElement = doc.createElement("Device");
            deviceElement.setAttribute("id", String.valueOf(device.getId()));
            Element name = doc.createElement("Name");
            name.setTextContent(device.getName());
            deviceElement.appendChild(name);
            Element origin = doc.createElement("Origin");
            origin.setTextContent(device.getOrigin());
            deviceElement.appendChild(origin);
            Element price = doc.createElement("Price");
            price.setTextContent(String.valueOf(device.getPrice()));
            deviceElement.appendChild(price);
            root.appendChild(deviceElement);
            Element critical = doc.createElement("Critical");
            critical.setTextContent(String.valueOf(device.isCritical()));
            deviceElement.appendChild(critical);

            Element types = doc.createElement("Types");
            Element peripheral = doc.createElement("Peripheral");
            peripheral.setTextContent(String.valueOf(device.getTypes().isPeripheral()));
            types.appendChild(peripheral);
            Element energyConsumption = doc.createElement("EnergyConsumption");
            energyConsumption.setTextContent(String.valueOf(device.getTypes().getEnergyConsumption()));
            types.appendChild(energyConsumption);
            Element cooler = doc.createElement("Cooler");
            cooler.setTextContent(String.valueOf(device.getTypes().isCooler()));
            types.appendChild(cooler);
            Element componentGroup = doc.createElement("ComponentGroup");
            componentGroup.setTextContent(String.valueOf(device.getTypes().getComponentGroup()));
            types.appendChild(componentGroup);
            Element port = doc.createElement("Port");
            port.setTextContent(String.valueOf(device.getTypes().getPort()));
            types.appendChild(port);
            deviceElement.appendChild(types);
            root.appendChild(deviceElement);
        }

        doc.appendChild(root);
        TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(new File(xmlPath)));
    }
}
