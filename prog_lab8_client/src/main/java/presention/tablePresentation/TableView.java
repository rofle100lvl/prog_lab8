package presention.tablePresentation;

import presention.Model;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

public class TableView extends JPanel {
    private JTable table;
    private CardLayout layout;

    public TableView() {
        super(new GridLayout());
    }

    public void init(TableModel model) {
        layout = new CardLayout();
        setLayout(layout);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel1.add(scrollPane);

        panel2.add(new JLabel("You should LogIn to get TABLE!!!"));

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



}
