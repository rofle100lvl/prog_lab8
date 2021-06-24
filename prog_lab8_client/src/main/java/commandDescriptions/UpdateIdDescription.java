package commandDescriptions;

import model.Flat;
import support.CommandName;

public class UpdateIdDescription extends CommandDescription{
    Flat flat;
    int id;

    public UpdateIdDescription(Flat flat, int id) {
        super(CommandName.UPDATEID);
        this.flat=flat;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public Flat getFlat() {
        return flat;
    }
}
