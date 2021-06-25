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
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Service {
    private Connector connector;
    private final ExecutorService executor;
    private final Receiver receiver;

    public Service(Consumer<Response> answerWithCollection, Consumer<Response> answerWithoutCollection) {
        connector = ConnectorFabric.getConnector();
        executor = Executors.newSingleThreadExecutor();
        receiver = new Receiver(connector, answerWithCollection, answerWithoutCollection);
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

    public void clear() throws LimitOfReconnectionsException {
        connector.send(new ClearDescription());
    }


    public void update(int id, Flat flat) throws LimitOfReconnectionsException {
        connector.send(new UpdateIdDescription(flat,id));
    }

    public void info() throws LimitOfReconnectionsException {
        connector.send(new InfoCommandDescription());
    }
    public void help() throws LimitOfReconnectionsException {
        connector.send(new HelpCommandDescription());
    }

}
