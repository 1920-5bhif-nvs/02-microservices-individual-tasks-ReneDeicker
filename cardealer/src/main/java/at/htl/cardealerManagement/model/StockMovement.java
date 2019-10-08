package at.htl.cardealerManagement.model;

import javax.persistence.*;

@Entity
public class StockMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer newCarOwner;

    @OneToOne (cascade = CascadeType.ALL)
    private Employee seller;

    @OneToOne (cascade = CascadeType.ALL)
    private CarExemplar car;

    public StockMovement(Customer newCarOwner, Employee seller, CarExemplar car) {
        this.newCarOwner = newCarOwner;
        this.seller = seller;
        this.car = car;
    }

    public Customer getNewCarOwner() {
        return newCarOwner;
    }

    public void setNewCarOwner(Customer newCarOwner) {
        this.newCarOwner = newCarOwner;
    }

    public Employee getSeller() {
        return seller;
    }

    public void setSeller(Employee seller) {
        this.seller = seller;
    }

    public CarExemplar getCar() {
        return car;
    }

    public void setCar(CarExemplar car) {
        this.car = car;
    }

    public StockMovement() {
    }
}
