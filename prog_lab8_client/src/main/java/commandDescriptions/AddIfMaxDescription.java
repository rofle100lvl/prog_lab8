package commandDescriptions;

import model.Flat;
import support.CommandName;

public class AddIfMaxDescription extends CommandDescription{
    Flat flat;

    public AddIfMaxDescription(Flat flat) {
        super(CommandName.ADDIFMAX);
        this.flat = flat;

    }

    public Flat getFlat() {
        return flat;
    }
}
