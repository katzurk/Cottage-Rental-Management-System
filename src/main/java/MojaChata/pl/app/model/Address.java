package MojaChata.pl.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity (name= "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    private long id;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "Postal code is mandatory")
    @Column (name = "POSTAL_CODE")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @NotNull(message = "City is mandatory")
    private City city;

    public Long getId() {
        return this.id;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public City getCity() {
        return this.city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
