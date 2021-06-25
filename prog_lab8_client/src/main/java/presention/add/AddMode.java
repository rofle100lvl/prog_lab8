package presention.add;

public enum AddMode {
    ADD, ADD_IF_MAX;


    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "Add";
            case ADD_IF_MAX:
                return "Add if max";
            default:
                return "";
        }
    }
}
