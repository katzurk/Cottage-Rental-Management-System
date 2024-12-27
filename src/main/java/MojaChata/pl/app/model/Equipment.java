package MojaChata.pl.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity (name= "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipmentSeq")
    @SequenceGenerator(name = "equipmentSeq", sequenceName = "EQUIPMENTS_SEQ")
    @Column(name = "equipment_id")
    private long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    private String description;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescriotion() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}