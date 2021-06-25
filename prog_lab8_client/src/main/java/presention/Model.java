package presention;

import Services.Service;
import com.sun.org.apache.xpath.internal.operations.Number;
import exceptions.LimitOfReconnectionsException;
import model.Flat;
import model.Furnish;
import presention.main.MainView;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model implements TableModel {
    private ArrayList<Flat> flats = new ArrayList<>();
    private ArrayList<Flat> flatsBuffer = new ArrayList<>();
    private MainView view;
    private Service service;
    boolean filterMode;

    private ArrayList<Predicate<Flat>> filters = new ArrayList<>();
    private Comparator<Flat> comparator;
    private Comparator<Flat> prevComparator;

    private static ArrayList<Comparator<Flat>> comparators;

    static {
        comparators = new ArrayList<>();
        comparators.add(Comparator.comparingInt(Flat::getId));
        comparators.add(Comparator.comparing(Flat::getName));
        comparators.add(Comparator.comparingDouble((flat) -> flat.getCoordinates().getX()));
        comparators.add(Comparator.comparingDouble((flat) -> flat.getCoordinates().getY()));
        comparators.add(Comparator.comparingDouble(Flat::getArea));
        comparators.add(Comparator.comparingLong(Flat::getNumberOfRooms));
        comparators.add(Comparator.comparingInt(Flat::getPrice));
        comparators.add(Comparator.comparing(flat -> flat.getBalcony().toString()));
        comparators.add(Comparator.comparing(flat -> flat.getFurnish().toString()));
        comparators.add(Comparator.comparing(flat -> flat.getHouse().getName()));
        comparators.add(Comparator.comparingLong(flat -> flat.getHouse().getYear()));
        comparators.add(Comparator.comparingInt(flat -> flat.getHouse().getNumberOfFloors()));
        comparators.add(Comparator.comparingInt(flat -> flat.getHouse().getNumberOfFlatsOnFloor()));
        comparators.add(Comparator.comparingLong(flat -> flat.getHouse().getNumberOfLifts()));

    }

    public Model(MainView view) {
        this.view = view;
        service = new Service(response -> {
            flats = response.getFlats();
            view.repaint();
            sendToBuffer();
            System.out.println(flatsBuffer);
        }, response -> {
            JOptionPane.showMessageDialog(view,response.getRequestText());
            if (response.getCode() == 244) view.setLoginMode(true);
        });
    }

    @Override
    public int getRowCount() {
        return flatsBuffer.size();
    }

    @Override
    public int getColumnCount() {
        return 14;
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
        Flat flat = flatsBuffer.get(rowIndex);
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

    public void addComparatorByColumn(int columnIndex) {
        addComparator(comparators.get(columnIndex));
    }

    public void addComparator(Comparator<Flat> comparator) {
        if (Objects.equals(prevComparator, comparator)) {
            this.comparator = this.comparator.reversed();
        } else {
            this.comparator = Objects.nonNull(this.comparator) ? comparator.thenComparing(this.comparator) : comparator;
        }
        prevComparator = comparator;
    }

    public void addFilter(Predicate<Flat> filter) {
        filters.add(filter);
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

    public void add(Flat flat)  {
        try {
            service.add(flat);
            view.repaint();
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }

    public void addIfMax(Flat flat) throws LimitOfReconnectionsException {
        service.addIfMax(flat);
    }

    public void remove_by_id(int id) {
        try {
            service.remove_by_id(id);
        } catch (LimitOfReconnectionsException e) {
            e.printStackTrace();
        }
    }

    public void remove_head() {
        try {
            service.remove_head();
        } catch (LimitOfReconnectionsException e) {
            e.printStackTrace();
        }
    }

    public void info() {
        try {
            service.info();
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }

    public void clear() {
        try {
            service.clear();
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }

    public void update(int id, Flat flat)  {
        try {
            service.update(id, flat);
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }

    public void help()  {
        try {
            service.help();
        } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
            limitOfReconnectionsException.printStackTrace();
        }
    }

    public void sendToBuffer() {
        flatsBuffer = flats.stream().filter(filters.stream().reduce(x -> true, Predicate::and)).collect(Collectors.toCollection(ArrayList::new));
        flatsBuffer.sort(comparator);
    }

    public void setComparator(Comparator<Flat> comparator) {
        System.out.println(comparator);
        this.comparator = comparator;
    }
}

