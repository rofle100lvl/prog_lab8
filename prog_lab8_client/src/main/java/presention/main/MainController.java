package presention.main;

import presention.Model;
import presention.add.AddController;
import presention.add.AddView;
import presention.remove.RemoveController;
import presention.remove.RemoveView;
import presention.update.UpdateController;
import presention.update.UpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainController {
    private MainView view;
    private Model model;

    public MainController(MainView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::menuItemDidSelect, model));
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
}
