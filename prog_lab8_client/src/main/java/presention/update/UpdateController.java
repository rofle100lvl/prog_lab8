package presention.update;

import presention.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpdateController {
    private UpdateView view;
    private Model model;

    public UpdateController(UpdateView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        System.out.println("Updated");
    }
}
