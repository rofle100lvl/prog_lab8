package presention.add;

import model.Coordinates;
import model.Flat;
import model.Furnish;
import model.House;
import presention.Model;
import presention.login.LoginMode;
import presention.remove.RemoveView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddController {
    private AddView view;
    private Model model;

    public AddController(AddView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        add();
    }

    private void add() {
        Flat flat = new Flat();
        flat.setName(view.getNameTextField().getText());
        flat.setCoordinates(new Coordinates(Float.parseFloat(view.getxCoordTextField().getText()),
                Float.parseFloat(view.getyCoordTextField().getText())));
        flat.setArea(Float.parseFloat(view.getAreaTextField().getText()));
        flat.setNumberOfRooms(Long.parseLong(view.getNumberOfRoomsTextField().getText()));
        flat.setPrice(Integer.parseInt(view.getPriceTextField().getText()));
        flat.setBalcony(view.getBalconyCheckBox().isSelected());
        flat.setFurnish((Furnish) view.getFurnishComboBox().getSelectedItem());
        House house = new House();
        house.setName(view.getHouseNameTextField().getText());
        house.setYear(Long.parseLong(view.getHouseYearTextField().getText()));
        house.setNumberOfFloors(Integer.parseInt(view.getHouseNumberOfFloorsTextField().getText()));
        house.setNumberOfFlatsOnFloor(Integer.parseInt(view.getHouseNumberOfFlatsOnFloorTextField().getText()));
        house.setNumberOfLifts(Long.parseLong(view.getHouseNumberOfLifts().getText()));
        flat.setHouse(house);
        model.add(flat);
        view.dispose();
    }
}
