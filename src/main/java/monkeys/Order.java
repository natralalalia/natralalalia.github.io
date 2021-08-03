package monkeys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {
    private @Id
    @GeneratedValue Long id;

    private String name;
    private String description;
    private Status status;

    Order() {}

    Order(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
                && this.status == order.status && this.name == order.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + this.id + ", name = " + this.name + ", description='" + this.description + '\'' + ", status=" + this.status + '}';
    }
}
