package presention.main;

import presention.LocaleChanger;
import presention.Model;
import presention.add.AddController;
import presention.add.AddView;
import presention.filter.FilterLessNumberOfRoomsController;
import presention.filter.FilterLessNumberOfRoomsView;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {
    private MainView view;
    private Model model;
    private LocaleChanger localeChanger;
    private Locale ru = new Locale("ru", "RU");
    private Locale chr = new Locale("chr");
    private Locale sp = new Locale("sp", "SP");
    private Locale po = new Locale("po");
    ResourceBundle rb;

    public MainController(MainView view, Model model) {
        localeChanger = new LocaleChanger("bundles.gui");
        localeChanger.addSubscriber(view);

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
            localeChanger.changLocale(new Locale("en","EN"));
            view.getRegisterButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    register();
                }
            });
            view.getLogoutButton().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    logout();
                    

                }
            });

        });
    }



    public void menuItemDidSelect(ActionEvent e) {
        if ("Remove".equals(e.getActionCommand())) {
            RemoveView removeView = new RemoveView(localeChanger);
            RemoveController controller = new RemoveController(removeView, model);
            controller.presentView();
        } else if ("Add".equals(e.getActionCommand())) {
            AddView addView = new AddView(localeChanger);
            AddController controller = new AddController(addView, model);
            controller.presentView();
        } else if ("Update".equals(e.getActionCommand())) {
            UpdateView addView = new UpdateView(localeChanger);
            UpdateController controller = new UpdateController(addView, model);
            controller.presentView();
        } else if("Clear".equals(e.getActionCommand())) {
            model.clear();
        } else if("Information".equals(e.getActionCommand())) {
            model.info();
        } else if("Help".equals(e.getActionCommand())) {
            model.help();
        } else if("Filter".equals(e.getActionCommand())) {
            FilterLessNumberOfRoomsView filterLessNumberOfRoomsView = new FilterLessNumberOfRoomsView();
            FilterLessNumberOfRoomsController controller =
                    new FilterLessNumberOfRoomsController(filterLessNumberOfRoomsView, model);
            controller.presentView();
        } else if("Log Out".equals(e.getActionCommand())) {
            view.setLoginMode(false);
        }
        System.out.println(e.getActionCommand());
    }

    private void logout() {
        view.setLoginMode(false);
    }

    private void login() {
        LoginView loginView = new LoginView(LoginMode.LOGIN, localeChanger);
        LoginController loginController = new LoginController(loginView, model);
        loginController.presentView();
    }

    private void register() {
        LoginView loginView = new LoginView(LoginMode.REGISTER, localeChanger);
        LoginController loginController = new LoginController(loginView, model);
        loginController.presentView();
    }

    private void clear() {

    }
}
