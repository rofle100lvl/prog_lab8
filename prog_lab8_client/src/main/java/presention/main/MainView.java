package presention.main;

import presention.LocaleChangeable;
import presention.Model;
import presention.interactivePresentation.InteractiveView;
import presention.tablePresentation.TableView;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MainView extends JFrame implements LocaleChangeable {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JButton registerButton;
    JMenu editMenu;
    JMenuItem addMenuItem;
    JMenuItem updateMenuItem;
    JMenuItem removeMenuItem;
    JMenuItem clearMenuItem;
    JMenu statsMenu;
    JMenuItem filterLessThanNumberOfRoomsCommandDescription;
    JMenuItem printFieldDescending;
    JMenuItem printUniquePrice;
    JMenu otherMenu;
    JMenuItem infoMenuItem;
    JMenuItem helpMenuItem;
    JMenuItem executeScriptMenuItem;

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    private TableView tableView;
    private InteractiveView interactiveView;

    private boolean isLogin = false;

    public void init(ActionListener listener, Model model) {
        setTitle("");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        configureTabs(model);
        configureMenu(listener);
        configureLowerPane();

        pack();
        setVisible(true);
    }

    private void configureTabs(Model model) {
        // init TabbedPane
        tabbedPane = new JTabbedPane();

        // Add table tab
        tableView = new TableView();
        tableView.init(model);
        tabbedPane.add(tableView);

        tableView.getTable().getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println(2);
                } else {
                    int index = tableView.getTable().columnAtPoint(e.getPoint());
                    model.addComparatorByColumn(index);
                    model.sendToBuffer();
                    repaint();
                }
            }
        });
        // Add interactive tab
        interactiveView = new InteractiveView();
        interactiveView.init(model);
        tabbedPane.add("Interactive presentation", interactiveView);

        // Add TabbedPane
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void configureMenu(ActionListener listener) {
        // Init MenuBar
        menuBar = new JMenuBar();

        // Add edit menu
        editMenu = new JMenu();
        addMenuItem = new JMenuItem();
        addMenuItem.addActionListener(listener);
        updateMenuItem = new JMenuItem();
        updateMenuItem.addActionListener(listener);
        removeMenuItem = new JMenuItem();
        removeMenuItem.addActionListener(listener);
        clearMenuItem = new JMenuItem();
        clearMenuItem.addActionListener(listener);

        editMenu.add(addMenuItem);
        editMenu.addSeparator();
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();
        editMenu.add(removeMenuItem);
        editMenu.addSeparator();
        editMenu.add(clearMenuItem);

        menuBar.add(editMenu);

        // Add statistic
        statsMenu = new JMenu();
        filterLessThanNumberOfRoomsCommandDescription = new JMenuItem();
        filterLessThanNumberOfRoomsCommandDescription.addActionListener(listener);
        printFieldDescending = new JMenuItem();
        printFieldDescending.addActionListener(listener);
        printUniquePrice = new JMenuItem();
        printUniquePrice.addActionListener(listener);

        statsMenu.add(printFieldDescending);
        statsMenu.add(printUniquePrice);
        statsMenu.addSeparator();
        statsMenu.add(filterLessThanNumberOfRoomsCommandDescription);

        menuBar.add(statsMenu);

        // Add other
        otherMenu = new JMenu();

        infoMenuItem = new JMenuItem();
        infoMenuItem.addActionListener(listener);
        helpMenuItem = new JMenuItem();
        helpMenuItem.addActionListener(listener);
        executeScriptMenuItem = new JMenuItem();
        executeScriptMenuItem.addActionListener(listener);

        otherMenu.add(infoMenuItem);
        otherMenu.add(helpMenuItem);
        otherMenu.addSeparator();
        otherMenu.add(executeScriptMenuItem);

        menuBar.add(otherMenu);

        setMenuBarEnabled();
        setTableEnabled();

        add(menuBar, BorderLayout.NORTH);
    }

    private void configureLowerPane() {
        Container panel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
        panel.setLayout(layout);

        add(panel, BorderLayout.SOUTH);

        loginButton = new JButton();
        registerButton = new JButton();
        usernameLabel = new JLabel("1234");
        setUsernameLabelVisible();
        panel.add(usernameLabel);
        panel.add(loginButton);
        panel.add(registerButton);
    }

    public void setLogin(String login) {
        usernameLabel.setText(login);
    }

    public void setUsernameLabelVisible() {
        usernameLabel.setVisible(isLogin);
    }
    public void setMenuBarEnabled() {
        for (MenuElement subElement : menuBar.getSubElements()) {
            ((JMenu) subElement).setEnabled(isLogin);
        }
    }

    public void setTableEnabled() {
        if (isLogin) {
            tableView.loginMode();
            interactiveView.loginMode();
        }
        else {
            tableView.logoutMode();
            interactiveView.logoutMode();
        }
    }

    public void setLoginMode(boolean login) {
        isLogin = login;
        setMenuBarEnabled();
        setTableEnabled();
        setUsernameLabelVisible();
    }

    // Getters

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public TableView getTableView() {
        return tableView;
    }

    @Override
    public void localeDidChange(ResourceBundle resourceBundle, Locale locale) {
        setTitle(resourceBundle.getString("titleName"));
        tabbedPane.setTitleAt(0, resourceBundle.getString("tablePresentation"));
        editMenu.setText(resourceBundle.getString("firstMenu.name"));
        addMenuItem.setText(resourceBundle.getString("firstMenu.field1"));
        updateMenuItem.setText(resourceBundle.getString("firstMenu.field2"));
        removeMenuItem.setText(resourceBundle.getString("firstMenu.field3"));
        clearMenuItem.setText(resourceBundle.getString("firstMenu.field4"));
        statsMenu.setText(resourceBundle.getString("secondMenu.name"));
        filterLessThanNumberOfRoomsCommandDescription.setText(resourceBundle.getString("secondMenu.field1"));
        printFieldDescending.setText(resourceBundle.getString("secondMenu.field2"));
        printUniquePrice.setText(resourceBundle.getString("secondMenu.field3"));
        otherMenu.setText(resourceBundle.getString("thirdMenu.name"));
        infoMenuItem.setText(resourceBundle.getString("thirdMenu.field1"));
        helpMenuItem.setText(resourceBundle.getString("thirdMenu.field2"));
        executeScriptMenuItem.setText(resourceBundle.getString("thirdMenu.field3"));
        loginButton.setText(resourceBundle.getString("downPlane.login"));
        registerButton.setText((resourceBundle.getString("downPlane.register")));
        pack();
    }
}
