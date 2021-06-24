package presention.login;

import presention.Model;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {
    private LoginView view;
    private Model model;

    public LoginController(LoginView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> {
            view.init();
            view.getButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    login();
                }
            });

        });
    }

    private void login() {
        String login = view.getLoginTextField().getText();
        String password = view.getPasswordTextField().getText();
        model.login(login,password);
        view.dispose();
    }
}
