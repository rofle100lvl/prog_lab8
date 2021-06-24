package Services;

import commandDescriptions.*;
import exceptions.LimitOfReconnectionsException;
import model.Flat;
import utils.Connector;
import utils.ConnectorFabric;
import utils.Response;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service {
    Connector connector;
    private final ExecutorService executor;
    private final Receiver receiver;

    public Service() throws Exception {
        connector = ConnectorFabric.getConnector();
        executor = Executors.newSingleThreadExecutor();
        receiver = new Receiver(connector, () -> System.out.println(1), () -> System.out.println(2));
        executor.submit(receiver);

    }

    private void setUserData(String login, String password) {
        connector.setUserLogin(login);
        connector.setUserPassword(password);

    }

    public void login(String login, String password) throws LimitOfReconnectionsException {
        setUserData(login, password);
        connector.send(new LoginCommandDescription());
    }

    public void register(String login, String password) throws LimitOfReconnectionsException {
        setUserData(login, password);
        connector.send(new RegistrationCommandDescription());
    }

    public void add(Flat flat) throws LimitOfReconnectionsException {
        connector.send(new AddDescription(flat));
    }

    public void addIfMax(Flat flat) throws LimitOfReconnectionsException {
        connector.send(new AddIfMaxDescription(flat));
    }

    public void remove_by_id(int id) throws LimitOfReconnectionsException {
        connector.send(new RemoveByIdDescription(id));
    }

    public void remove_head() throws LimitOfReconnectionsException {
        connector.send(new RemoveHeadDescription());
    }

    public void update(int id, Flat flat) throws LimitOfReconnectionsException {
        connector.send(new UpdateIdDescription(flat,id));
    }



}
