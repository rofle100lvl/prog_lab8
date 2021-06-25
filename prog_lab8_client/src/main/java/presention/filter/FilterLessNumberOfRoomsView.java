package presention.filter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FilterLessNumberOfRoomsView extends JFrame {
    public static int REMOVE_AT = 0;
    public static int REMOVE_BY_ID = 1;
    //    private RemoveMode mode;
    private JTextField indexTextField;
    private JButton button;
    private JLabel label;

    public FilterLessNumberOfRoomsView() {
        super();

    }

    public void init(ActionListener listener) {
        setTitle("Filter");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 5, 10);
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        add(new JLabel("Mode:"), constraints);
//
//        constraints.insets = new Insets(5, 10, 5, 10);
//        constraints.gridx = 1;
//        constraints.gridy = 0;
//        JComboBox<RemoveMode> comp = new JComboBox<>(RemoveMode.values());
//        comp.addItemListener(e -> {
//            setMode((RemoveMode) e.getItem());
//        });
//        add(comp, constraints);

        constraints.gridx = 0;
        constraints.gridy = 0; // TODO: set to 1
        label = new JLabel("Less than:");
        add(label, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0; // TODO: set to 1
        indexTextField = new JTextField(10);
        add(indexTextField, constraints);

        constraints.insets = new Insets(5, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 1; // TODO: set to 2
        constraints.gridwidth = 2;
        button = new JButton("Filter");
        button.addActionListener(listener);
        add(button, constraints);


        setMinimumSize(layout.minimumLayoutSize(getContentPane()));

        pack();
        setVisible(true);
    }

    public String getText() {
        return indexTextField.getText();
    }

    public JTextField getIndexTextField() {
        return indexTextField;
    }

}
