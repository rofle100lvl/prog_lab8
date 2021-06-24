package presention;

import commandDescriptions.*;
import exceptions.LimitOfReconnectionsException;
import model.Coordinates;
import model.Flat;
import model.Furnish;
import model.House;
import presention.main.MainView;
import utils.Connector;
import utils.ConnectorFabric;
import utils.Consts;
import utils.Response;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Model implements TableModel {
    private ArrayList<Flat> flats = new ArrayList<>();
    private Connector connector = ConnectorFabric.getConnector();
    private MainView view;
    private Response serverResponse;

    {
        connector.setUserLogin("login");
        connector.setUserPassword("pass");
        SwingUtilities.invokeLater(() -> {
            try {
                connector.send(new LoginCommandDescription());
                while (flats == null || flats.size() == 0) {
                    flats = receive();
                }
                view.repaint();

            } catch (LimitOfReconnectionsException | IOException limitOfReconnectionsException) {
                limitOfReconnectionsException.printStackTrace();
            }
        });
    }

    public Model(MainView view) {
        this.view = view;
    }

    public ArrayList<Flat> receive() throws LimitOfReconnectionsException, IOException {
        serverResponse = connector.receive();
        if (serverResponse != null){
            serverResponse.printResponse();
            if (serverResponse.getCode() == 200){
                System.out.println(serverResponse.getFlats());
                return serverResponse.getFlats();
            }
        }
        return null;
    }


    @Override
    public int getRowCount() {
        return flats.size();
    }

    @Override
    public int getColumnCount() {
        return 13;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "name";
            case 2:
                return "x_coord";
            case 3:
                return "y_coord";
            case 4:
                return "area";
            case 5:
                return "number_of_rooms";
            case 6:
                return "price";
            case 7:
                return "balcony";
            case 8:
                return "furnish";
            case 9:
                return "house_name";
            case 10:
                return "house_year";
            case 11:
                return "house_number_of_floors";
            case 12:
                return "house_number_of_flats_on_floor";
            case 13:
                return "house_number_of_lifts";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 12:
                return int.class;
            case 1:
            case 9:
                return String.class;
            case 2:
            case 3:
                return Double.class;
            case 4:
                return float.class;
            case 5:
            case 10:
            case 13:
                return Long.class;
            case 6:
            case 11:
                return Integer.class;
            case 7:
                return boolean.class;
            case 8:
                return Furnish.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Flat flat = flats.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return flat.getId();
            case 1:
                return flat.getName();
            case 2:
                return flat.getCoordinates().getX();
            case 3:
                return flat.getCoordinates().getY();
            case 4:
                return flat.getArea();
            case 5:
                return flat.getNumberOfRooms();
            case 6:
                return flat.getPrice();
            case 7:
                return flat.getBalcony();
            case 8:
                return flat.getFurnish();
            case 9:
                return flat.getHouse().getName();
            case 10:
                return flat.getHouse().getYear();
            case 11:
                return flat.getHouse().getNumberOfFloors();
            case 12:
                return flat.getHouse().getNumberOfFlatsOnFloor();
            case 13:
                return flat.getHouse().getNumberOfLifts();
            default:
                return Object.class;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

    public void add(Flat flat) throws LimitOfReconnectionsException {
        connector.send(new AddDescription(flat));
    }

    public void clear() throws LimitOfReconnectionsException {
        connector.send(new ClearDescription());
    }

    public void update(Flat flat, int id) throws LimitOfReconnectionsException {
        connector.send(new UpdateIdDescription(flat, id));
    }

    public void remove_id(int id) throws LimitOfReconnectionsException {
        connector.send(new RemoveByIdDescription(id));
    }

    public void addIfMax(Flat flat) throws LimitOfReconnectionsException {
        connector.send(new AddIfMaxDescription(flat));
    }

    public void remove_head() throws LimitOfReconnectionsException {
        connector.send(new RemoveHeadDescription());
    }

    public void setFlats(ArrayList<Flat> flats) {
        this.flats = flats;
    }

    public Response filterLessThanNumberOfRooms(int numberOfRooms) {
        if (flats.isEmpty()) return new Response(204, Consts.emptyCollection);
        return new Response(200,
                flats.stream()
                        .filter(x -> x.getNumberOfRooms() < numberOfRooms)
                        .map(Flat::niceToString)
                        .collect(Collectors.joining("\n")));
    }

    public Response head() {

        if (flats.isEmpty()) {
            return new Response(204, Consts.emptyCollection);
        }
        return new Response(200, flats.get(0).niceToString());
    }

    public Response getFieldDescendingHouse() {
        return new Response(201, flats.stream()
                .map(Flat::getHouse)
                .map(House::toString)
                .collect(Collectors.joining("\n")));
    }

    public Response getUniquePrice() {
        HashSet<Integer> uniquePrice = new HashSet<>();
        flats.stream()
                .forEach(flat -> uniquePrice.add(flat.getPrice()));
        return new Response(200, uniquePrice.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    public Response show() {
            if (flats.isEmpty()) return new Response(204, Consts.emptyCollection);
            return new Response(200, flats.stream()
                    .map(Flat::niceToString)
                    .collect(Collectors.joining("\n")));
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }
}

