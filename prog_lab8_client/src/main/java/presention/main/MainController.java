package presention.main;

import presention.Model;
import presention.add.AddController;
import presention.add.AddView;
import presention.login.LoginController;
import presention.login.LoginMode;
import presention.login.LoginView;
import presention.remove.RemoveController;
import presention.remove.RemoveView;
import presention.update.UpdateController;
import presention.update.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainController {
    private MainView view;
    private Model model;

    public MainController(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> {
            view.init(this::menuItemDidSelect, model);
            view.getLoginButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    login();
                }
            });
            view.getRegisterButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    register();
                }
            });
        });
    }



    public void menuItemDidSelect(ActionEvent e) {
        if ("Remove".equals(e.getActionCommand())) {
            RemoveView removeView = new RemoveView();
            RemoveController controller = new RemoveController(removeView, model);
            controller.presentView();
        } else if ("Add".equals(e.getActionCommand())) {
            AddView addView = new AddView();
            AddController controller = new AddController(addView, model);
            controller.presentView();
        } else if ("Update".equals(e.getActionCommand())) {
            UpdateView addView = new UpdateView();
            UpdateController controller = new UpdateController(addView, model);
            controller.presentView();
        }
    }

    private void login() {
        LoginView loginView = new LoginView(LoginMode.LOGIN);
        LoginController loginController = new LoginController(loginView, model);
        loginController.presentView();
    }

    private void register() {
        LoginView loginView = new LoginView(LoginMode.REGISTER);
        LoginController loginController = new LoginController(loginView, model);
        loginController.presentView();
    }
}
