package presention.remove;

import javax.swing.*;

public enum RemoveMode {
    REMOVE_BY_ID,
    REMOVE_AT;


    @Override
    public String toString() {
        switch (this) {
            case REMOVE_AT:
                return "Remove at..";
            case REMOVE_BY_ID:
                return "Remove by ID";
            default:
                return "";
        }
    }
}
