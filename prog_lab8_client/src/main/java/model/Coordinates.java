package model;

import orm.annotations.NotNull;
import orm.annotations.Element;
import orm.annotations.Id;
import orm.annotations.Table;

import java.io.Serializable;


/**
 * Начальный класс координат
 */
@Table(name = "Coordinates")
public class Coordinates implements Serializable {
    @Override
    public String toString() {
        return "Coordinates{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    @Id
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Element
    private float x;

    @Element
    @NotNull
    private Float y; //Поле не может быть null


    public Coordinates(float x, Float y){
        this.x = x;
        this.y=y;
    }



    public void setX(float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Coordinates() {
    }
}
