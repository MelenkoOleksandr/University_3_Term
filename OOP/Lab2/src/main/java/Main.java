import devices.Device;
import devices.DeviceManager;
import devices.DeviceTypes;
import devices.PortTypes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void showMenu() {
        System.out.println("0. Print devices");
        System.out.println("1. Parse Devices with DOM");
        System.out.println("2. Parse Devices with SAX");
        System.out.println("3. Parse Devices with StAX");
        System.out.println("4. Check XML with XSD");
        System.out.println("5. Sort Devices");
        System.out.println("6. Add new Device");
        System.out.println("7. Delete Device");
        System.out.println("8. Save Devices to XML");
        System.out.println("9. Exit");
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException, TransformerException {
        DeviceManager deviceManager = new DeviceManager("src\\main\\resources\\deviceTest1.xml");

        String choice;
        while (true) {
            showMenu();
            choice = scanner.nextLine();
            switch (choice) {
                case "0" -> deviceManager.printDevices();
                case "1" -> deviceManager.parseDevicesWithDOM();
                case "2" -> deviceManager.parseDevicesWithSAX();
                case "3" -> deviceManager.parseDevicesWithStAX();
                case "4" -> deviceManager.XSDValidation();
                case "5" -> deviceManager.sortDevices();
                case "6" -> {
                    System.out.println("Enter id");
                    String id = scanner.nextLine();
                    System.out.println("Enter device name");
                    String name = scanner.nextLine();
                    System.out.println("Enter device origin");
                    String origin = scanner.nextLine();
                    System.out.println("Enter device price");
                    String price = scanner.nextLine();
                    System.out.println("Enter device critical");
                    String critical = scanner.nextLine();
                    System.out.println("Enter device peripheral");
                    String peripheral = scanner.nextLine();
                    System.out.println("Enter device energy consumption");
                    String energyConsumption = scanner.nextLine();
                    System.out.println("Enter device cooler");
                    String cooler = scanner.nextLine();
                    System.out.println("Enter device componentGroup");
                    String componentGroup = scanner.nextLine();
                    System.out.println("Enter device port");
                    String port = scanner.nextLine();
                    deviceManager.addDevice(new Device(id, name, origin, Integer.parseInt(price), Boolean.parseBoolean(critical), new DeviceTypes(Boolean.parseBoolean(peripheral), Integer.parseInt(energyConsumption), Boolean.parseBoolean(cooler), componentGroup, PortTypes.valueOf(port)) ));
                }
                case "7" -> {
                    System.out.println("Enter id");
                    String id = scanner.nextLine();
                    deviceManager.deleteDevice(id);
                }
                case "8" -> deviceManager.saveDevices();
                case "9" -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
