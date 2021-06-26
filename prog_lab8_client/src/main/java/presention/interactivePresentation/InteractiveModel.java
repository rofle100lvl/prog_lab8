package presention.interactivePresentation;

import java.awt.*;

public interface InteractiveModel {
    double getX(int index);
    double getY(int index);
    double getRadius(int index);
    String getName(int index);
    Color getColor(int index);
    int getRowCount();
}
