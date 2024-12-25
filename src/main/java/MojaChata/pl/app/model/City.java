package MojaChata.pl.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity (name= "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citySeq")
    @SequenceGenerator(name = "citySeq", sequenceName = "CITIES_SEQ")
    @Column(name = "city_id")
    private long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotNull(message = "population is mandatory")
    private Double population;

    @NotNull(message = "size is mandatory")
    private Integer size;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "country is mandatory")
    private Country country;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPopulation() {
        return this.population;
    }

    public Integer getSize() {
        return this.size;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
