package presention.update;

import model.Furnish;
import presention.LocaleChangeable;
import presention.LocaleChanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UpdateView extends JFrame implements LocaleChangeable {
    private JTextField idTextField;
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
    private JLabel idLabel;
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

    public UpdateView(LocaleChanger localeChanger) {
        this.localeChanger = localeChanger;
    }

    public void init(ActionListener listener) {
        setTitle("Update");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        idTextField = new JTextField(10);
        idLabel = new JLabel();
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

        Map<JLabel, JComponent> stringJComponentMap = new LinkedHashMap<>();
        stringJComponentMap.put(idLabel, idTextField);
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

        constraints.insets = new Insets(5, 10, 5, 10);
        AtomicInteger i = new AtomicInteger(0);
        stringJComponentMap.forEach((jLabel, comp) -> {
            constraints.gridx = 0;
            constraints.gridy = i.incrementAndGet();
            add(jLabel, constraints);
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

        setMinimumSize(layout.minimumLayoutSize(getContentPane()));

        pack();
        setVisible(true);
        localeChanger.addSubscriber(this);
    }

    public JTextField getIdTextField() {
        return idTextField;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getxCoordTextField() {
        return xCoordTextField;
    }

    public JTextField getHouseNumberOfLifts() {
        return houseNumberOfLifts;
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
        setTitle(resourceBundle.getString("update.title"));
        idLabel.setText(resourceBundle.getString("update.field1"));
        nameLabel.setText(resourceBundle.getString("update.field2"));
        xCoordLabel.setText(resourceBundle.getString("update.field3"));
        yCoordLabel.setText(resourceBundle.getString("update.field4"));
        areaLabel.setText(resourceBundle.getString("update.field5"));
        numberOfRoomsLabel.setText(resourceBundle.getString("update.field6"));
        priceLabel.setText(resourceBundle.getString("update.field7"));
        balconyLabel.setText(resourceBundle.getString("update.field8"));
        balconyCheckBox.setText(resourceBundle.getString("update.field8Checked"));
        furnishLabel.setText(resourceBundle.getString("update.field9"));
        houseNameLabel.setText(resourceBundle.getString("update.field10"));
        houseYearLabel.setText(resourceBundle.getString("update.field11"));
        numberOfFloorsLabel.setText(resourceBundle.getString("update.field12"));
        numberOfFlatsOnFloorLabel.setText(resourceBundle.getString("update.field13"));
        numberOfLiftsLabel.setText(resourceBundle.getString("update.field14"));
        button.setText(resourceBundle.getString("update.confirm"));
    }
}
