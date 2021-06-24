import presention.Model;
import presention.add.AddView;
import presention.main.MainController;
import presention.main.MainView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

//        MainView view = new MainView();
//        Model model = new Model(view);
//        MainController controller = new MainController(view, model);
//        controller.presentView();

        SwingUtilities.invokeLater(() -> {
            new AddView().init();
        });
    }
}
