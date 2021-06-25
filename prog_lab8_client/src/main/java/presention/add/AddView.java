package presention.add;

import model.Furnish;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AddView extends JFrame {
    private AddMode mode;
    private JTextField nameTextField;
    private JTextField xCoordTextField;
    private JTextField yCoordTextField;
    private JTextField areaTextField;
    private JTextField numberOfRoomsTextField;
    private JTextField priceTextField;
    private JCheckBox balconyCheckBox;
    private JComboBox<Furnish> furnishComboBox;
    private JTextField houseNameTextField;
    private JTextField houseYearTextField;
    private JTextField houseNumberOfFloorsTextField;
    private JTextField houseNumberOfFlatsOnFloorTextField;
    private JTextField houseNumberOfLifts;
    private JButton button;


    public void init(ActionListener listener) {
        setTitle("Add");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        nameTextField = new JTextField(10);
        xCoordTextField = new JTextField(10);
        yCoordTextField = new JTextField(10);
        areaTextField = new JTextField(10);
        numberOfRoomsTextField = new JTextField(10);
        priceTextField = new JTextField(10);
        balconyCheckBox = new JCheckBox("Has balcony");
        furnishComboBox = new JComboBox<>(Furnish.values());
        houseNameTextField = new JTextField(10);
        houseYearTextField = new JTextField(10);
        houseNumberOfFloorsTextField = new JTextField(10);
        houseNumberOfFlatsOnFloorTextField = new JTextField(10);
        houseNumberOfLifts = new JTextField(10);

        JComboBox<AddMode> addModeComboBox = new JComboBox<>(AddMode.values());
        addModeComboBox.addItemListener(e -> setMode((AddMode) e.getItem()));

        Map<String, JComponent> stringJComponentMap = new LinkedHashMap<>();
        stringJComponentMap.put("", nameTextField);
        stringJComponentMap.put("X Coordinate:", xCoordTextField);
        stringJComponentMap.put("Y Coordinate:", yCoordTextField);
        stringJComponentMap.put("Area:", areaTextField);
        stringJComponentMap.put("Number of rooms:", numberOfRoomsTextField);
        stringJComponentMap.put("Price:", priceTextField);
        stringJComponentMap.put("Balcony:", balconyCheckBox);
        stringJComponentMap.put("Furnish:", furnishComboBox);
        stringJComponentMap.put("Name of House:", houseNameTextField);
        stringJComponentMap.put("Year of House:", houseYearTextField);
        stringJComponentMap.put("Number of floors:", houseNumberOfFloorsTextField);
        stringJComponentMap.put("Numbers of flats:", houseNumberOfFlatsOnFloorTextField);
        stringJComponentMap.put("Number of Lifts:", houseNumberOfLifts);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 5, 10);
        add(new JLabel("Mode:"), constraints);
        constraints.gridx = 1;
        add(addModeComboBox, constraints);
        constraints.insets = new Insets(5, 10, 5, 10);
        AtomicInteger i = new AtomicInteger(0);
        stringJComponentMap.forEach((string, comp) -> {
            constraints.gridx = 0;
            constraints.gridy = i.incrementAndGet();
            add(new JLabel(string), constraints);
            constraints.gridx = 1;
            add(comp, constraints);
        });
        constraints.gridx = 0;
        constraints.gridy = i.incrementAndGet();
        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridwidth = 2;
        button = new JButton("Apply");
        button.addActionListener(listener);
        add(button, constraints);

        setMode((AddMode) addModeComboBox.getSelectedItem());

        setMinimumSize(layout.minimumLayoutSize(getContentPane()));

        pack();
        setVisible(true);
    }

    public void setMode(AddMode mode) {
        this.mode = mode;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getxCoordTextField() {
        return xCoordTextField;
    }

    public JTextField getyCoordTextField() {
        return yCoordTextField;
    }

    public JTextField getAreaTextField() {
        return areaTextField;
    }

    public JTextField getNumberOfRoomsTextField() {
        return numberOfRoomsTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public JCheckBox getBalconyCheckBox() {
        return balconyCheckBox;
    }

    public JTextField getHouseNumberOfLifts() {
        return houseNumberOfLifts;
    }

    public JComboBox<Furnish> getFurnishComboBox() {
        return furnishComboBox;
    }

    public JTextField getHouseNameTextField() {
        return houseNameTextField;
    }

    public JTextField getHouseYearTextField() {
        return houseYearTextField;
    }

    public JTextField getHouseNumberOfFloorsTextField() {
        return houseNumberOfFloorsTextField;
    }

    public JTextField getHouseNumberOfFlatsOnFloorTextField() {
        return houseNumberOfFlatsOnFloorTextField;
    }

    public JButton getButton() {
        return button;
    }
}
