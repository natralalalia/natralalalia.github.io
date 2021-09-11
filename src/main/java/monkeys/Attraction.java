package monkeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.Validate;
import org.w3c.dom.Attr;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store

@Entity
public class Attraction implements Serializable {

    private @Id @GeneratedValue(strategy= GenerationType.IDENTITY) long id;

    private String name;
    private String description;
    private String address;
    private String image;
    private double score;
    private float latitude;
    private float longitude;

    public Attraction() {}

    public Attraction(String name, String description, String address, String image, double score, float latitude, float longitude) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.image = image;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public float getLatitude() {return this.latitude;}

    public float getLongitude() {return this.longitude;}

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

    public void setLatitude(float latitude) {this.latitude = latitude;}

    public void setLongitude(float longitude) {this.longitude = longitude;}

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
                Objects.equals(this.score, attraction.score) &&
                Objects.equals(this.latitude, attraction.latitude) &&
                Objects.equals(this.longitude, attraction.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address, this.description, this.image, this.score, this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Attraction{" + "id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + "', description='" + this.description + '\'' + '}';
    }
}