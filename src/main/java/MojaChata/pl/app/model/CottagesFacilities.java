package MojaChata.pl.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity (name= "cottages_facilities")
public class CottagesFacilities {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facilityId;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottageId;

    public Facility getFacilityId() {
        return this.facilityId;
    }

    public Cottage getCottageId() {
        return this.cottageId;
    }

    public void setFacilityId(Facility facilityId) {
        this.facilityId = facilityId;
    }

    public void setCottageId(Cottage cottageId) {
        this.cottageId = cottageId;
    }
}
