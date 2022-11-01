package entity;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coffee")
public class CoffeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Type(type = "CustomJsonType")

    private com.ntnv.gldva.json.CustomJSONb description;

    public CoffeeEntity() {
    }

    public CoffeeEntity(CoffeeEntity coffee) {
        this.setId(coffee.id);
        this.setName(coffee.name);
        this.setDescription(coffee.description);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.ntnv.gldva.json.CustomJSONb getDescription() {
        return description;
    }

    public void setDescription(com.ntnv.gldva.json.CustomJSONb description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeEntity that = (CoffeeEntity) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {

        return "{" +
                "\"name\": \"" + name + "\"" +
                ", \"description\": " + description.toString() +
                '}';
    }
}

