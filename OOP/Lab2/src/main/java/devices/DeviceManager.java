package devices;

import org.xml.sax.SAXException;
import parsers.DOMParser;
import parsers.SAXParcer;
import parsers.StaXParser;
import utils.DeviceSorter;
import utils.XMLCreator;
import utils.XMLValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DeviceManager {
    String XML_FILE = "src\\main\\resources\\devices.xml";
    ArrayList<Device> devices = new ArrayList<Device>();

    public DeviceManager() {
    }

    public DeviceManager(String XML_FILE) {
        this.XML_FILE = XML_FILE;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void printDevices() {
        for (Device device : devices) {
            System.out.println(device);
        }
    }

    public void addDevice(Device device) {
        for (Device device1 : devices) {
            if ( device1.getId() == device.getId() ) {
                System.out.println("Device already exists");
                return;
            }
        }
        devices.add(device);
    }

    public void deleteDevice(String id) {
        for (Device device : devices) {
            if (Objects.equals(device.getId(), id)) {
                devices.remove(device);
                break;
            }
        }
    }

    public boolean XSDValidation() {
        if (XMLValidator.validateXML("src\\main\\resources\\devices.xsd", XML_FILE)) {
            System.out.println("XML is valid");
            return true;
        } else {
            System.out.println("XML is not valid");
            return false;
        }
    }

    public void parseDevicesWithDOM() throws ParserConfigurationException, IOException, SAXException {
        devices = DOMParser.parse(XML_FILE);
    }

    public  void parseDevicesWithSAX() throws ParserConfigurationException, IOException, SAXException {
        devices = SAXParcer.parse(XML_FILE);
    }

    public void parseDevicesWithStAX() throws XMLStreamException, FileNotFoundException {
        devices = StaXParser.parse(XML_FILE);
    }

    public void sortDevices() {
        DeviceSorter.sortDevices(devices);
    }

    public void saveDevices() throws ParserConfigurationException, TransformerException {
        XMLCreator.createXML(XML_FILE, devices );
    }
}
