package commandDescriptions;

import support.CommandName;

public class RemoveByIdDescription extends CommandDescription{
    int id;
    public RemoveByIdDescription(int id) {
        super(CommandName.REMOVEBYID);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
