package presention.filter;

import model.Coordinates;
import model.Flat;
import presention.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FilterLessNumberOfRoomsController {
    private FilterLessNumberOfRoomsView view;
    private Model model;

    public FilterLessNumberOfRoomsController(FilterLessNumberOfRoomsView view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void presentView() {
        SwingUtilities.invokeLater(() -> view.init(this::didTapApply));
    }

    public void didTapApply(ActionEvent e) {
        filter();
    }

    private void filter() {
        view.dispose();

    }
}
