import presention.Model;
import presention.add.AddView;
import presention.main.MainController;
import presention.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        System.out.println(new BigDecimal("123.3232"));
        MainView view = new MainView();
        Model model = new Model(view);
        MainController controller = new MainController(view, model);
        controller.presentView();

    }
}
