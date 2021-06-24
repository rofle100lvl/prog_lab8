package presention.add;

import presention.Model;
import presention.remove.RemoveView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddController {
    private AddView view;
    private Model model;

    public AddController(AddView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        System.out.println("Added");
    }
}
