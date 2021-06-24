package utils;

import exceptions.LimitOfReconnectionsException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectorFabric {

    private static Connector getPropertiesConnector(Properties properties) throws NumberFormatException,
            LimitOfReconnectionsException, NullPointerException {
        int reconnectionTimeout = Integer.parseInt(properties.getProperty("reconnectionTimeout"));
        int maxReconnectionAttempts = Integer.parseInt(properties.getProperty("maxReconnectionAttempts"));
        int PORT = Integer.parseInt(properties.getProperty("PORT"));
        return new Connector(PORT, reconnectionTimeout, maxReconnectionAttempts);
    }


    private static Properties loadProperties() {
        try {
            Properties clientProperties = new Properties();
            clientProperties.load(new FileInputStream("client.properties"));
            return clientProperties;
        } catch (IOException e) {
            return null;
        }
    }

    public static Connector getConnector() {
        try {
            return getPropertiesConnector(loadProperties());
        } catch (LimitOfReconnectionsException | NumberFormatException |
                NullPointerException e) {
            System.out.println("Файл повреждён или недоступен, проверьте правильность настроек");
            return null;
        }
    }
}
