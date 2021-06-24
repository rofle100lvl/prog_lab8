package model;

import annotations.Between;
import annotations.GreaterThan;
import annotations.NotEqualString;
import annotations.NotNulll;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import orm.annotations.Element;
import orm.annotations.Id;
import orm.annotations.Table;
import support.User;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Начальный коасс квартир
 */

@Table(name = "Flats")
public class Flat implements Comparable<Flat>, Serializable {
    @Id
    private int id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private Integer owner_id;


    @Element
    @NotNulll
    @NotEqualString
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Element
    @NotNulll
    private Coordinates coordinates; //Поле не может быть null

    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Element
    @GreaterThan(num=0)
    private float area; //Значение поля должно быть больше 0

    @Element
    @NotNulll
    @GreaterThan(num=0)
    private Long numberOfRooms; //Поле может быть null, Значение поля должно быть больше 0

    @Element
    @Between(from=0,to=121887043)
    private Integer price; //Максимальное значение поля: 121887043, Значение поля должно быть больше 0

    @Element
    @NotNulll
    private Boolean balcony; //Поле может быть null

    @NotNulll
    @Element
    private Furnish furnish; //Поле может быть null

    @Element
    @NotNulll
    private House house; //Поле может быть null

    public Flat() {
        house = new House();
        coordinates = new Coordinates();
        numberOfRooms = new Long(0);
        creationDate = ZonedDateTime.now();
    }

    public Flat(String name){
        this.name=name;
        creationDate=ZonedDateTime.now();

    }

    public Integer getUser() {
        return owner_id;
    }

    public void setUser(User owner) {
        this.owner_id = owner.getId();
    }

    @Getter
    public Integer getPrice() {
        return price;
    }

    @Getter
    public float getArea() {
        return area;
    }

    @Getter
    public String getName() {
        return name;
    }

    @Getter
    public Long getNumberOfRooms() {
        return numberOfRooms;
    }
    @Getter
    public int getId() {
        return id;
    }

    @Getter
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Getter
    public Boolean getBalcony() {
        return balcony;
    }

    @Getter
    public Furnish getFurnish() {
        return furnish;
    }
    @Getter
    public House getHouse() {
        return house;
    }

    @Setter
    public void setId(int id) {
        coordinates.setId(1);
        house.setId(1);
        this.id = id;
    }

    @Setter
    public void setName(String name) {
        this.name = name;
    }

    @Setter
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Setter
    public void setCoordinates(float x, Float y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }

    @Setter
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
   }

    @Setter
    public void setArea(float area) {
        this.area = area;
    }

    @Setter
    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Setter
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Setter
    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    @Setter
    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    @Setter
    public void setHouse(House house) {
        this.house = house;
    }

    @Setter
    public void setHouse(String name, Long year, Integer numberOfFloors, int numberOfFlatsOnFloor, Long numberOfLifts) {
        house.setName(name);
        house.setYear(year);
        house.setNumberOfFloors(numberOfFloors);
        house.setNumberOfFlatsOnFloor(numberOfFlatsOnFloor);
        house.setNumberOfLifts(numberOfLifts);
    }



    public boolean greaterThan(Flat b){
        if(price>b.getPrice())return true;
        return false;
    }

    public String niceToString() {
        return "\t\t\t\t\t\tКВАРТUРА " + id + "\n" +
                "Номер квартиры: " + id +
                ", имя собственника: " + name +
                ", координаты квартиры: (" + coordinates.getX() + ", " + coordinates.getY() + ")" +
                ",\n время добавления квартиры в коллекцию: " + creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")) +
                ",\n цена квартиры: " + price  +
                ", число комнат: " + numberOfRooms +
                ", площадь квартиры: " + area  + " кв.м." +
                ", наличие балкона: " + balcony +
                ", ремонт: " + furnish +
                ",\n данные дома {Название - " + house.getName() + ", год постройки - " + house.getYear() + ", число квартир на этаже - " + house.getNumberOfFlatsOnFloor() + "}";
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                ", balcony=" + balcony +
                ", furnish=" + furnish +
                ", house=" + house +
                '}';
    }

    @Override
    public int compareTo(Flat o) {
        return this.price-o.price;
    }

}
