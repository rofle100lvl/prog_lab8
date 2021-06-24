package support;


import model.Flat;
import orm.annotations.Element;
import orm.annotations.Id;
import orm.annotations.Table;
import orm.annotations.Unique;

import java.util.ArrayList;

@Table(name = "Users")
public class User {


    @Id
    int id;

    @Unique
    @Element
    private String login;

    @Element
    private String password;

    private ArrayList<Flat> flats;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        flats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }

    public String getLogin() {
        return login;
    }

}
