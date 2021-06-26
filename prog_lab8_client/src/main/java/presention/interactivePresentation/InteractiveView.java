package presention.interactivePresentation;

import javax.swing.*;
import java.awt.*;

public class InteractiveView extends JPanel {
    private CardLayout layout;

    public InteractiveView() {
        super(new GridLayout());
    }

    public void init(InteractiveModel model) {
        layout = new CardLayout();
        setLayout(layout);

        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel();

        Canvas canvas = new CanvasForInteractive(model);
        canvas.setSize(panel1.getSize());
        panel1.add(canvas);

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
