package monkeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.Validate;
import org.w3c.dom.Attr;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store

@Entity
public class Attraction implements Serializable {

    private @Id @GeneratedValue long id;

    private String name;
    private String description;
    private String address;
    private String image;
    private double score;

    public Attraction() {}

    public Attraction(String name, String description, String address, String image, double score) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.score = score;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() { return this.name; }

    public String getDescription() { return this.description; }

    public String getAddress() {
        return this.address;
    }

    public String getImage() {return this.image;}

    public double getScore() {return this.score;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(String image) {this.image = image;}

    public void setScore(double score) {this.score = score;}

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Attraction))
            return false;
        Attraction attraction = (Attraction) o;
        return Objects.equals(this.id, attraction.id) &&
                Objects.equals(this.name, attraction.name) &&
                Objects.equals(this.description, attraction.description) &&
                Objects.equals(this.address, attraction.address) &&
                Objects.equals(this.image, attraction.image) &&
                Objects.equals(this.score, attraction.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address, this.description, this.image, this.score);
    }

    @Override
    public String toString() {
        return "Attraction{" + "id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + "', description='" + this.description + '\'' + '}';
    }
}