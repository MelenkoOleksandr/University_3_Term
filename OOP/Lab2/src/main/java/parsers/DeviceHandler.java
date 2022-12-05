package parsers;

import devices.Device;
import devices.PortTypes;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

class DeviceHandler extends DefaultHandler {
    private ArrayList<Device> devices;
    private Device device = null;
    private StringBuilder data = null;

    public ArrayList<Device> getDevices() {
        return devices;
    }

    boolean bName = false;
    boolean bOrigin = false;
    boolean bPrice = false;
    boolean bCritical = false;
    boolean bPeripheral = false;
    boolean bEnergyConsumption = false;
    boolean bCooler = false;
    boolean bComponentGroup = false;
    boolean bPort = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Device")) {
            String id = attributes.getValue("id");
            device = new Device();
            device.setId(id);
            if (devices == null)
                devices = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("Name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("Origin")) {
            bOrigin = true;
        } else if (qName.equalsIgnoreCase("Price")) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase("Critical")) {
            bCritical = true;
        } else if (qName.equalsIgnoreCase("Peripheral")) {
            bPeripheral = true;
        } else if (qName.equalsIgnoreCase("EnergyConsumption")) {
            bEnergyConsumption = true;
        } else if (qName.equalsIgnoreCase("Cooler")) {
            bCooler = true;
        } else if (qName.equalsIgnoreCase("ComponentGroup")) {
            bComponentGroup = true;
        } else if (qName.equalsIgnoreCase("Port")) {
            bPort = true;
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bName) {
            device.setName(data.toString());
            bName = false;
        } else if (bOrigin) {
            device.setOrigin(data.toString());
            bOrigin = false;
        } else if (bPrice) {
            device.setPrice(Integer.parseInt(data.toString()));
            bPrice = false;
        } else if (bCritical) {
            device.setCritical(Boolean.parseBoolean(data.toString()));
            bCritical = false;
        } else if (bPeripheral) {
            device.getTypes().setPeripheral(Boolean.parseBoolean(data.toString()));
            bPeripheral = false;
        } else if (bEnergyConsumption) {
            device.getTypes().setEnergyConsumption(Integer.parseInt(data.toString()));
            bEnergyConsumption = false;
        } else if (bCooler) {
            device.getTypes().setCooler(Boolean.parseBoolean(data.toString()));
            bCooler = false;
        } else if (bComponentGroup) {
            device.getTypes().setComponentGroup(data.toString());
            bComponentGroup = false;
        } else if (bPort) {
            device.getTypes().setPort(PortTypes.valueOf(data.toString()));
            bPort = false;
        } else if (qName.equalsIgnoreCase("Device")) {
            devices.add(device);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
