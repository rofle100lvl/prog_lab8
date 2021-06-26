package presention.tablePresentation;

import presention.LocaleChangeable;
import presention.LocaleChanger;
import presention.Model;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class TableView extends JPanel implements LocaleChangeable {
    private JTable table;
    private CardLayout layout;
    private JPanel panel2;
    private JLabel mustLogin;

    public TableView() {
        super(new GridLayout());;
    }

    public void init(TableModel model) {
        layout = new CardLayout();
        setLayout(layout);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel();

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);
        mustLogin = new JLabel();
        panel2.add(mustLogin);

        add("login", panel1);
        add("logout", panel2);
        logoutMode();
    }

    public void loginMode() {
        layout.show(this, "login");
    }

    public void logoutMode() {
        layout.show(this, "logout");
    }

    public JTable getTable() {
        return table;
    }

    @Override
    public void localeDidChange(ResourceBundle resourceBundle, Locale locale) {
        mustLogin.setText(resourceBundle.getString("mustLogin"));
        panel2.repaint();

    }
}
