package parsers;

import devices.Device;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXParcer {
    public static  ArrayList<Device> parse(String fileName) throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        DeviceHandler deviceHandler = new DeviceHandler();
        saxParser.parse(new File(fileName), deviceHandler);

        return deviceHandler.getDevices() == null ? new ArrayList<>() : deviceHandler.getDevices();
    }
}
