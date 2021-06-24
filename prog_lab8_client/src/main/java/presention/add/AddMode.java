package presention.add;

public enum AddMode {
    ADD, ADD_IF_MIN;


    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "Add";
            case ADD_IF_MIN:
                return "Add if min";
            default:
                return "";
        }
    }
}
