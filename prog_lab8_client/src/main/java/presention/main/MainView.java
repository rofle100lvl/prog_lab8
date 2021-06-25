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
    Locale sp = new Locale("sp");
    Locale po = new Locale("po");
    File ru_Loc = new File("bundles/GuiLabels_ru.properties");
    File po_Loc = new File("bundles/GuiLabels_po.properties");
    ResourceBundle rb;

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    private TableView tableView;

    private boolean isLogin = false;

    public void init(ActionListener listener, Model model) {
        rb = ResourceBundle.getBundle("bundles.GuiLabels", ru);
        System.out.println(rb.getString("login"));
        setTitle("Storage of Study Groups");
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
        tabbedPane.add("Table presentation", tableView);

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
        JMenu editMenu = new JMenu("Edit Collection");
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.addActionListener(listener);
        JMenuItem updateMenuItem = new JMenuItem("Update");
        updateMenuItem.addActionListener(listener);
        JMenuItem remove = new JMenuItem("Remove");
        remove.addActionListener(listener);
        JMenuItem removeHead = new JMenuItem("Remove head");
        removeHead.addActionListener(listener);
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(listener);

        editMenu.add(addMenuItem);
        editMenu.addSeparator();
        editMenu.add(updateMenuItem);
        editMenu.addSeparator();
        editMenu.add(remove);
        editMenu.add(removeHead);
        editMenu.addSeparator();
        editMenu.add(clearMenuItem);

        menuBar.add(editMenu);

        // Add statistic
        JMenu statsMenu = new JMenu("Statistic");
        JMenuItem filterLessThanNumberOfRoomsCommandDescription = new JMenuItem("Filter");
        filterLessThanNumberOfRoomsCommandDescription.addActionListener(listener);
        JMenuItem printFieldDescending= new JMenuItem("Print field descending");
        printFieldDescending.addActionListener(listener);
        JMenuItem printUniquePrice = new JMenuItem("Print unique price");
        printUniquePrice.addActionListener(listener);

        statsMenu.add(printFieldDescending);
        statsMenu.add(printUniquePrice);
        statsMenu.addSeparator();
        statsMenu.add(filterLessThanNumberOfRoomsCommandDescription);

        menuBar.add(statsMenu);

        // Add other
        JMenu otherMenu = new JMenu("Other");

        JMenuItem infoMenuItem = new JMenuItem("Information");
        infoMenuItem.addActionListener(listener);
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(listener);
        JMenuItem executeScriptMenuItem = new JMenuItem("Execute script");
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

        loginButton = new JButton("Log in");
        registerButton = new JButton("Register");
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
