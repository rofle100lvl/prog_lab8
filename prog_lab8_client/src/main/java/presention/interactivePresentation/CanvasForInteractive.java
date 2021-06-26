package presention.interactivePresentation;

import java.awt.*;
import java.util.Locale;

public class CanvasForInteractive extends Canvas {
    private InteractiveModel model;
    private double maxSizeKoef = 0.2;

    public CanvasForInteractive(InteractiveModel model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics g) {
        Font newFont = new Font("Dialog", Font.BOLD, 12);
        g.setFont(newFont);
        for (int i = 0; i < model.getRowCount(); i++) {
            g.setColor(model.getColor(i));
            int x = (int) Math.round(0.1 + model.getX(i) * getWidth() * 0.8);
            int y = (int) Math.round(model.getY(i) * getHeight() * 0.75 + 0.05 * getHeight());
            int round = (int) Math.round(model.getRadius(i) * Math.min(getHeight(), getWidth()) * maxSizeKoef);
            g.fillOval(x, y, round, round);
            g.setColor(Color.BLACK);
            g.drawString(model.getName(i), x, y);
        }
    }
}
