package monkeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.Validate;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store

@Entity
public class Monkey {

  private @Id @GeneratedValue long id;

  private String species;
  private String firstName;
  private String lastName;

  public Monkey() {}

  public Monkey(String firstName, String lastName, String species) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.species = species;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() { return this.firstName + " " + this.lastName; }

  public String getFirstName() { return this.firstName; }

  public String getLastName() { return this.lastName; }

  public String getSpecies() {
    return this.species;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
  }

  public void setFirstName(String firstName) { this.firstName = firstName; }

  public void setLastName(String lastName) { this.lastName = lastName; }

  public void setSpecies(String species) {
    this.species = species;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Monkey))
      return false;
    Monkey monkey = (Monkey) o;
    return Objects.equals(this.id, monkey.id) && Objects.equals(this.firstName, monkey.firstName)
            && Objects.equals(this.lastName, monkey.lastName) && Objects.equals(this.species, monkey.species);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName, this.species);
  }

  @Override
  public String toString() {
    return "Monkey{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + "', species='" + this.species + '\'' + '}';
  }
}