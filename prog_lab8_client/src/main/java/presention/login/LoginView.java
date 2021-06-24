package presention.login;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JButton button;

    public void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");

        loginTextField = new JTextField(10);
        passwordTextField = new JTextField(10);
        button = new JButton("Apply");

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 10, 5, 10);
        add(new JLabel("Login:"), constraints);
        constraints.gridx = 1;
        add(loginTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Password:"), constraints);
        constraints.gridx = 1;
        add(passwordTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(button, constraints);

        setMinimumSize(getLayout().minimumLayoutSize(getContentPane()));
        pack();
        setVisible(true);
    }

    // Getteer
    public JTextField getLoginTextField() {
        return loginTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getButton() {
        return button;
    }
}
