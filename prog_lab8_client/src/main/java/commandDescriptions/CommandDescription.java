package commandDescriptions;

import support.CommandName;

import java.io.Serializable;

public abstract class CommandDescription implements Serializable {
    private static final long serialVersionUID = -7879056808897113735L;
    private final CommandName name;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String password;
    private String login;

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public CommandDescription(CommandName name) {
        this.name = name;
    }

    public CommandName getName() {
        return name;
    }


}
