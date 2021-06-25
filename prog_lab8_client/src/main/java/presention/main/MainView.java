package presention.main;

import presention.Model;
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

public class MainView extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    private JLabel usernameLabel;
    private JButton loginButton;
    private JButton registerButton;
    Locale ru = new Locale("ru", "RU");
    Locale chr = new Locale("chr");
    Locale sp = new Locale("sp", "SP");
    Locale po = new Locale("po");
    ResourceBundle rb;

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    private TableView tableView;

    private boolean isLogin = false;

    public void init(ActionListener listener, Model model) {
        rb = ResourceBundle.getBundle("bundles.gui");
        setTitle(rb.getString("titleName"));

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
        tabbedPane.add(rb.getString("tablePresentation"), tableView);

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
        // Add TabbedPane
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void configureMenu(ActionListener listener) {
        // Init MenuBar
        menuBar = new JMenuBar();

        // Add edit menu
        JMenu editMenu = new JMenu(rb.getString("firstMenu.name"));
        JMenuItem addMenuItem = new JMenuItem(rb.getString("firstMenu.field1"));
        addMenuItem.addActionListener(listener);
        JMenuItem updateMenuItem = new JMenuItem(rb.getString("firstMenu.field2"));
        updateMenuItem.addActionListener(listener);
        JMenuItem remove = new JMenuItem(rb.getString("firstMenu.field3"));
        remove.addActionListener(listener);
        JMenuItem clearMenuItem = new JMenuItem(rb.getString("firstMenu.field4"));
        clearMenuItem.addActionListener(listener);

        editMenu.add(addMenuItem);
        editMenu.addSeparator();
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();
        editMenu.add(remove);
        editMenu.addSeparator();
        editMenu.add(clearMenuItem);

        menuBar.add(editMenu);

        // Add statistic
        JMenu statsMenu = new JMenu(rb.getString("secondMenu.name"));
        JMenuItem filterLessThanNumberOfRoomsCommandDescription = new JMenuItem(rb.getString("secondMenu.field1"));
        filterLessThanNumberOfRoomsCommandDescription.addActionListener(listener);
        JMenuItem printFieldDescending= new JMenuItem(rb.getString("secondMenu.field2"));
        printFieldDescending.addActionListener(listener);
        JMenuItem printUniquePrice = new JMenuItem(rb.getString("secondMenu.field3"));
        printUniquePrice.addActionListener(listener);

        statsMenu.add(printFieldDescending);
        statsMenu.add(printUniquePrice);
        statsMenu.addSeparator();
        statsMenu.add(filterLessThanNumberOfRoomsCommandDescription);

        menuBar.add(statsMenu);

        // Add other
        JMenu otherMenu = new JMenu(rb.getString("thirdMenu.name"));

        JMenuItem infoMenuItem = new JMenuItem(rb.getString("thirdMenu.field1"));
        infoMenuItem.addActionListener(listener);
        JMenuItem helpMenuItem = new JMenuItem(rb.getString("thirdMenu.field2"));
        helpMenuItem.addActionListener(listener);
        JMenuItem executeScriptMenuItem = new JMenuItem(rb.getString("thirdMenu.field3"));
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

        loginButton = new JButton(rb.getString("downPlane.login"));
        registerButton = new JButton(rb.getString("downPlane.register"));
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
        if (isLogin) tableView.loginMode();
        else tableView.logoutMode();
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
}
