package presention;

import Services.Service;
import commandDescriptions.*;
import exceptions.LimitOfReconnectionsException;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Model implements TableModel {
    private ArrayList<Flat> flats = new ArrayList<>();
    private MainView view;
    private Service service;

//    {
//        flats.add(new Flat());
//        flats.add(new Flat());
//        flats.add(new Flat());
//        flats.add(new Flat());
//    }

    public Model(MainView view) {
        this.view = view;
        service = new Service(response -> {
            flats = response.getFlats();
        }, response -> {
            JOptionPane.showMessageDialog(view,response.getRequestText());
            if (response.getCode() == 244) view.setLoginMode(true);
        });
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

    public void login(String login, String password)  {
        try {
            service.login(login, password);
        } catch (LimitOfReconnectionsException exception) {
            exception.printStackTrace();
        }
    }

    public void register(String login, String password) {
        try {
            service.register(login, password);
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }
}

