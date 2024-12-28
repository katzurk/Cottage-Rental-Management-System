package MojaChata.pl.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity (name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countrySeq")
    @SequenceGenerator(name = "countrySeq", sequenceName = "COUNTRIES_SEQ", allocationSize = 1)
    @Column(name = "country_id")
    private long id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "language is mandatory")
    private String language;

    @NotBlank(message = "currency is mandatory")
    private String currency;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
