package base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Settings {

    private static String path = "src/test/resources/settings/Settings.xml";
    private static File file = new File(path);
    private static String absolutePath = file.getAbsolutePath();
    private static String BASE_URL;
    private static String LOGIN;
    private static String PASSWORD;
    private static String TOKEN;
    private static Document document;

    static {
        File f = new File(absolutePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(f);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        initData();
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getLogin() {
        return LOGIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static String getToken() {
        return TOKEN;
    }

    private static void initData() {
        BASE_URL = initElement("BaseUrl");
        LOGIN = initElement("Login");
        PASSWORD = initElement("Password");
        TOKEN = initElement("Token");
    }

    private static String initElement(String element) {
        Element message = (Element) document.getDocumentElement().getElementsByTagName(element).item(0);
        return message.getTextContent();
    }
}
