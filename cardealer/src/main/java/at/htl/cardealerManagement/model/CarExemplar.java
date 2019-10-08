package at.htl.cardealerManagement.model;

import javax.persistence.*;
@NamedQueries(
        @NamedQuery(name="CarExemplar.findAll",query = "select c from CarExemplar c")
)
@Entity
public class CarExemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mileage;
    private int horsepower;
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarType carType;


    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public CarExemplar(int mileage, int horsepower, String color, CarType carType) {
        this.mileage = mileage;
        this.horsepower = horsepower;
        this.color = color;
        this.carType = carType;
    }

    public CarExemplar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarType getcarType() {
        return carType;
    }

    public void setcarType(CarType carType) {
        this.carType = carType;
    }
}
