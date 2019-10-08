package at.htl.cardealerManagement.business;

import at.htl.cardealerManagement.model.*;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class InitBean {
    @Inject
    EntityManager em;

    @Transactional
    void init(@Observes StartupEvent ev){
        /*
        sudo docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name postgres-quarkus-hibernate -e POSTGRES_USER=hibernate -e POSTGRES_PASSWORD=hibernate -e POSTGRES_DB=hibernate_db -p 5432:5432 postgres:10.5
        * */
        System.out.println("+++ it works");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Customer customer1 = new Customer("Hans", "Mayer", "069917836554", "Linz", "Hafenstraße", 4020, LocalDate.parse("1958-03-10", formatter), "AT568asf9945");
        Customer customer2 = new Customer("Max", "Mustermann", "06648946354", "Scharnstein", "Bergstraße", 4644, LocalDate.parse("1965-06-05", formatter), "ATas89asd385");
        em.persist(customer1);
        em.persist(customer2);

        Employee employee1 = new Employee("Hans", "Luger", "06648946354", "Linz", "Hafenstraße", 4020, LocalDate.parse("1965-06-05", formatter), 4300, "6598220698");
        Employee employee2 = new Employee("Werner", "Dorfer", "069936498226", "Linz", "Hafenstraße", 4020, LocalDate.parse("1973-08-11", formatter), 4300, "9836221303");
        em.persist(employee1);
        em.persist(employee2);

        CarType carType = new CarType("Audi", "A4");
        em.persist(carType);

        CarExemplar exemplar1 = new CarExemplar(50000, 140, "black", carType);
        CarExemplar exemplar2 = new CarExemplar(21000, 180, "blue", carType);
        em.persist(exemplar1);
        em.persist(exemplar2);

        StockMovement stockMovement = new StockMovement(customer1, employee1, exemplar1);
        em.persist(stockMovement);
    }
}
