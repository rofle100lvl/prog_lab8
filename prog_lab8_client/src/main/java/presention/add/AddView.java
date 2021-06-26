package presention.add;

import model.Furnish;
import presention.LocaleChangeable;
import presention.LocaleChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AddView extends JFrame implements LocaleChangeable {
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
    private LocaleChanger localeChanger;
    private JLabel nameLabel;
    private JLabel xCoordLabel;
    private JLabel yCoordLabel;
    private JLabel areaLabel;
    private JLabel numberOfRoomsLabel;
    private JLabel priceLabel;
    private JLabel balconyLabel;
    private JLabel furnishLabel;
    private JLabel houseNameLabel;
    private JLabel houseYearLabel;
    private JLabel numberOfFloorsLabel;
    private JLabel numberOfFlatsOnFloorLabel;
    private JLabel numberOfLiftsLabel;
    private JLabel modeLabel;
    private JLabel applyLabel;

    public AddView(LocaleChanger localeChanger) {
        this.localeChanger = localeChanger;
    }


    public void init(ActionListener listener) {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        nameTextField = new JTextField(10);
        nameLabel = new JLabel();
        xCoordTextField = new JTextField(10);
        xCoordLabel =new JLabel();
        yCoordTextField = new JTextField(10);
        yCoordLabel = new JLabel();
        areaTextField = new JTextField(10);
        areaLabel = new JLabel();
        numberOfRoomsTextField = new JTextField(10);
        numberOfRoomsLabel = new JLabel();
        priceTextField = new JTextField(10);
        priceLabel = new JLabel();
        balconyCheckBox = new JCheckBox();
        balconyLabel = new JLabel();
        furnishComboBox = new JComboBox<>(Furnish.values());
        furnishLabel = new JLabel();
        houseNameTextField = new JTextField(10);
        houseNameLabel = new JLabel();
        houseYearTextField = new JTextField(10);
        houseYearLabel = new JLabel();
        houseNumberOfFloorsTextField = new JTextField(10);
        numberOfFloorsLabel = new JLabel();
        houseNumberOfFlatsOnFloorTextField = new JTextField(10);
        numberOfFlatsOnFloorLabel = new JLabel();
        houseNumberOfLifts = new JTextField(10);
        numberOfLiftsLabel = new JLabel();
        modeLabel = new JLabel();

        JComboBox<AddMode> addModeComboBox = new JComboBox<>(AddMode.values());
        addModeComboBox.addItemListener(e -> setMode((AddMode) e.getItem()));

        Map<JLabel, JComponent> stringJComponentMap = new LinkedHashMap<>();
        stringJComponentMap.put(nameLabel,nameTextField);
        stringJComponentMap.put(xCoordLabel,xCoordTextField);
        stringJComponentMap.put(yCoordLabel, yCoordTextField);
        stringJComponentMap.put(areaLabel, areaTextField);
        stringJComponentMap.put(numberOfRoomsLabel, numberOfRoomsTextField);
        stringJComponentMap.put(priceLabel, priceTextField);
        stringJComponentMap.put(balconyLabel, balconyCheckBox);
        stringJComponentMap.put(furnishLabel, furnishComboBox);
        stringJComponentMap.put(houseNameLabel, houseNameTextField);
        stringJComponentMap.put(houseYearLabel, houseYearTextField);
        stringJComponentMap.put(numberOfFloorsLabel, houseNumberOfFloorsTextField);
        stringJComponentMap.put(numberOfFlatsOnFloorLabel, houseNumberOfFlatsOnFloorTextField);
        stringJComponentMap.put(numberOfLiftsLabel, houseNumberOfLifts);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 5, 10);
        add(modeLabel, constraints);
        constraints.gridx = 1;
        add(addModeComboBox, constraints);
        constraints.insets = new Insets(5, 10, 5, 10);
        AtomicInteger i = new AtomicInteger(0);
        stringJComponentMap.forEach((label, comp) -> {
            constraints.gridx = 0;
            constraints.gridy = i.incrementAndGet();
            add(label, constraints);
            constraints.gridx = 1;
            add(comp, constraints);
        });
        constraints.gridx = 0;
        constraints.gridy = i.incrementAndGet();
        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridwidth = 2;
        button = new JButton();
        button.addActionListener(listener);
        add(button, constraints);

        setMode((AddMode) addModeComboBox.getSelectedItem());

        setMinimumSize(layout.minimumLayoutSize(getContentPane()));

        pack();
        setVisible(true);
        localeChanger.addSubscriber(this);
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

    @Override
    public void localeDidChange(ResourceBundle resourceBundle, Locale locale) {
        setTitle(resourceBundle.getString("add.title"));
        nameLabel.setText(resourceBundle.getString("add.field1"));
        xCoordLabel.setText(resourceBundle.getString("add.field2"));
        yCoordLabel.setText(resourceBundle.getString("add.field3"));
        areaLabel.setText(resourceBundle.getString("add.field4"));
        numberOfRoomsLabel.setText(resourceBundle.getString("add.field5"));
        priceLabel.setText(resourceBundle.getString("add.field6"));
        balconyLabel.setText(resourceBundle.getString("add.field7"));
        balconyCheckBox.setText(resourceBundle.getString("add.field7Checked"));
        furnishLabel.setText(resourceBundle.getString("add.field8"));
        houseNameLabel.setText(resourceBundle.getString("add.field9"));
        houseYearLabel.setText(resourceBundle.getString("add.field10"));
        numberOfFloorsLabel.setText(resourceBundle.getString("add.field11"));
        numberOfFlatsOnFloorLabel.setText(resourceBundle.getString("add.field12"));
        numberOfLiftsLabel.setText(resourceBundle.getString("add.field13"));
        button.setText(resourceBundle.getString("add.confirm"));




    }
}
