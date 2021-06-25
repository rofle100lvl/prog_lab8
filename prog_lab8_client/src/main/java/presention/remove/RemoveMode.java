package presention.remove;

import javax.swing.*;

public enum RemoveMode {
    REMOVE_BY_ID,
    REMOVE_HEAD;


    @Override
    public String toString() {
        switch (this) {
            case REMOVE_HEAD:
                return "Remove head";
            case REMOVE_BY_ID:
                return "Remove by ID";
            default:
                return "";
        }
    }
}
