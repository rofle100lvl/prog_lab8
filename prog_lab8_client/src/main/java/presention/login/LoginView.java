package presention.login;

import presention.LocaleChangeable;
import presention.LocaleChanger;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginView extends JFrame implements LocaleChangeable {
    private JTextField loginTextField;
    private JTextField passwordTextField;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JButton button;
    private LoginMode loginMode;
    private LocaleChanger localeChanger;

    public LoginView(LoginMode loginMode, LocaleChanger localeChanger) throws HeadlessException {
        this.loginMode = loginMode;
        this.localeChanger = localeChanger;
    }

    public void init() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        loginTextField = new JTextField(10);
        passwordTextField = new JPasswordField(10);
        button = new JButton();

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        loginLabel = new JLabel();
        passwordLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 10, 5, 10);
        add(loginLabel, constraints);
        constraints.gridx = 1;
        add(loginTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(passwordLabel, constraints);
        constraints.gridx = 1;
        add(passwordTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(button, constraints);

        setMinimumSize(getLayout().minimumLayoutSize(getContentPane()));
        pack();
        setVisible(true);
        localeChanger.addSubscriber(this);
    }

    // Getter
    public JTextField getLoginTextField() {
        return loginTextField;
    }

    public LoginMode getLoginMode() {
        return loginMode;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getButton() {
        return button;
    }

    @Override
    public void localeDidChange(ResourceBundle resourceBundle, Locale locale) {
        setTitle(loginMode.equals(LoginMode.LOGIN) ? resourceBundle.getString("loginView.login")
                :  resourceBundle.getString("loginView.register"));
        loginLabel.setText(resourceBundle.getString("loginView.getLogin"));
        passwordLabel.setText(resourceBundle.getString("loginView.getPassword"));
        button.setText(resourceBundle.getString("loginView.getAccept"));
    }
}
