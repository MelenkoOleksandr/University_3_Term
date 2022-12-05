import devices.Device;
import devices.DeviceManager;
import devices.DeviceTypes;
import devices.PortTypes;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class DeviceManagerTest {

    @Test
    @Description("Test if the device manager can load devices from XML using SAX, DOM and StAX parsers")
    public void testParseXML() throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        ArrayList<Device> result1 = new ArrayList<Device>();
        ArrayList<Device> result2 = new ArrayList<Device>();
        result1.add(new Device("1", "Device1","USA", 250,  false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        result1.add(new Device("2", "Device2","USA", 450,  false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        result1.add(new Device("3", "Device3", "USA",300,  false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        result1.add(new Device("4", "Device4", "USA",50,  false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        result1.add(new Device("5", "Device5", "USA",1000,  false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));

        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTest1.xml");

        deviceManager.parseDevicesWithDOM();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result1.toArray());
        deviceManager.parseDevicesWithSAX();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result1.toArray());
        deviceManager.parseDevicesWithStAX();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result1.toArray());

        deviceManager = new DeviceManager("src\\main\\resources\\deviceTest2.xml");
        deviceManager.parseDevicesWithDOM();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result2.toArray());
        deviceManager.parseDevicesWithSAX();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result2.toArray());
        deviceManager.parseDevicesWithStAX();
        Assertions.assertArrayEquals(deviceManager.getDevices().toArray(), result2.toArray());
    }

    @Test
    @Description("Test if the device manager can add and delete devices")
    public void testAddAndDeleteDevices() throws ParserConfigurationException, IOException, SAXException {
        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTest1.xml");
        deviceManager.parseDevicesWithDOM();
        deviceManager.addDevice(new Device("6", "Device6", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("7", "Device7", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("8", "Device8", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("9", "Device9", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("10", "Device10", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));

        Assertions.assertEquals(deviceManager.getDevices().size(), 10);
        deviceManager.addDevice(new Device("11", "Device11", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        Assertions.assertEquals(deviceManager.getDevices().size(), 11);
        deviceManager.addDevice(new Device("11", "Device11", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        Assertions.assertEquals(deviceManager.getDevices().size(), 11);

        deviceManager.deleteDevice("6");
        deviceManager.deleteDevice("7");
        Assertions.assertEquals(deviceManager.getDevices().size(), 9);
    }

    @Test
    @Description("Test if the device manager can sort devices by price")
    public void testSortDevicesByPrice() throws ParserConfigurationException, IOException, SAXException {
        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTest1.xml");
        deviceManager.parseDevicesWithDOM();
        deviceManager.sortDevices();
        Assertions.assertEquals(deviceManager.getDevices().get(0).getPrice(), 50);
        Assertions.assertEquals(deviceManager.getDevices().get(1).getPrice(), 250);
        Assertions.assertEquals(deviceManager.getDevices().get(2).getPrice(), 300);
        Assertions.assertEquals(deviceManager.getDevices().get(3).getPrice(), 450);
        Assertions.assertEquals(deviceManager.getDevices().get(4).getPrice(), 1000);
    }

    @Test
    @Description("Test if the device manager can validate XML with XSD")
    public void testValidateXML() {
        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTest1.xml");
        Assertions.assertTrue(deviceManager.XSDValidation());
        deviceManager = new DeviceManager("src\\main\\resources\\deviceTest2.xml");
        Assertions.assertTrue(deviceManager.XSDValidation());
        deviceManager = new DeviceManager("src\\main\\resources\\invalidDevices.xml");
        Assertions.assertFalse(deviceManager.XSDValidation());
    }

    @Test
    @Description("Test if the device manager can create XML from devices")
    public void testCreateXML() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTestCreated.xml");
        deviceManager.addDevice(new Device("1", "Device1", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("2", "Device2", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.addDevice(new Device("3", "Device3", "USA", 1000, false, new DeviceTypes(true, 20, true, "Multimedia", PortTypes.valueOf("USB"))));
        deviceManager.saveDevices();
        deviceManager.parseDevicesWithDOM();
        Assertions.assertEquals(deviceManager.getDevices().size(), 3);
    }
}
