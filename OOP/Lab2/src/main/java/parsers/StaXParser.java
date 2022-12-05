package parsers;

import devices.Device;
import devices.PortTypes;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StaXParser {
    public static ArrayList<Device> parse(String fileName) throws FileNotFoundException, XMLStreamException {
        Device device =  new Device();
        ArrayList<Device> devices = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (startElement.getName().getLocalPart().equals("Device")) {
                    device.setId(String.valueOf(Integer.parseInt(startElement.getAttributeByName(QName.valueOf("id")).getValue())));
                } else if (startElement.getName().getLocalPart().equals("Name")) {
                    device.setName(eventReader.nextEvent().asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("Origin")) {
                    device.setOrigin(eventReader.nextEvent().asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("Price")) {
                    device.setPrice((int) Double.parseDouble(eventReader.nextEvent().asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("Critical")) {
                    device.setCritical(Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("Peripheral")) {
                    device.getTypes().setPeripheral(Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("EnergyConsumption")) {
                    device.getTypes().setEnergyConsumption(Integer.parseInt(eventReader.nextEvent().asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("Cooler")) {
                    device.getTypes().setCooler(Boolean.parseBoolean(eventReader.nextEvent().asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equals("ComponentGroup")) {
                    device.getTypes().setComponentGroup(eventReader.nextEvent().asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals("Port")) {
                    device.getTypes().setPort(PortTypes.valueOf(eventReader.nextEvent().asCharacters().getData()));
                }
            }

            if (event.isEndElement()) {
                if (event.asEndElement().getName().getLocalPart().equals("Device")) {
                    devices.add(device);
                    device = new Device();
                }
            }
        }

        return  devices;
    }

}
