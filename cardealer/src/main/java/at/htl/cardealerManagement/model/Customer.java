package at.htl.cardealerManagement.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
})
public class Customer extends Pupil{
    public String getIBAN() {
        return IBAN;
    }

    public Customer(String firstName, String lastName, String phoneNumber, String city, String street, int zipCode, LocalDate birthDate, String IBAN) {
        super(firstName, lastName, phoneNumber, city, street, zipCode, birthDate);
        this.IBAN = IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    private String IBAN;

    public Customer() {
    }
}
