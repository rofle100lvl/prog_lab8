package presention.update;

import model.Coordinates;
import model.Flat;
import model.Furnish;
import model.House;
import presention.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateController {
    private UpdateView view;
    private Model model;

    public UpdateController(UpdateView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        update();
    }

    public void update() {
        try {
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
            model.update(Integer.parseInt(view.getIdTextField().getText()), flat);
            view.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Ошибка, вы ввели строку в поле, где должно быть число");
        }
    }
}
