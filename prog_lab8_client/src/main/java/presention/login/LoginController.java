package presention.login;

import presention.Model;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private Model model;

    public LoginController(LoginView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init());
    }
}
