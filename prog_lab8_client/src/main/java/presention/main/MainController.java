package presention.main;

import exceptions.LimitOfReconnectionsException;
import model.Coordinates;
import model.Flat;
import model.Furnish;
import model.House;
import presention.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainController {
    private MainView view;
    private Model model;

    public MainController(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::menuItemDidSelect, model));
    }

    public void menuItemDidSelect(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add":
                House house = new House("r", 123L,123,123,12l);
                Flat flat = new Flat();
                flat.setHouse(house);
                flat.setCoordinates(12, 23F);
                flat.setName("name");
                flat.setBalcony(true);
                flat.setPrice(12);
                flat.setNumberOfRooms(12L);
                flat.setFurnish(Furnish.FINE);
                try {
                    model.add(new Flat());
                } catch (LimitOfReconnectionsException limitOfReconnectionsException) {
                    limitOfReconnectionsException.printStackTrace();
                }
        }
        view.repaint();
    }


}
