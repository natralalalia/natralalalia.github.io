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

  private String name;
  private String species;

  public Monkey() {}

  public Monkey(String name, String species) {
//    if (name.equals("")){
//      throw new IndexOutOfBoundsException();
//    }
    this.name = name;
    this.species = species;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() { return this.name; }

  public String getSpecies() {
    return this.species;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

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
    return Objects.equals(this.id, monkey.id) && Objects.equals(this.name, monkey.name)
        && Objects.equals(this.species, monkey.species);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.species);
  }

  @Override
  public String toString() {
    return "Monkey{" + "id=" + this.id + ", name='" + this.name + '\'' + ", species='" + this.species + '\'' + '}';
  }
}