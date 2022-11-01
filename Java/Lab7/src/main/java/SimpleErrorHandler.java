package groceryXML;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException exception) {
        System.out.println("Warning: ");
        printInfo(exception);
    }

    public void error(SAXParseException exception) {
        System.out.println("Error: ");
        printInfo(exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("Fatal error: ");
        printInfo(exception);
    }

    private void printInfo(SAXParseException exception) {
        System.out.println("   Public ID: " + exception.getPublicId());
        System.out.println("   System ID: " + exception.getSystemId());
        System.out.println("   Line number: " + exception.getLineNumber());
        System.out.println("   Column number: " + exception.getColumnNumber());
        System.out.println("   Message: " + exception.getMessage());
    }
}
